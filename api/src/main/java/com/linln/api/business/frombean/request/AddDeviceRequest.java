package com.linln.api.business.frombean.request;

import com.linln.api.business.frombean.base.Request;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddDeviceRequest extends Request {

    @ApiModelProperty(value = "mac地址")
    private String mac;
    @ApiModelProperty(value="push类型：mina，jpush")
    private String pushType;
    @ApiModelProperty(value="flag")
    private Integer flag;
    @ApiModelProperty(value="版本")
    private String version;
}
