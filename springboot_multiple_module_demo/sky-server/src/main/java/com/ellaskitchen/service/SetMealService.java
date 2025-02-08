package com.ellaskitchen.service;

import com.ellaskitchen.dto.SetMealDTO;
import com.ellaskitchen.dto.SetMealPageQueryDTO;
import com.ellaskitchen.entity.SetMeal;
import com.ellaskitchen.result.PageResult;
import com.ellaskitchen.vo.DishItemVO;
import com.ellaskitchen.vo.SetMealVO;

import java.util.List;

public interface SetMealService {
    /**
     * 新增套餐
     * @param setmealDTO
     */
    void saveWithDish(SetMealDTO setmealDTO);

    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetMealPageQueryDTO setmealPageQueryDTO);

    /**
     * 批量删除套餐
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询套餐
     * @param id
     * @return
     */
    SetMealVO getByIdWithDish(Long id);

    /**
     * 修改套餐
     * @param setmealDTO
     */
    void update(SetMealDTO setmealDTO);

    /**
     * 套餐起售停售
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    List<SetMeal> list(SetMeal setmeal);

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);

}
