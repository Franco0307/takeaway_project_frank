package com.ellaskitchen.mapper;

import com.ellaskitchen.annotations.AutoFill;
import com.ellaskitchen.dto.EmployeeDTO;
import com.ellaskitchen.entity.Employee;
import com.ellaskitchen.enumeration.OperationType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {
//    mybatis可以采用注解+配置文件两种方式，可以混合使用

    /** get
     * 根据用户名查询员工
     * @param username
     * @return
     */

    @Select("select * from employee where username = #{username}")
    Employee getByUsername( String username);


    /** post
     *注册新员工
     * @param
     * @return
     */
    @AutoFill(OperationType.INSERT)
    @Insert("Insert into employee (id, name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) " +
            "values " +
            "(#{id},#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void insert(Employee employee);


    /** get
     * 员工分页查询
     * @param name
     * @return
     */

    Page<Employee> PageQuery(String name);


    /**
     * 员工状态调整
     * @param employee
     */
    @AutoFill(OperationType.UPDATE)
    void update(Employee employee);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @Select("select * from employee where id = #{id}")
    Employee getById(long id);

    /**
     * 员工更改密码
     * @param newPassword
     */
    @Update("update employee set password = #{newPassword} where id = #{id}")
    void changePassword(String newPassword, long id);




}
