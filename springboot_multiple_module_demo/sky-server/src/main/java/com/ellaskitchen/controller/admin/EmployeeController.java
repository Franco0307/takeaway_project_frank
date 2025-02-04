package com.ellaskitchen.controller.admin;


import com.ellaskitchen.constant.JwtClaimsConstant;
import com.ellaskitchen.dto.EmployeeLoginDTO;
import com.ellaskitchen.entity.Employee;
import com.ellaskitchen.properties.JwtProperties;
import com.ellaskitchen.result.Result;
import com.ellaskitchen.service.EmployeeService;
import com.ellaskitchen.utils.JwtUtils;
import com.ellaskitchen.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
