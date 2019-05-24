package com.linln.admin.residentialQuarters.validator;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 小懒虫
 * @date 2019/05/23
 */
@Data
public class AlarmCallValid implements Serializable {
    @NotNull(message = "小区id不能为空")
    private Long residentialQuartersId;
}