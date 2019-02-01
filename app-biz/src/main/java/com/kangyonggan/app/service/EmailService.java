package com.kangyonggan.app.service;

import com.kangyonggan.app.model.Email;

/**
 * @author kangyonggan
 * @since 12/6/18
 */
public interface EmailService {
    /**
     * 查找邮件
     *
     * @param type
     * @param toEmail
     * @return
     */
    Email findEmailByTypeAndToEmail(String type, String toEmail);

    /**
     * 删除邮件
     *
     * @param email
     */
    void deleteEmail(Email email);

    /**
     * 发送验证码邮件
     *
     * @param toEmail
     * @param type
     * @param code
     * @param ipAddress
     */
    void sendEmailCode(String toEmail, String type, String code, String ipAddress);

    /**
     * 发送邮件
     *
     * @param toEmail
     * @param content
     * @param ipAddress
     */
    void sendEmail(String toEmail, String content, String ipAddress);
}
