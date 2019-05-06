/**
 * 
 */
/**
 * @author zizige
 *
 */
package com.linln.push.bean;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

@Data
public class PushBean {
	
	public static final String DEVICE_TYPE_IOS = "ios";
	
	public static final String DEVICE_TYPE_ANDORID = "andorid";
	
	/**
	 * 消息ID
	 */
	private String mMsgId;
	/***
	 * 内容
	 */
	private String alertContent;
	/***
	 * 标题
	 */
	private String title;
	/***
	 * 消息
	 */
	private String mMsg;
	/***
	 * 注册id
	 */
	private String regId;

	/***
	 * 类型
	 */
	private String mType;

	/***
	 * 电话
	 */
	private String phone;
	/***
	 * 设备id
	 */
	private String deviceId;
	/***
	 * 发送类型 0个人 1系统
	 */
	private String sendType = "0";

	/**
	 * andorid 安卓设备
	 * ios IOS设备
	 */
	private String deviceType;
	
	
	private String time = DateUtil.now();

	/***
	 * 门牌号
	 */
	private String housenum;
}