package com.kangyonggan.app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;


/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Table(name = "tb_note")
@Data
public class Note implements Serializable {
    /**
     * 视频ID
     */
    @Id
    @Column(name = "note_id")
    private Long noteId;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * IP地址
     */
    @Column(name = "ip_address")
    private String ipAddress;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否回复
     */
    @Column(name = "is_reply")
    private Byte isReply;

    /**
     * 逻辑删除
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}