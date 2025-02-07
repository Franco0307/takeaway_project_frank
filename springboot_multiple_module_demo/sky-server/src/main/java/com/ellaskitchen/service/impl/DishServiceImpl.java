package com.ellaskitchen.service.impl;

import com.ellaskitchen.dto.DishChangeStatusDTO;
import com.ellaskitchen.dto.DishDTO;
import com.ellaskitchen.dto.DishPageQueryDTO;
import com.ellaskitchen.entity.Dish;
import com.ellaskitchen.mapper.DishMapper;
import com.ellaskitchen.mapper.FlavorMapper;
import com.ellaskitchen.mapper.SetMealDishMapper;
import com.ellaskitchen.mapper.SetMealMapper;
import com.ellaskitchen.result.PageResult;
import com.ellaskitchen.service.DishService;
import com.ellaskitchen.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private FlavorMapper flavorMapper;
    //为啥DishService层需要注入这两个依赖？
    @Autowired
    private SetMealMapper setMealMapper;
    @Autowired
    private SetMealDishMapper setMealDishMapper;

    /**
     * 新增菜品
     *
     * @param dishDTO
     */
    @Override
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);

    }

    /**
     * 菜品分页查询
     *
     * @param dishPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        return null;
    }

    /**
     * 菜品批量删除
     *
     * @param ids
     */
    @Override
    public void deleteBatch(List<Long> ids) {

    }

    /**
     * 根据id查询菜品
     *
     * @param id
     * @return
     */
    @Override
    public DishVO getByIdWithFlavor(Long id) {
        return null;
    }

    /**
     * 修改菜品
     *
     * @param dishDTO
     */
    @Override
    public void updateWithFlavor(DishDTO dishDTO) {

    }

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    @Override
    public List<Dish> list(Long categoryId) {
        return List.of();
    }

    /**
     * 条件查询菜品和口味
     *
     * @param dish
     * @return
     */
    @Override
    public List<DishVO> listWithFlavor(Dish dish) {
        return List.of();
    }

    /**
     * 菜品起售停售
     *
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {

    }
}
