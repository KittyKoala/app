package com.kangyonggan.app.model;

import com.kangyonggan.app.annotation.Valid;
import com.kangyonggan.app.constants.Regex;
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
@Table(name = "tb_user")
@Data
public class User implements Serializable {
    /**
     * 用户ID
     */
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 电子邮件
     */
    @Valid(regex = Regex.EMAIL, minLengthText = "请输入正确的电子邮箱")
    private String email;

    /**
     * 密码
     */
    @Valid(required = true, requiredText = "请输入密码", regex = Regex.PASSWORD, regexText = "密码必须是8至20位的字母或数字")
    private String password;

    /**
     * 密码盐
     */
    private String salt;

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