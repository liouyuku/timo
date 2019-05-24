package com.linln.api.business.frombean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BindRequest {

    @ApiModelProperty("用户ID")
    private String uId;

    @ApiModelProperty("小区ID")
    private String villageId;

    @ApiModelProperty("期数ID")
    private String periodsId;

    @ApiModelProperty("栋数ID")
    private String buildingId;

    @ApiModelProperty("门牌号 1304之类的")
    private String houseNumber;

    @ApiModelProperty("用户类型 业主：1 |租客：2 |家属：3 ")
    private String userType;

    @ApiModelProperty("sn")
    private String sn;

}
