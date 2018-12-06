package com.kangyonggan.app.constants;

/**
 * @author kangyonggan
 * @since 8/9/18
 */
public interface Regex {

    /**
     * 密码的正则表达式
     */
    String PASSWORD = "^[a-zA-Z0-9]{8,20}$";

    /**
     * 电子邮箱的正则表达式
     */
    String EMAIL = "^[\\.a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    /**
     * 身份证的正则表达式
     */
    String ID_NO = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";

}
