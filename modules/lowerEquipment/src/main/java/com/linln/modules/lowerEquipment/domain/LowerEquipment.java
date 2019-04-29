package com.linln.modules.lowerEquipment.domain;

import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.StatusUtil;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mr. Zhou
 * @date 2019/04/25
 */
@Data
@Entity
@Table(name = "xyd_lowerEquipment")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = StatusUtil.notDelete)
public class LowerEquipment implements Serializable {
	// 主键ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// 绑定类型（1表示栋数，2表示小区）
	private Byte bulidtype;
	// 设备名称
	private String eqName;
	// ip地址
	private String ipaddress;
	// 是否为广告机（1为广告机，0为门禁机）
	private Byte isAdvertising;
	// 是否为测试机（0为真机，1为测试机）
	private Byte isTest;
	//
	private String playUrl;
	// 数据状态（0为未删除，1为删除）
	private Byte status = StatusEnum.OK.getCode();
	//
	private String pushUrl;
	// 设备的sn码
	private String sn;
	// 绑定为小区时为小区id，绑定类型为栋数时为栋数的id
	private String thenumberofbuildingsId;
	// 更新客户端的类型
	private String updateClient;
	// 更新时间
	private String updateTime;
	// 设备的版本号
	private String version;
	// 创建时间
	private Date createDate;
}