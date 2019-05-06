package com.linln.admin.residentialQuarters.validator;

import lombok.Data;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

/**
 * @author 小懒虫
 * @date 2019/04/30
 */
@Data
public class ResidentialQuartersValid implements Serializable {
    @NotEmpty(message = "标题不能为空")
    private String name;
    @NotEmpty(message = "地址不能为空")
    private String address;
}