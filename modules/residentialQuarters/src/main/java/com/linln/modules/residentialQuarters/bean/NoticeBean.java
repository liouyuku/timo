package com.linln.modules.residentialQuarters.bean;

import lombok.Data;

@Data
public class NoticeBean {
    private Long id;
    /**
     * 通知标题
     */
    private String title;
    /**
     * 通知内容
     */
    private String content;
    /**
     * 结束时间
     */

    private String endDate;
    /**
  持续时间
     */

    private Integer durationDate;
    /**
     * 小区id
     */
    private Long residentialQuartersId;
    /**
     * 期数id
     */
    private Long numberOfPeriodsId;
    /**
     * 栋数id
     */
    private Long numberOfBuildingsId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 绑定类型(1为所有人，2为固定人，3为小区，4为期数，5为栋数)
     */
    private Integer buildType;
    /**
     * 关联数据绑定为小区时值为小区的id,绑定为期数时为期数id,绑定为栋数时为栋数id,绑定为具体的人是为用户id，绑定为所有人时为-1）
     */
    private Long relationId;

}
