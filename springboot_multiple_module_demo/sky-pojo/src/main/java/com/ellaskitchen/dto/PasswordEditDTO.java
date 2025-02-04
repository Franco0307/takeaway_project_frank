package com.ellaskitchen.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PasswordEditDTO implements Serializable {

    //员工id
    private long empId;

    //旧密码 password
    private String oldPassword;

    //新密码
    private String newPassword;
}
