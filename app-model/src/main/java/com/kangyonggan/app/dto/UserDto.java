package com.kangyonggan.app.dto;

import com.kangyonggan.app.model.UserProfile;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kangyonggan
 * @since 8/11/18
 */
@Data
public class UserDto extends UserProfile implements Serializable {

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

}
