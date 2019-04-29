package com.linln.modules.numberPeriods.domain;

import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.StatusUtil;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mr. Zhou
 * @date 2019/04/27
 */
@Data
@Entity
@Table(name="residential_numberPeriods")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = StatusUtil.notDelete)
public class NumberPeriods implements Serializable {
    // 主键ID
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    // 小区id
    private Long residentialQuartersId;
    // 创建时间
    @CreatedDate
    private Date createDate;
    // 期数编号
    private String numberOfPeriodsNumber;
    // 数据状态
    private Byte status = StatusEnum.OK.getCode();
}