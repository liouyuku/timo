package com.linln.modules.Equipment.domain;

import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.StatusUtil;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 门禁卡实体类
 */
@Data
@Entity
@Table(name="xyd_card")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = StatusUtil.notDelete)
public class Card {
    // 主键ID
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //门禁卡的序列号
    private String serialNumber;
    //门禁卡的创建时间
    private Date createDate;
    //门禁卡的结束时间
    private Date endDate;
    //门禁卡的类型（1为IC卡，2为ID卡，3为身份证）
    private  Byte type;
    //数据状态（1为数据未删除，2为数据冻结，3为数据删除）
    private Byte status = StatusEnum.OK.getCode();
}
