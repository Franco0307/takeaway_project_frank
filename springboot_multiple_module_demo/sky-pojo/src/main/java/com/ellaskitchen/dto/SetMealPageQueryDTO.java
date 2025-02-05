package com.ellaskitchen.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SetMealPageQueryDTO implements Serializable {
    //页数
    private int page;
    //每页显示数量
    private int pageSize;
    //根据状态查询
    private Integer status;
    //根据套餐名字查询
    private String name;
    //根据套餐分类查询
    private Integer categoryId;


}
