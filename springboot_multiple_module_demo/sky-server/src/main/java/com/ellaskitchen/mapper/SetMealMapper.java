package com.ellaskitchen.mapper;

import com.ellaskitchen.annotations.AutoFill;
import com.ellaskitchen.dto.SetMealChangeStatusDTO;
import com.ellaskitchen.dto.SetMealPageQueryDTO;
import com.ellaskitchen.entity.SetMeal;
import com.ellaskitchen.enumeration.OperationType;
import com.ellaskitchen.vo.DishItemVO;
import com.ellaskitchen.vo.SetMealVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.checkerframework.checker.units.qual.A;

import java.util.List;
import java.util.Map;

@Mapper
public interface SetMealMapper {



    /**
     * 根据分类id查询套餐的数量
     * @param categoryId
     * @return
     */
    Integer countByCategoryId(@Param("categoryId") Long categoryId);

    /**
     * 向套餐表插入数据
     * @param setmeal
     */
    @AutoFill(OperationType.INSERT)
    void insert(SetMeal setmeal);

    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    Page<SetMealVO> pageQuery(SetMealPageQueryDTO setmealPageQueryDTO);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    SetMeal getById(Long id);

    /**
     * 根据id删除数据
     * @param id
     */
    void deleteById(Long id);

    /**
     * 修改套餐表
     * @param setmeal
     */
    @AutoFill(OperationType.UPDATE)
    void update(SetMeal setmeal);

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
    List<DishItemVO> getDishItemBySetmealId(Long id);

    /**
     * 根据条件统计套餐数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);

}
