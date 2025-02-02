package com.ellaskitchen.dto;

import lombok.Data;

@Data
public class PasswordEditDTO {
    private String
    //员工id
    private long id;

    //旧密码 password
    private String oldPassword;

    //新密码
    private String newPassword;
}
