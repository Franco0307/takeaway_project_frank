package com.ellaskitchen.mapper;

import com.ellaskitchen.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    Employee getByUsername(@Param("username") String username);


    /**
     *注册新员工
     * @param
     * @return
     */



    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */

    /**
     * 启用禁用员工账户,编辑员工信息
     * @param employee
     */


    /**
     * 根据iD查询用户信息
     * @param id
     * @return
     */

}
