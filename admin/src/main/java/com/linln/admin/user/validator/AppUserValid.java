package com.linln.admin.user.validator;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author 小懒虫
 * @date 2019/05/12
 */
@Data
public class AppUserValid implements Serializable {
    @NotEmpty(message = "姓名不能为空")
    private String name;
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    @NotNull(message = "栋数不能为空")
    private Long numberOfBuildingsId;
    @NotNull(message = "期数不能为空")
    private Long numberOfPeriodsId;
    @NotEmpty(message = "房间号不能为空")
    //@Pattern(regexp = "([0-9]{4})",message = "房间号只能为4位的数字")
    private String roomNumber;
    @NotEmpty(message = "门禁卡的失效时间不能为空")
    private String endDate;
    @NotEmpty(message = "电话号码不能为空")
    @Pattern(regexp = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", message = "手机号码格式不正确")
    private String mobilePhone;
}