package com.ellaskitchen.controller.admin;


import com.ellaskitchen.constant.JwtClaimsConstant;
import com.ellaskitchen.constant.MessageConstant;
import com.ellaskitchen.context.BaseContext;
import com.ellaskitchen.dto.EmployeeDTO;
import com.ellaskitchen.dto.EmployeeLoginDTO;
import com.ellaskitchen.dto.EmployeePageQueryDTO;
import com.ellaskitchen.dto.PasswordEditDTO;
import com.ellaskitchen.entity.Employee;
import com.ellaskitchen.properties.JwtProperties;
import com.ellaskitchen.result.PageResult;
import com.ellaskitchen.result.Result;
import com.ellaskitchen.service.EmployeeService;
import com.ellaskitchen.utils.JwtUtils;
import com.ellaskitchen.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("admin/employee")
@Api(tags = "员工相关接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtProperties jwtProperties;


    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @ApiOperation("员工登陆")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        log.info("员工登陆:{}",employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登陆成功后，生成jwt令牌
        Map<String , Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtils.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getTtl(),
                claims
        );
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .username(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }





    /**
     * 退出
     *
     * @return
     */
    @ApiOperation("员工登出")
    @PostMapping("/logout")
    public Result<String> logout (){
        return Result.success(MessageConstant.EXIT_OK);
    }



    /**
     * 新增员工
     * @param employeeDTO
     * @return
     */
    @ApiOperation("新增员工")
    @PostMapping
    public Result save(@RequestBody EmployeeDTO employeeDTO){
        log.info("新增员工:{}",employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();

    }


    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
    @ApiOperation("员工分页查询")
    @PostMapping("/page")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("查询员工:{}", employeePageQueryDTO);
        PageResult pageResult = employeeService.page(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用禁用员工账户
     * @param status
     * @param id
     * @return
     */
    @ApiOperation("启用禁用员工账号")
    @PostMapping("/status/{status}")
    public Result changeStatus(@PathVariable Integer status, long id){
        log.info("更改人：{},更改状态：{}",id,status);
        employeeService.changeStatus(status, id);
        return Result.success();
    }

    /**
     * 根据iD查询用户信息
     * @param id
     * @return
     */
    @ApiOperation("根据iD查询用户信息")
    @PostMapping("/{id}")
    public Result<Employee> getById(@PathVariable long id){
        log.info("员工id:{}",id);
        Employee employee = employeeService.getById(id);
        employee.setPassword("****");// 是不是用VO比较好？
        return Result.success(employee);

    }

    /**
     * 编辑员工信息
     * @param employeeDTO
     * @return
     */
    @ApiOperation("编辑员工信息")
    @PutMapping
    public Result changeInformation(@RequestBody EmployeeDTO employeeDTO){
        log.info("编辑员工信息");
        employeeService.changeInformation(employeeDTO);
        return Result.success();
    }

    /**
     * 修改密码
     * @param passwordEditDTO
     * @return
     */
    @ApiOperation("修改密码")
    @PutMapping("/editPassword")
    public Result changePassword(@RequestBody PasswordEditDTO passwordEditDTO){
        long id = BaseContext.getCurrentId();
        passwordEditDTO.setEmpId(id);
        Boolean flag = employeeService.changePassword(passwordEditDTO);
        if (flag){
            return Result.success();
        }else {
            return Result.error("密码错误，请重新输入密码");
        }

    }




}
