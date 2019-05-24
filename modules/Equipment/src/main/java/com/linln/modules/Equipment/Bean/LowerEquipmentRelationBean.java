package com.linln.modules.Equipment.Bean;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class LowerEquipmentRelationBean {
    /**
     * 设备id
     */
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
    /**
     * 小区id
     */
    private Long residentialQuartersId;
    /**
     * 小区名称
     */
    private String residentialQuartersName;
    /**
     * 期数id
     */
    private Long numberofperiodsId;
    /**
     * 期数名称
     */
    private String numberofperiodsName;
    /**
     * 栋数id
     */
    private Long numberOfBuildingsId;
    /**
     * 栋数名称
     */
    private String  numberOfBuildingsName;

    public LowerEquipmentRelationBean(Long id, String mac, String version, Object createDate, Byte buildType, Byte isTest, Byte isAdvertising, Long residentialQuartersId, String residentialQuartersName, Long numberofperiodsId, String numberofperiodsName, Long numberOfBuildingsId, String numberOfBuildingsName) {
        this.id = id;
        this.mac = mac;
        this.version = version;
        this.createDate = (Timestamp) createDate;
        this.buildType = buildType;
        this.isTest = isTest;
        this.isAdvertising = isAdvertising;
        this.residentialQuartersId = residentialQuartersId;
        this.residentialQuartersName = residentialQuartersName;
        this.numberofperiodsId = numberofperiodsId;
        this.numberofperiodsName = numberofperiodsName;
        this.numberOfBuildingsId = numberOfBuildingsId;
        this.numberOfBuildingsName = numberOfBuildingsName;
    }
}
