package com.linln.api.business.frombean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CallUserRequest {

    //栋数ID
    @ApiModelProperty(value = "栋数ID")
    private String block;

    //门牌号
    @ApiModelProperty(value = "门牌号")
    private String callinfo;

    //设备ID
    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    //是否是小区
    @ApiModelProperty(value = "是否是小区")
    private String isVillage;
}
