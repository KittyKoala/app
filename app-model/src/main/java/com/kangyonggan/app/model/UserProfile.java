package com.kangyonggan.app.model;

import com.kangyonggan.app.annotation.Valid;
import com.kangyonggan.app.constants.Regex;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Table(name = "tb_user_profile")
@Data
public class UserProfile implements Serializable {
    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 姓名
     */
    @Valid(maxLength = 20, maxLengthText = "姓名最多20位")
    private String name;

    /**
     * 证件类型
     */
    @Column(name = "id_type")
    private String idType;

    /**
     * 证件号码
     */
    @Column(name = "id_no")
    @Valid(regex = Regex.ID_NO, minLengthText = "请输入正确的18位身份证号码")
    private String idNo;

    /**
     * IP地址
     */
    @Column(name = "ip_address")
    private String ipAddress;

    /**
     * 皮肤
     */
    private String skin;

    /**
     * 是否启用邮件通知
     */
    @Column(name = "enable_email_notice")
    private Byte enableEmailNotice;

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