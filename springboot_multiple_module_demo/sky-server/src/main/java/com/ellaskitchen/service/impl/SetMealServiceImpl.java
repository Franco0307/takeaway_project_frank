package com.ellaskitchen.service.impl;

import com.ellaskitchen.constant.MessageConstant;
import com.ellaskitchen.constant.StatusConstant;
import com.ellaskitchen.dto.SetMealDTO;
import com.ellaskitchen.dto.SetMealPageQueryDTO;
import com.ellaskitchen.entity.Dish;
import com.ellaskitchen.entity.SetMeal;
import com.ellaskitchen.entity.SetMealDish;
import com.ellaskitchen.exception.DeleteNotAllowedException;
import com.ellaskitchen.exception.SetMealEnableFailedException;
import com.ellaskitchen.mapper.DishMapper;
import com.ellaskitchen.mapper.SetMealDishMapper;
import com.ellaskitchen.mapper.SetMealMapper;
import com.ellaskitchen.result.PageResult;
import com.ellaskitchen.service.SetMealService;
import com.ellaskitchen.vo.DishItemVO;
import com.ellaskitchen.vo.SetMealVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    SetMealMapper setMealMapper;
    @Autowired
    DishMapper dishMapper;
    @Autowired
    SetMealDishMapper setMealDishMapper;
    /**
     * 新增套餐
     *
     * @param setmealDTO
     */
    @Transactional
    @Override
    public void saveWithDish(SetMealDTO setmealDTO) {
        SetMeal setMeal = new SetMeal();
        BeanUtils.copyProperties(setmealDTO,setMeal);
        //把套餐信息放入套餐表

        setMealMapper.insert(setMeal);
        //在注入数据库之后才有setMealId 主键
        //把套餐和菜品的一对多信息插入SetMealDish中间表
        List<SetMealDish> setMealDishes = setmealDTO.getSetmealDishes();
        setMealDishes.forEach(
                setMealDish -> {
                    setMealDish.setSetmealId(setMeal.getId());
                }
        );
        setMealDishMapper.insertBatch(setMealDishes);
    }

    /**
     * 分页查询
     *
     * @param setmealPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(SetMealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());

        Page<SetMealVO> page = setMealMapper.pageQuery(setmealPageQueryDTO);

        return new PageResult(page.getTotal(), page.getResult());



    }

    /**
     * 批量删除套餐
     *
     * @param ids
     */
    @Transactional
    @Override
    public void deleteBatch(List<Long> ids) {
        //        起售中的套餐不能删除

        ids.forEach(id->{
            SetMeal setmeal = setMealMapper.getById(id);
            if (StatusConstant.ENABLE == setmeal.getStatus()) {
                throw new DeleteNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        });
//先确保了所有的删除id都不是起售的，原子性！！！
        ids.forEach(id->{
//            删除套餐表中的数据
            setMealMapper.deleteById(id);

//            删除套餐餐品关系表中的数据
            setMealDishMapper.deleteBySetMaleId(id);
        });


    }

    /**
     * 根据id查询套餐
     *
     * @param id
     * @return
     */
    @Override
    public SetMealVO getByIdWithDish(Long id) {
        SetMealVO setMealVO = new SetMealVO();
        BeanUtils.copyProperties(setMealMapper.getById(id),setMealVO);
        setMealVO.setSetmealDishes(setMealDishMapper.getBySetMealId(id));
        return setMealVO;
    }

    /**
     * 修改套餐
     *
     * @param setmealDTO
     */
    @Transactional
    @Override
    public void update(SetMealDTO setmealDTO) {
        SetMeal setMeal = new SetMeal();
        BeanUtils.copyProperties(setmealDTO,setMeal);
        //在套餐表里更新套餐
        setMealMapper.update(setMeal);
        //在套餐菜品表里也更新套餐和菜品
        //根据套餐id删除套餐菜品表所有记录
        //根据套餐id插入新的记录
        setMealDishMapper.deleteBySetMaleId(setMeal.getId());
        List<SetMealDish> setMealDishList = setmealDTO.getSetmealDishes();
        setMealDishList.forEach(setMealDish -> setMealDish.setSetmealId(setmealDTO.getId()));

        setMealDishMapper.insertBatch(setMealDishList);

    }

    /**
     * 套餐起售停售
     *
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        //起售套餐时，判断套餐内是否有停售菜品，有停售菜品提示"套餐内包含未启售菜品，无法启售"
        if (status == StatusConstant.ENABLE) {
            //这里用了连表查询，连接了dish表和中间表，
            List<Dish> dishList = dishMapper.getBySetmealId(id);
            if (dishList != null && dishList.size() > 0) {
                dishList.forEach(dish -> {
                    if (StatusConstant.DISABLE == dish.getStatus()) { // 有停售商品
                        throw new SetMealEnableFailedException(MessageConstant.SETMEAL_ENABLE_FAILED);
                    }
                });
            }
        }

        SetMeal setmeal = SetMeal.builder()
                .id(id)
                .status(status)
                .build();
        setMealMapper.update(setmeal);
    }

    /**
     * 条件查询
     *
     * @param setmeal
     * @return
     */
    @Override
    public List<SetMeal> list(SetMeal setmeal) {
        List<SetMeal> setMealList = setMealMapper.list(setmeal);
       return setMealList;
    }

    /**
     * 根据id查询菜品选项
     *
     * @param id
     * @return
     */
    @Override
    public List<DishItemVO> getDishItemById(Long id) {
        return setMealMapper.getDishItemBySetmealId(id);
    }
}
