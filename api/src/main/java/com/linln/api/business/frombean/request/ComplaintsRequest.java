package com.linln.api.business.frombean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ComplaintsRequest {

    @ApiModelProperty(value = "信息标题")
    private String title;
    @ApiModelProperty(value = "信息内容")
    private String content;
    @ApiModelProperty(value = "用户ID")
    private String uid;
    @ApiModelProperty(value = "上传的图片;这里只做说明;不是字符串;不要使用file;可以用img或者imgfile等字段")
    private String file;
}
