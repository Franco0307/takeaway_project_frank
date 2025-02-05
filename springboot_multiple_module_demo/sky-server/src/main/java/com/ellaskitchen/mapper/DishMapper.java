package com.ellaskitchen.mapper;

import com.ellaskitchen.annotations.AutoFill;
import com.ellaskitchen.dto.DishChangeStatusDTO;
import com.ellaskitchen.entity.Dish;
import com.ellaskitchen.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper {

    /**
     *新增菜品
     * @param dish
     */
    @AutoFill(OperationType.INSERT)
    @Insert( "insert dish(name, category_id, price, image, description, status, create_time, update_time, create_user, update_user)"+
    "values"+"(#{name},#{categoryId}, #{price}, #{image},#{description} ,#{status} ,#{createTime} ,#{updateTime} ,#{createUser} ,#{updateUser})")
    void save(Dish dish);

    /**
     * 删除菜品
     * @param id
     */
    void deleteById(long id);

    /**
     * 修改菜品
     * @param dish
     */
    @AutoFill(OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 菜品起售 停售 修改状态
     * @param dishChangeStatusDTO
     */
    void dishChangeStatus(DishChangeStatusDTO dishChangeStatusDTO);
    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
}

