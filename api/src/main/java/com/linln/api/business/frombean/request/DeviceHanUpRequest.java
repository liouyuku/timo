package com.linln.api.business.frombean.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeviceHanUpRequest {

    @ApiModelProperty(value = "設備ID")
    private String deviceId;

    @ApiModelProperty(value = "栋数id")
    private String building;

    @ApiModelProperty(value = "门牌号")
    private String roomNumber;

}
