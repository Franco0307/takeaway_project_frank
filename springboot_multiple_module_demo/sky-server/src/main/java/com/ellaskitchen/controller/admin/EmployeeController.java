package com.ellaskitchen.controller.admin;


import com.ellaskitchen.properties.JwtProperties;
import com.ellaskitchen.service.EmployeeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping
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
    @PostMapping("/admin/login")




    /**
     * 退出
     *
     * @return
     */

    /**
     * 新增员工
     * @param employeeDTO
     * @return
     */

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */

    /**
     * 启用禁用员工账户
     * @param status
     * @param id
     * @return
     */

    /**
     * 根据iD查询用户信息
     * @param id
     * @return
     */

    /**
     * 编辑员工信息
     * @param employeeDTO
     * @return
     */



}
