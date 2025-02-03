package com.ellaskitchen.service.impl;

import com.ellaskitchen.dto.EmployeeDTO;
import com.ellaskitchen.dto.EmployeePageQueryDTO;
import com.ellaskitchen.dto.PasswordEditDTO;
import com.ellaskitchen.entity.Employee;
import com.ellaskitchen.result.PageResult;
import com.ellaskitchen.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {


    /**
     * 员工登陆
     *
     * @param employeeLoginDTO
     * @return
     */
    @Override
    public Employee login(EmployeeDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = em

    }

    /**
     * 新增员工
     *
     * @param employeeDTO
     */
    @Override
    public void save(EmployeeDTO employeeDTO) {

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
