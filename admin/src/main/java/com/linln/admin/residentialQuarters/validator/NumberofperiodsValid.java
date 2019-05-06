package com.linln.admin.residentialQuarters.validator;

import lombok.Data;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

/**
 * @author 小懒虫
 * @date 2019/04/30
 */
@Data
public class NumberofperiodsValid implements Serializable {
    @NotEmpty(message = "期数名字不能为空")
    private String name;
}