package com.ellaskitchen.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CategoryUpdateStatusDTO implements Serializable {
    private Integer status;//菜品品类状态
    private long   empId;//操作员工id
    private LocalDateTime updateTime;//状态更新时间
    private long categoryId;//菜品品类id




}
