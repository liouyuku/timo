package com.linln.api.business.frombean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FindPasswordRequest {

    @ApiModelProperty("用户ID|用户电话号码|用户名")
    @NotBlank(message = "用户名不能为空")
    private String uid;
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String pwd;
}
