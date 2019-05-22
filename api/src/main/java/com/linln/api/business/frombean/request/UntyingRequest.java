package com.linln.api.business.frombean.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UntyingRequest {
    @ApiModelProperty("用户ID")
    @NotBlank(message = "用户id不能为空")
    private String uid;
    @ApiModelProperty("门牌ID")
    @NotBlank(message = "门牌id不能为空")
    private String house_number_id;
    @ApiModelProperty("栋数ID")
    @NotBlank(message = "栋数id不能为空")
    private String building;
}
