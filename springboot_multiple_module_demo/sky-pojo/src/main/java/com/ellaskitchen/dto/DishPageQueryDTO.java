package com.ellaskitchen.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DishPageQueryDTO implements Serializable {
    //页数
    private int page;
    //每页显示数
    private int pageSize;
    //查询功能 菜品名字
    private String name;
    //分类功能 菜类id
    private long categoryId;
    //启用 禁用功能
    private Integer status;


}
