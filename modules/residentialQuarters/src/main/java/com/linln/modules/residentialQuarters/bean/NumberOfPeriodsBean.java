package com.linln.modules.residentialQuarters.bean;

import com.linln.common.enums.StatusEnum;
import lombok.Data;

import java.sql.Timestamp;
@Data
public class NumberOfPeriodsBean {
    private Long id;
    private String name;
    // 期数编号
    private String numberOfPeriodsNumber;
    // 创建时间

    private Timestamp createDate;
    // 数据状态（1表示正常，2表示冻结，3表示删除）
    private Byte status = StatusEnum.OK.getCode();
    // 小区id
    private Long residentialQuartersId;

    public NumberOfPeriodsBean(Long id, String name, String numberOfPeriodsNumber, Object createDate, Byte status, Long residentialQuartersId) {
        this.id = id;
        this.name = name;
        this.numberOfPeriodsNumber = numberOfPeriodsNumber;
        this.createDate = (Timestamp) createDate;
        this.status = status;
        this.residentialQuartersId = residentialQuartersId;
    }
}
