package com.linln.modules.residentialQuarters.bean;

import com.linln.common.enums.StatusEnum;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class NumberOfBuildingsBean {
    private Long id;
    private String name;
    // 栋数编号
    private String numberOfBuildingsNumber;
    // 创建时间

    private Timestamp createDate;
    // 期数id
    private Long numberOfPeriodsId;
    // 第一台梯控mac
    private String ladderControlFristMac;
    // 第二台梯控mac
    private String ladderControlSecondMac;
    // 第一台梯控备注
    private String ladderControlFristRemark;
    // 第二台梯控备注
    private String ladderControlSecondRemark;
    // 数据状态
    private Byte status = StatusEnum.OK.getCode();

    public NumberOfBuildingsBean(Long id, String name, String numberOfBuildingsNumber, Object createDate, Long numberOfPeriodsId, String ladderControlFristMac, String ladderControlSecondMac, String ladderControlFristRemark, String ladderControlSecondRemark, Byte status) {
        this.id = id;
        this.name = name;
        this.numberOfBuildingsNumber = numberOfBuildingsNumber;
        this.createDate = (Timestamp) createDate;
        this.numberOfPeriodsId = numberOfPeriodsId;
        this.ladderControlFristMac = ladderControlFristMac;
        this.ladderControlSecondMac = ladderControlSecondMac;
        this.ladderControlFristRemark = ladderControlFristRemark;
        this.ladderControlSecondRemark = ladderControlSecondRemark;
        this.status = status;
    }
}
