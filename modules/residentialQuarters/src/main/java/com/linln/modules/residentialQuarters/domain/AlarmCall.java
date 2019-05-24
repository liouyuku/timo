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
 * @date 2019/05/23
 */
@Data
@Entity
@Table(name="xyd_AlarmCall")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = StatusUtil.notDelete)
public class AlarmCall implements Serializable {
    // 主键ID
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    // 小区id
    private Long residentialQuartersId;
    // 电话号码
    private String telePhone;
    // 创建时间
    @CreatedDate
    private Date createDate;
    // 数据状态
    private Byte status = StatusEnum.OK.getCode();
}