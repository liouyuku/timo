package com.linln.modules.Equipment.Bean;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class LowerEquipmentBean {
    private Long id;
    /**
     * 设备的mac
     */
    private String mac;
    /**
     * 设备的版本
     */
    private String version;

    /**
     * 设备位于的位置
     */
    private String location;
    /**
     * 创建时间
     */
    private Timestamp createDate;
    /**
     * 绑定类型（值为1时邦定类型为栋数，2时为小区）
     */
    private Byte buildType;
    /**
     * 是否为测试机（0为真机，1为测试机）
     */
    private Byte isTest;
    /**
     * 是否为广告机（1为广告机，0为下位机）
     */
    private Byte isAdvertising;

    public LowerEquipmentBean(Long id, String mac, String version,  String location,Object createDate, Byte buildType, Byte isTest, Byte isAdvertising) {
        this.id = id;
        this.mac = mac;
        this.version = version;

        this.location = location;
        this.createDate =(Timestamp) createDate;
        this.buildType = buildType;
        this.isTest = isTest;
        this.isAdvertising = isAdvertising;
    }
}
