package com.linln.modules.residentialQuarters.domain;

import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.StatusUtil;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table(name="residential_room")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = StatusUtil.notDelete)
public class Room {
    // 主键ID
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    /**
     * 房号
     */
    private int roomNumber;
    /**
     * 数据状态码
     */
    private Byte status= StatusEnum.OK.getCode();;
}
