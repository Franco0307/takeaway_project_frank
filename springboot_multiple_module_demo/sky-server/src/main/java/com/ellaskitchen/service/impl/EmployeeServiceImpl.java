package com.ellaskitchen.service.impl;

import com.ellaskitchen.constant.MessageConstant;
import com.ellaskitchen.constant.StatusConstant;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


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
        employee.setPassword(DigestUtils.md5DigestAsHex())


    }

    /**
     * 分页查询
     *
     * @param employeePageQueryDTO
     * @return
     */
    @Override
    public PageResult page(EmployeePageQueryDTO employeePageQueryDTO) {
        return null;
    }

    /**
     * 员工状态调整
     *
     * @param status
     * @param id
     */
    @Override
    public void changeStatus(Integer status, long id) {

    }

    /**
     * 编辑员工信息
     *
     * @param employeeDTO
     */
    @Override
    public void changeInformation(EmployeeDTO employeeDTO) {

    }

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    @Override
    public Employee getById(long id) {
        return null;
    }

    /**
     * 更改密码
     *
     * @param passwordEditDTO
     */
    @Override
    public boolean changePassword(PasswordEditDTO passwordEditDTO) {
        return false;
    }
}
