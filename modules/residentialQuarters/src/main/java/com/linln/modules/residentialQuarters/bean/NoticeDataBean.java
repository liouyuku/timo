package com.linln.modules.residentialQuarters.bean;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class NoticeDataBean {
    private Long id;
    /**
     * 通告标题
     */
    private String title;
    /**
     * 通告内容
     */
    private String content;
    /**
     * 发布通告的作者
     */
    private String author;
    /**
     * 通告的创建时间
     */
    private Timestamp createDate;
    /**
     * 通告的结束时间
     */
    private Timestamp endDate;
    /**
     * 通告的绑定类型
     */
    private Byte buildType;

    public NoticeDataBean(Long id, String title, String content, String author, Object createDate, Object endDate, Byte buildType) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createDate = (Timestamp) createDate;
        this.endDate = (Timestamp) endDate;
        this.buildType = buildType;
    }
}
