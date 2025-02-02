package com.ellaskitchen.service;

import com.ellaskitchen.dto.EmployeeDTO;
import com.ellaskitchen.dto.EmployeePageQueryDTO;
import com.ellaskitchen.dto.PasswordEditDTO;
import com.ellaskitchen.entity.Employee;
import com.ellaskitchen.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登陆
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult page(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 员工状态调整
     * @param status
     * @param id
     */
    void changeStatus(Integer status, long id);

    /**
     * 编辑员工信息
     * @param employeeDTO
     */
    void changeInformation(EmployeeDTO employeeDTO);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Employee getById(long id);

    /**
     * 更改密码
     * @param passwordEditDTO
     */
    boolean changePassword(PasswordEditDTO passwordEditDTO);

}
