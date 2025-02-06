package com.ellaskitchen.service.impl;

import com.ellaskitchen.constant.MessageConstant;
import com.ellaskitchen.dto.CategoryDTO;
import com.ellaskitchen.dto.CategoryPageQueryDTO;
import com.ellaskitchen.dto.CategoryUpdateStatusDTO;
import com.ellaskitchen.entity.Category;
import com.ellaskitchen.exception.DeleteNotAllowedException;
import com.ellaskitchen.mapper.CategoryMapper;
import com.ellaskitchen.mapper.DishMapper;
import com.ellaskitchen.mapper.SetMealMapper;
import com.ellaskitchen.result.PageResult;
import com.ellaskitchen.service.CategoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetMealMapper setMealMapper;
    /**
     * 新增品类
     *
     * @param categoryDTO
     */
    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        //新设置的菜类设置为禁用
        category.setStatus(0);
        categoryMapper.insert(category);
    }


    /**
     * 根据id删除品类
     * @param id
     */
    @Override
    public void delete(long id) {
        //查询目前目录是否关联了菜品，如果关联了就报错
        Integer count = dishMapper.countByCategoryId(id);
        if (count > 0){
            throw new DeleteNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }

        //查询当前目录是否关联了套餐，如果关联了就报错
        count = setMealMapper.countByCategoryId(id);
        if (count > 0){
            throw new DeleteNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        categoryMapper.delete(id);
    }


    /**
     * 修改category
     *
     * @param categoryDTO
     */
    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        categoryMapper.updateCategory(category);
    }

    /**
     * 分类启用禁用
     *
     * @param categoryUpdateStatusDTO
     */
    @Override
    public void updateStatus(CategoryUpdateStatusDTO categoryUpdateStatusDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryUpdateStatusDTO,category);
        categoryMapper.updateCategory(category);

    }

    /**
     * 分类查询
     *
     * @param type
     * @return
     */
    @Override
    public List<Category> getByType(Integer type) {
       List<Category> categoryList = categoryMapper.gettByType(type);
       return categoryList;
    }

    /**
     * 分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
        ////下一条sql进行分页，自动加入limit关键字分页
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());



    }
}
