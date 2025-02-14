package com.ellaskitchen.service.impl;

import com.ellaskitchen.constant.MessageConstant;
import com.ellaskitchen.constant.PasswordConstant;
import com.ellaskitchen.constant.StatusConstant;
import com.ellaskitchen.context.BaseContext;
import com.ellaskitchen.dto.EmployeeDTO;
import com.ellaskitchen.dto.EmployeeLoginDTO;
import com.ellaskitchen.dto.EmployeePageQueryDTO;
import com.ellaskitchen.dto.PasswordEditDTO;
import com.ellaskitchen.entity.Employee;
import com.ellaskitchen.exception.AccountLockedException;
import com.ellaskitchen.exception.AccountNotFoundException;
import com.ellaskitchen.exception.PasswordErrorException;
import com.ellaskitchen.mapper.EmployeeMapper;
import com.ellaskitchen.result.PageResult;
import com.ellaskitchen.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    //注入EmployeeMapper 依赖

    @Autowired
    private EmployeeMapper employeeMapper;




    /**
     * 员工登陆
     *
     * @param employeeLoginDTO
     * @return
     */
    //为什么传入是DTO，返回是Entity？
    //为什么不传入VO？ 封装之后传给前端
    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1 根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if(employee == null){
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        if (!password.equals(employee.getPassword())){
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        if (employee.getStatus() == StatusConstant.DISABLE){
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }
        //返回实体对象
        return employee;

    }

    /**
     * 新增员工
     *
     * @param employeeDTO
     */
    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        //实体类属性转换
        BeanUtils.copyProperties(employeeDTO,employee);
        employee.setStatus(StatusConstant.ENABLE);
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setCreateUser(BaseContext.getCurrentId());
//        employee.setUpdateUser(BaseContext.getCurrentId());
        //已经有AutoFill来操作了
        employeeMapper.insert(employee);
        BaseContext.removeCurrentId();//线程池？


    }

    /**
     * 分页查询
     *
     * @param employeePageQueryDTO
     * @return
     */
    @Override
    public PageResult page(EmployeePageQueryDTO employeePageQueryDTO) {
        //使用分页查询插件进行动态分页
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        //编写sql语句
        Page<Employee> page = employeeMapper.PageQuery(employeePageQueryDTO.getName());
        //拿到总记录数和结果
        long total = page.getTotal();
        List<Employee> list = page.getResult();
        //封装成pageResult对象
        PageResult pageResult = new PageResult();
        pageResult.setTotal(total);
        pageResult.setRecords(list);
        return pageResult;
    }

    /**
     * 员工状态调整
     *
     * @param status
     * @param id
     */
    @Override
    public void changeStatus(Integer status, long id) {
        Employee employee = Employee.builder()
                .status(status)
                .id(id)
//                .updateTime(LocalDateTime.now())
//                .updateUser(BaseContext.getCurrentId())
                .build();
        employeeMapper.update(employee);

    }

    /**
     * 编辑员工信息
     *
     * @param employeeDTO
     */
    @Override
    public void changeInformation(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        employeeMapper.update(employee);
    }

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    @Override
    public Employee getById(long id) {
        Employee employee = employeeMapper.getById(id);
        return employee;


    }

    /**
     * 更改密码 passwordEditDTO里，新旧密码都是未加密
     *
     * @param passwordEditDTO
     * @return
     */
    @Override
    public boolean changePassword(PasswordEditDTO passwordEditDTO) {
        Employee employee = employeeMapper.getById(passwordEditDTO.getEmpId());
        //获取DTO输入的oldPassword，然后用md5加密
        String mypassword = employee.getPassword();
        String oldPassword = DigestUtils.md5DigestAsHex(passwordEditDTO.getOldPassword().getBytes());
        String newPassword = DigestUtils.md5DigestAsHex(passwordEditDTO.getNewPassword().getBytes());
        if (mypassword.equals(oldPassword)){
            employeeMapper.changePassword(newPassword, passwordEditDTO.getEmpId());
            return true;
        }else {
            return false;
        }

    }
}
