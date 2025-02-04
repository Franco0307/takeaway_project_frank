package com.ellaskitchen.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    /**
     * 新增菜品分类
     * @param category
     */
    @Insert("insert into category(type, name, sort )")



}
