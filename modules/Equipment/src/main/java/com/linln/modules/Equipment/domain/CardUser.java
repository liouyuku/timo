package com.linln.modules.Equipment.domain;

import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.StatusUtil;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 门禁卡用户关联的实体类
 */
@Data
@Entity
@Table(name="xyd_card_user")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = StatusUtil.notDelete)
public class CardUser {
    // 主键ID
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户ID
     */
    private Long uid;
    //门禁卡的id
    private Long cid;
    //创建时间
    private Date createDate;
    //数据状态（1为数据未删除，2为数据冻结，3为数据删除）
    private Byte status = StatusEnum.OK.getCode();

}
