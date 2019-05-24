package com.linln.admin.residentialQuarters.validator;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author 小懒虫
 * @date 2019/05/07
 */
@Data
public class NumberOfBuildingsValid implements Serializable {
    @NotEmpty(message = "栋数名称不能为空")
    private String name;
}