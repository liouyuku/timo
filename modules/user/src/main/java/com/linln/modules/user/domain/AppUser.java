package com.linln.modules.user.domain;

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
 * @date 2019/05/12
 */
@Data
@Entity
@Table(name = "xyd_AppUser")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = StatusUtil.notDelete)
public class AppUser implements Serializable {
    // 主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // 性别（0为男，1为女）
    private Byte sex;
    // 用户名
    private String userName;
    // 密码
    private String passWord;
    // 创建时间
    @CreatedDate
    private Date createDate;
    // 电话号码
    private String mobilePhone;
    // 备用电话
    private String standbyTelephone;
    // 是否在线(1为在线，0为不在线)
    private Byte isOnline;
    // 最后登录时间
    private Date lastLoginTime;
    // 用户类型（1表示业主，2表示租客，3表示家属）
    private Byte userType;
    // 业主的sn码
    private String snCode;
    //用户推送类型
    private String regKey;
    //推送类型标识（1标识华为推送，2标识小米推送，3表示极光推送，4表示其他推送）
    private Byte regKeyType;
    //操作系统（安卓，ios）
    private String osVersion;
    //app版本（创享e家版本）
    private String appVersion;
    //手机型号
    private String phoneVersion;
    // 数据状态
    private Byte status = StatusEnum.OK.getCode();

    public AppUser() {

    }

    public AppUser(String name, Byte sex, String userName, Object createDate, String mobilePhone, String standbyTelephone, Byte isOnline, Object lastLoginTime, Byte userType, String snCode, String regKey, Byte regKeyType, String osVersion, String appVersion, String phoneVersion, Byte status) {
        this.name = name;
        this.sex = sex;
        this.userName = userName;
        this.createDate = (Date) createDate;
        this.mobilePhone = mobilePhone;
        this.standbyTelephone = standbyTelephone;
        this.isOnline = isOnline;
        this.lastLoginTime = (Date) lastLoginTime;
        this.userType = userType;
        this.snCode = snCode;
        this.regKey = regKey;
        this.regKeyType = regKeyType;
        this.osVersion = osVersion;
        this.appVersion = appVersion;
        this.phoneVersion = phoneVersion;
        this.status = status;
    }
}