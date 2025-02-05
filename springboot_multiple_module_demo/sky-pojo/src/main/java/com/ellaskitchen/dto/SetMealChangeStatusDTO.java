package com.ellaskitchen.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SetMealChangeStatusDTO implements Serializable {
    //套餐状态
    private Integer status;
    //套餐id
    private Long id;
    //修改时间
    private LocalDateTime updateTime;
    //修改员工id
    private Long empId;


}
