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
 * @date 2019/04/30
 */
@Data
@Entity
@Table(name="residential_numberofperiods")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = StatusUtil.notDelete)
public class Numberofperiods implements Serializable {
    // 主键ID
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    // 期数编号
    private String numberOfPeriodsNumber;
    // 创建时间
    @CreatedDate
    private Date createDate;
    // 数据状态（1表示正常，2表示冻结，3表示删除）
    private Byte status = StatusEnum.OK.getCode();
    // 小区id
    private Long residentialQuartersId;


}