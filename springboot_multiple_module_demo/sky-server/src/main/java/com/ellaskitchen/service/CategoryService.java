package com.ellaskitchen.service;

import com.ellaskitchen.dto.CategoryDTO;
import com.ellaskitchen.dto.CategoryPageQueryDTO;
import com.ellaskitchen.dto.CategoryUpdateStatusDTO;
import com.ellaskitchen.entity.Category;
import com.github.pagehelper.Page;

import java.util.List;

public interface CategoryService {


    /**
     * 新增品类
     * @param categoryDTO
     */
    public void save(CategoryDTO categoryDTO);

    /**
     * 根据id删除品类
     * @param id
     */
    public void delete(long id);

    /**
     *根据id查询category
     * @param id
     * @return
     */
    public Category getById(long id);

    /**
     * 修改category
     * @Param categoryDTO
     */
    public void updateCategory(CategoryDTO categoryDTO);

    /**
     * 分类启用禁用
     * @param categoryUpdateStatusDTO
     */
    public void updateStatus(CategoryUpdateStatusDTO categoryUpdateStatusDTO);

    /**
     * 分类查询
     * @param type
     * @return
     */
    public List<Category> getByType(Integer type);

    /**
     * 分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    public Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);


}
