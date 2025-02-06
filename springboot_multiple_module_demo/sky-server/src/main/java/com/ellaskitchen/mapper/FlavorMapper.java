package com.ellaskitchen.mapper;

import com.ellaskitchen.entity.Dish;
import com.ellaskitchen.entity.DishFlavor;

import java.util.List;

public interface FlavorMapper {

    //保存菜品口味
    /**
     * @param dishFlavors
     */
    void saveFlavors(List<DishFlavor> dishFlavors);

    //根据id菜品查询口味,可能返回很多口味
    /**
     * * @param dishId
     * @return
     */
    List<DishFlavor> getByDishId(long dishId);

    //根据dishId删除菜品口味
    /**
     * @param  dishId
     */
    void deleteByDishId(List<Long> dishId);



}
