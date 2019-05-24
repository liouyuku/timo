package com.linln.modules.Equipment.Bean;

import lombok.Data;

@Data
public class LowerEquipmentEditBean {
    /**
     * 设备的id
     */
    private Long id;
    /**
     * 小区的id
     */
    private Long residentialQuartersId;
    /**
     * 栋数的id
     */
    private Long numberOfBuildings;
    /**
     * 是否为广告机
     */
    private Byte isAdvertising;
    /**
     * 是否为测试机
     */
    private Byte isTest;
    /**
     * 绑定类型
     */
    private Byte bulidType;
}
