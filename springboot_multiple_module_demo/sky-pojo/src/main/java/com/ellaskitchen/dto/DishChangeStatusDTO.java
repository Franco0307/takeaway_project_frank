package com.ellaskitchen.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class DishChangeStatusDTO implements Serializable {
    //菜品id
    private long id;
    //菜品起禁用状态
    private Integer status;
    //公用字段 更新员工，更新时间
    private long empId;
    private LocalDateTime updateTime;

}
