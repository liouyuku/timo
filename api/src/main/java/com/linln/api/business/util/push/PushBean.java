package com.linln.api.business.util.push;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

@Data
public class PushBean {
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
    private String msg;
    /***
     * 注册id
     */
    private String regId;


    /***
     * 类型
     */
    private String type;

    /***
     * 栋数id
     */
    private String util;
    /***
     * 电话
     */
    private String phone;
    /***
     * 设备id
     */
    private String deviceId;
    /***
     * 设备地址
     */
    private String address;

    /***
     * 发送类型 0个人 1系统
     */
    private String sendType = "0";
    /***
     * 管理员
     */
    private String sendUser = "admin";


    /**
     * 发送时间
     */
    private String time = DateUtil.now();
    /**
     * 消息ID
     */
    private String msgId;
    /***
     * 门牌号
     */
    private String houseNumber;

}
