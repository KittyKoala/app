package com.kangyonggan.app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Table(name = "tb_email")
@Data
public class Email implements Serializable {
    /**
     * 邮件ID
     */
    @Id
    @Column(name = "email_id")
    private Long emailId;

    /**
     * 标题
     */
    private String subject;

    /**
     * 发送方
     */
    @Column(name = "from_email")
    private String fromEmail;

    /**
     * 接收方
     */
    @Column(name = "to_email")
    private String toEmail;

    /**
     * 邮件类型
     */
    private String type;

    /**
     * IP地址
     */
    @Column(name = "ip_address")
    private String ipAddress;

    /**
     * 验证码
     */
    private String code;

    /**
     * 邮件内容
     */
    private String content;

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