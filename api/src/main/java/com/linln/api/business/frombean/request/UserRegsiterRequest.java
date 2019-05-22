package com.linln.api.business.frombean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserRegsiterRequest {

    @ApiModelProperty("真实名字")
    private String name;

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("推送sdk 生成的regkey")
    private String regkey;

    @ApiModelProperty("性别0(男),1")
    @Range(min = 0, max = 1, message = "性别为0或1")
    private String sex;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String pwd;

    @ApiModelProperty("电话号码")
    @Pattern(regexp = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$", message = "电话号码格式不对")
    private String phone;

    @ApiModelProperty("是否在线")
    private String IsOnline;
    @ApiModelProperty("备用电话号码")
    private String beiyongphone;
    @ApiModelProperty("邮箱")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式不对")
    private String email;

    @ApiModelProperty("第三方注册ID")
    private String openId;

    @ApiModelProperty("软件版本号")
    private String appversions;

    @ApiModelProperty("手机型号")
    private String phoneversion;

    @ApiModelProperty("微信:0,QQ:1,微博：2，其他：3")
    @Range(min = 0, max = 3, message = "regType数据不对")
    private String regType;
}
