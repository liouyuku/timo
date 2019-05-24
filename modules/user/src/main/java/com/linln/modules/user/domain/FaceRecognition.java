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
 * @date 2019/05/16
 */
@Data
@Entity
@Table(name="xyd_faceRecognition")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = StatusUtil.notDelete)
public class FaceRecognition implements Serializable {
    // 主键ID
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    // 电话号码
    private String mobile;
    // 备注
    private String tag;
    // 创建时间
    @CreatedDate
    private Date createDate;
    // 人脸特征库
    @Lob
    @Column(columnDefinition="TEXT")
    private String data;
    // 栋数id
    private Long numberOfBuildingsId;
    // 设备类型
    private Byte equipmentType;
    // 是否审核
    private Byte isChecked;
    // 数据状态
    private Byte status = StatusEnum.OK.getCode();
}