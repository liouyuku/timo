package com.linln.api.business.frombean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PushRequest {

    @ApiModelProperty("设备ID /手机regid")
    private String deviceId;

    @ApiModelProperty("推送的内容 ，如果是下位机则直接写命令如 M001，如果是手机的话则是自己拼凑推送的json对象")
    private String content;

}
