package com.ellaskitchen.service.impl;

import com.ellaskitchen.dto.CategoryDTO;
import com.ellaskitchen.dto.CategoryPageQueryDTO;
import com.ellaskitchen.dto.CategoryUpdateStatusDTO;
import com.ellaskitchen.entity.Category;
import com.ellaskitchen.mapper.CategoryMapper;
import com.ellaskitchen.service.CategoryService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    /**
     * 新增品类
     *
     * @param category
     */
    @Override
    public void save(Category category) {

    }

    /**
     * 新增品类
     *
     * @param categoryDTO
     */
    @Override
    public void save(CategoryDTO categoryDTO) {

    }

    /**
     * 根据id删除品类
     *
     * @param id
     */
    @Override
    public void delete(long id) {

    }

    /**
     * 根据id查询category
     *
     * @param id
     * @return
     */
    @Override
    public Category getById(long id) {
        return null;
    }

    /**
     * 修改category
     *
     * @param category
     * @Param category
     */
    @Override
    public void updateCategory(Category category) {

    }

    /**
     * 分类启用禁用
     *
     * @param categoryUpdateStatusDTO
     */
    @Override
    public void updateStatus(CategoryUpdateStatusDTO categoryUpdateStatusDTO) {

    }

    /**
     * 分类查询
     *
     * @param type
     * @return
     */
    @Override
    public List<Category> getByType(Integer type) {
        return List.of();
    }

    /**
     * 分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */
    @Override
    public Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        return null;
    }
}
