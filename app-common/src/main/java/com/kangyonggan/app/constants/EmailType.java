package com.kangyonggan.app.constants;

import lombok.Getter;

/**
 * 邮件类型枚举
 *
 * @author kangyonggan
 * @since 8/9/18
 */
public enum EmailType {

    /**
     * 注册
     */
    REGISTER("REGISTER"),

    /**
     * 找回密码
     */
    FORGOT("FORGOT"),

    /**
     * 换绑
     */
    CHANGE("CHANGE"),

    /**
     * 邮件通知
     */
    NOTICE("NOTICE");

    /**
     * 邮件类型
     */
    @Getter
    private final String type;

    EmailType(String type) {
        this.type = type;
    }
}