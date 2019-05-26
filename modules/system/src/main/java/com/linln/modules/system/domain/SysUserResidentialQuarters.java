package com.linln.modules.system.domain;

import com.linln.common.enums.StatusEnum;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户和小区的关联表
 */
@Data
@Entity
@Table(name = "xyd_sys_user_residential_quarters")
@EntityListeners(AuditingEntityListener.class)
public class SysUserResidentialQuarters implements Serializable {
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 小区的id
     */
    @Column(name = "residential_quarters_id")
    private Long residentialQuartersId;
    /**
     * 系统用户的id
     */
    @Column(name = "sys_user_id")
    private Long sysUserId;
    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;
    /***
     * 数据状态
     */
    private Byte status = StatusEnum.OK.getCode();

    public SysUserResidentialQuarters() {
        
    }

    public SysUserResidentialQuarters(Long id, Long residentialQuartersId, Long sysUserId, Date createDate, Byte status) {
        this.id=id;
        this.residentialQuartersId = residentialQuartersId;
        this.sysUserId = sysUserId;
        this.createDate = createDate;
        this.status = status;
    }
}
