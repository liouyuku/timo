package com.linln.modules.residentialQuarters.domain;

import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.StatusUtil;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 用户，房间，栋数对应表
 */
@Data
@Entity
@Table(name = "user_room_numberofbuildings")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = StatusUtil.notDelete)
public class UserRoomNumberOfBuildings {
    // 主键ID
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户id
     */
    private Long uId;
    /**
     * 房间ID
     */
    private Long roomId;
    /**
     * 栋数id
     */
    private Long numberOfBuildingsId;
    /**
     * 是否审核，0表示审核，1表示未审核
     */
    private Byte isChecked;
    /**
     * 0表示默认小区，1表示不是默认小区
     */
    private Byte isDefault;
    private Byte status = StatusEnum.OK.getCode();

}
