package com.linln.modules.user.Bean;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AppUserPageBean {
    private Long id;
    //用户名
    private String userName;
    //住址
    private String location;
    //创建时间
    private Timestamp createDate;
    //电话号码
    private String mobilePhone;

    public AppUserPageBean(Long id, String userName, String location, Object createDate, String mobilePhone) {
        this.id = id;
        this.userName = userName;
        this.location = location;
        this.createDate = (Timestamp) createDate;
        this.mobilePhone = mobilePhone;
    }
}
