package com.linln.modules.user.Bean;

import lombok.Data;

@Data
public class AppUserBean {
    //用户名字
    private String name;
    //用户名
    private String userName;
    //电话号码
    private String mobilePhone;
    //备用电话
    private String standbyTelephone;
    //门禁卡的序列号
    private String serialNumber;
    //门禁卡类型（IC卡为1，ID卡为2，身份证为3）
    private Byte cardType;
    //门禁卡失效实现
    private String endDate;
    //期数ID
    private Long numberOfPeriodsId;
    //栋数id
    private Long numberOfBuildingsId;
    //房间号
    private String roomNumber;
    //用户类型
    private Byte userType;


}
