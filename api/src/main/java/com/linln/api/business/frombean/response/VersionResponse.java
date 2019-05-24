package com.linln.api.business.frombean.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VersionResponse {

    @ApiModelProperty(value = "版本号")
    private  String versionNum;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    @ApiModelProperty(value = "更新描述")
    private String note;

    @ApiModelProperty(value = "是否要强制更新")
    private String imposed;
}
