package com.ellaskitchen.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {
    //品类id
    private long id;
    //品类名
    private String name;
    //type 1 菜品 or 2 套餐
    private Integer type;
    //排序
    private Integer sort;

}
