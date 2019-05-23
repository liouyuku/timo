package com.linln.api.business.frombean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @ApiModelProperty(value = "用户id")
    private String id;
    @ApiModelProperty(value = "用户昵稱，不是用户名")
    // 昵称
    private String name;
    @ApiModelProperty(value = "性别0：男|1 ： 女")
    // 性别
    private String sex;
    @ApiModelProperty(value = "邮箱")
    // 邮箱
    private String email;
    @ApiModelProperty(value = "备用电话号码")
    // 备用号码
    private String reserve;
}
