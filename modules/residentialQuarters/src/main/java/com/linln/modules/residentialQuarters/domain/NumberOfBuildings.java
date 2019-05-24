package com.linln.modules.residentialQuarters.domain;

import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.StatusUtil;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 小懒虫
 * @date 2019/05/07
 */
@Data
@Entity
@Table(name="residential_numberOfBuildings")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = StatusUtil.notDelete)
public class NumberOfBuildings implements Serializable {
    // 主键ID
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    // 栋数编号
    private String numberOfBuildingsNumber;
    // 创建时间
    @CreatedDate
    private Date createDate;
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
    public NumberOfBuildings() {

    }
    public NumberOfBuildings(String name, String numberOfBuildingsNumber) {
        this.name = name;
        this.numberOfBuildingsNumber = numberOfBuildingsNumber;
    }

    public NumberOfBuildings(String name, String numberOfBuildingsNumber, Date createDate, Long numberOfPeriodsId, String ladderControlFristMac, String ladderControlSecondMac, String ladderControlFristRemark, String ladderControlSecondRemark, Byte status) {
        this.name = name;
        this.numberOfBuildingsNumber = numberOfBuildingsNumber;
        this.createDate = createDate;
        this.numberOfPeriodsId = numberOfPeriodsId;
        this.ladderControlFristMac = ladderControlFristMac;
        this.ladderControlSecondMac = ladderControlSecondMac;
        this.ladderControlFristRemark = ladderControlFristRemark;
        this.ladderControlSecondRemark = ladderControlSecondRemark;
        this.status = status;
    }
}