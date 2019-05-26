package com.linln.modules.residentialQuarters.bean;

import com.linln.common.enums.StatusEnum;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class NumberOfPeriodsPageListBean {

	/**
	 * 
	 */
	private Long id;
	/**
	 * 期数的名字
	 */
	private String name;
	/**
	 * 小区名字
	 */
	private String rName;
	/**
	 *  期数编号
	 */
	private String numberOfPeriodsNumber;
	/**
	 *  创建时间
	 */
	private Timestamp createDate;
	// 数据状态（1表示正常，2表示冻结，3表示删除）
	private Byte status = StatusEnum.OK.getCode();

	/**
	 *  小区id
	 */
	private Long residentialQuartersId;
	public NumberOfPeriodsPageListBean(Long id, String rName,String name,  String numberOfPeriodsNumber,
			Object createDate) {
		super();
		this.id = id;
		this.rName = rName;
		this.name = name;

		this.numberOfPeriodsNumber = numberOfPeriodsNumber;
		this.createDate = (Timestamp) createDate;
	}
	
	
	
	
	
	
	
	

}
