package com.linln.api.business.frombean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PhoneCallRequest {

    @ApiModelProperty("用户id")
    private String uid;

    @ApiModelProperty("设备id")
    private String eid;

    @ApiModelProperty("操作类型fail|end|start")
    private String flag;

    @ApiModelProperty("app呼叫日志id")
    private String callId;

    @ApiModelProperty("电话呼叫日志id")
    private String id;
}
