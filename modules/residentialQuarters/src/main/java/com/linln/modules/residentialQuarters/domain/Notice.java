package com.linln.modules.residentialQuarters.domain;

import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.StatusUtil;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 小懒虫
 * @date 2019/05/10
 */
@Data
@Entity
@Table(name="residential_notice")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = StatusUtil.notDelete)
public class Notice implements Serializable {
    // 主键ID
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    // 标题
    private String title;
    // 内容
    @Lob
    @Column(columnDefinition="TEXT")
    private String content;
    // 作者(用户的id)
    private Long author;
    // 创建时间
    @CreatedDate
    private Date createDate;
    // 结束时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    // 持续时间
    private Integer durationDate;
    // 绑定类型（为1时表示绑定为小区，为2时绑定为期数，为3时表示绑定为栋数，4时表示绑定为具体的人，5时表示所有人）
    private Byte buildType;
    // 关联数据绑定为小区时值为小区的id,绑定为期数时为期数id,绑定为栋数时为栋数id,绑定为具体的人是为用户id，绑定为所有人时为-1）
    private Long relationId;
    // 是否置顶（0为不置顶，1为置顶）
    private Byte isTop;
    // 数据状态
    private Byte status = StatusEnum.OK.getCode();
}