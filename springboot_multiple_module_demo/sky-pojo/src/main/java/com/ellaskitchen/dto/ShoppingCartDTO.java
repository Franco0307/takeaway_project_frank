package com.ellaskitchen.dto;

import lombok.Data;

@Data
public class ShoppingCartDTO {
    private Long dishId;
    private Long setmealId;
    private String dishFlavor;
}
