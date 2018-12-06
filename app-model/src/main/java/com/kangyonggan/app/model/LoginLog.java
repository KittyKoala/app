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
@Table(name = "tb_login_log")
@Data
public class LoginLog implements Serializable {
    /**
     * 登录ID
     */
    @Id
    @Column(name = "login_id")
    private Long loginId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 登录IP
     */
    @Column(name = "ip_address")
    private String ipAddress;

    /**
     * 应用来源
     */
    @Column(name = "app_source")
    private String appSource;

    /**
     * jsessionid
     */
    private String jsessionid;

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