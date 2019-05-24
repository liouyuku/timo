package com.linln.admin.user.validator;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author 小懒虫
 * @date 2019/05/16
 */
@Data
public class FaceRecognitionValid implements Serializable {
    @NotEmpty(message = "电话号码不能为空")
    @Pattern(regexp = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", message = "手机号码格式不正确")
    private String mobile;
}