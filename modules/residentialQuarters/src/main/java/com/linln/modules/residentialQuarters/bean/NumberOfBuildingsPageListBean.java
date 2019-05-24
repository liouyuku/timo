package com.linln.modules.residentialQuarters.bean;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class NumberOfBuildingsPageListBean {
    private Long id;
    /**
     * 栋数名称
     */
    private String name;
    /**
     * 栋数编号
     */
    private String numberOfBuildingsNumber;
    /**
     * 小区名称
     */
    private String residentialQuartersName;
    /**
     * 期数名称
     */
    private String numberofperiodsName;
    /**
     * 创建时间
     */
    private Timestamp createDate;

    public NumberOfBuildingsPageListBean(Long id, String name, String numberOfBuildingsNumber, String residentialQuartersName, String numberofperiodsName, Object createDate) {
        this.id = id;
        this.name = name;
        this.numberOfBuildingsNumber = numberOfBuildingsNumber;
        this.residentialQuartersName = residentialQuartersName;
        this.numberofperiodsName = numberofperiodsName;
        this.createDate =(Timestamp)createDate;
    }
}
