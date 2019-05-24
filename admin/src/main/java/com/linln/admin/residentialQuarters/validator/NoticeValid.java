package com.linln.admin.residentialQuarters.validator;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 小懒虫
 * @date 2019/05/10
 */
@Data
public class NoticeValid implements Serializable {
    @NotEmpty(message = "标题不能为空")
    private String title;
    @NotNull(message = "内容不能为空")
    private String content;
    @NotNull(message = "持续时间不能为空")
    @Digits(integer = 12, fraction = 2, message = "持续时间不是数字")
    private Integer durationDate;
}