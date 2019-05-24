package com.linln.admin.Equipment.validator;

import lombok.Data;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

/**
 * @author 小懒虫
 * @date 2019/05/08
 */
@Data
public class LowerEquipmentValid implements Serializable {
    @NotEmpty(message = "设备mac不能为空")
    private String mac;
}