package com.linln.api.business.frombean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class APPLoginRequest {
    @ApiModelProperty("手机型号")
    private String mobile_type;
    @ApiModelProperty("系统类型.IOS/ANDORID")
    private String os_type;
    @ApiModelProperty("安卓sdk版本")
    private String os_version;
    @ApiModelProperty("应用版本")
    private String app_version;
    @ApiModelProperty("推送regkey")
    private String regkey;
    @ApiModelProperty("用户名")
    @NotBlank(message="用户名不能为空")
    private String userName;
    @ApiModelProperty("用户密码")
    @NotBlank(message="密码不能为空")
    private String userPwd;
    @ApiModelProperty("登陆标示，用来区分是否为第三方登陆 1/0")
    private String flag;
}
