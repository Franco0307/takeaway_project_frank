package com.ellaskitchen.mapper;

import com.ellaskitchen.entity.Dish;
import com.ellaskitchen.entity.DishFlavor;

import java.util.List;

public interface FlavorMapper {

    //    批量插入口味数据
    void insertBatch(List<DishFlavor> flavors);

    //    删除菜单关联的口味数据
    void deleteByDishId(Long id);

    //    根据菜品id查询口味数据
    List<DishFlavor> getByDishId(Long id);
}
