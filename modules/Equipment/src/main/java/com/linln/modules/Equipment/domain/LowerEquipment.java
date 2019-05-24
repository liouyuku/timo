package com.linln.modules.Equipment.domain;

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
 * @date 2019/05/08
 */
@Data
@Entity
@Table(name="equipment_LowerEquipment")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = StatusUtil.notDelete)
public class LowerEquipment implements Serializable {
    // 主键ID
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    // 设备mac
    private String mac;
    // 设备版本号
    private String version;
    //设备的名称
    private String name;
    // 栋数id(绑定类型为小区时值为小区的id,绑定类型为栋数时值为栋数的id)
    private Long numberOfBuildingsId;
    // 创建时间
    @CreatedDate
    private Date createDate;
    // 绑定类型(值为1时邦定类型为栋数，2时为小区)
    private Byte bulidType;
    // 设备sn码
    private String sn;
    // 是否是测试机（0为正式机，1为测试机）
    private Byte isTest;
    // 是否为广告机（0为下位机，1为广告机）
    private Byte isAdvertising;
    // 数据状态（1为正常，2为冻结，3为删除，4为未绑定）
    private Byte status = StatusEnum.OK.getCode();
}