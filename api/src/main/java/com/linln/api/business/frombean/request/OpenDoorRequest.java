package com.linln.api.business.frombean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OpenDoorRequest {

    @ApiModelProperty("门牌号")
    @NotBlank(message = "门牌号不能为空")
    private String house_number;
    @ApiModelProperty("栋数ID")
    @NotBlank(message = "栋数不能为空")
    private String building;
    @ApiModelProperty("设备id")
    @NotBlank(message = "设备id不能为空")
    private String deviceId;
    @ApiModelProperty("用户id")
    @NotBlank(message = "用户id不能为空")
    private String uid;
    @ApiModelProperty("是否是pad开门")
    private String isPad;
}
