package com.ellaskitchen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("员工登陆时传递的数据模型")
public class EmployeeLoginDTO {

    //员工姓名 为啥不用id ？
    @ApiModelProperty("用户名")
    private String username;

    //员工密码
    @ApiModelProperty("用户密码")
    private String password;


}
