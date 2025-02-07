package com.ellaskitchen.mapper;

import com.ellaskitchen.entity.SetMealDish;

import java.util.List;

public interface SetMealDishMapper {
    /**
     * 判断当前菜品是否被套餐关联了
     * @param ids
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> ids);

    /**
     * 保存套餐和菜品的关联关系
     * @param setMealDishes
     */
    void insertBatch(List<SetMealDish> setMealDishes);

    /**
     * 删除套餐餐品关系表中的数据
     * @param id
     */
    void deleteBySetMaleId(Long id);

    /**
     * 根据套餐信息查询菜品信息
     * @param id
     * @return
     */
    List<SetMealDish> getBySetMealId(Long id);
}
