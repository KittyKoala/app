package com.kangyonggan.app.service.impl;

import com.github.ofofs.jca.annotation.Log;
import com.kangyonggan.app.constants.YesNo;
import com.kangyonggan.app.exception.BizException;
import com.kangyonggan.app.model.Email;
import com.kangyonggan.app.service.EmailService;
import com.kangyonggan.common.BaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.List;
import java.util.Properties;

/**
 * @author kangyonggan
 * @since 12/6/18
 */
@Service
@Log4j2
public class EmailServiceImpl extends BaseService<Email> implements EmailService {

    @Value("${email.host}")
    private String host;

    @Value("${email.port}")
    private String port;

    @Value("${email.username}")
    private String username;

    @Value("${email.password}")
    private String password;

    private Properties props;

    @Value("${email.codeLength}")
    private int codeLength;

    @Value("${email.username}")
    private String fromEmail;

    @Value("${app.name}")
    private String appName;

    @Value("${email.expireTime}")
    private String expireTime;

    @Override
    @Log
    public Email findEmailByTypeAndToEmail(String type, String toEmail) {
        Example example = new Example(Email.class);
        example.createCriteria().andEqualTo("toEmail", toEmail)
                .andEqualTo("type", type)
                .andEqualTo("isDeleted", YesNo.NO.getCode());

        example.setOrderByClause("email_id desc");

        List<Email> emails = myMapper.selectByExample(example);

        return emails.isEmpty() ? null : emails.get(0);
    }

    @Override
    @Log
    public void deleteEmail(Email email) {
        email.setIsDeleted(YesNo.YES.getCode());
        myMapper.updateByPrimaryKeySelective(email);
    }

    @Override
    @Log
    public void sendEmailCode(String toEmail, String type, String code, String ipAddress) {
        Email email = new Email();

        email.setType(type);
        email.setCode(code);
        email.setToEmail(toEmail);
        email.setFromEmail(fromEmail);
        email.setIpAddress(ipAddress);
        email.setContent("【" + appName + "】您本次的验证码为" + code + "，有效期" + expireTime + "分钟，此邮件仅用作注册、找回密码和邮件换绑使用！");
        email.setSubject("【" + appName + "】您的验证码为：" + code);

        try {
            sendSSL(email);

            myMapper.insertSelective(email);
        } catch (Exception e) {
            throw new BizException("邮件发送失败", e);
        }
    }

    /**
     * 加密群发
     *
     * @param email
     * @param toEmails
     * @throws Exception
     */
    private void sendSSL(Email email, String... toEmails) throws Exception {
        initProperties();
        MimeMessage msg = new MimeMessage(getSession());
        MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
        helper.setFrom(email.getFromEmail());
        helper.setTo(toEmails);
        helper.setSubject(email.getSubject());
        helper.setText(email.getContent(), false);
        Transport.send(msg);
        log.info("给{}发送邮件成功", email.getToEmail());
    }

    /**
     * 加密发送
     *
     * @param email
     * @throws Exception
     */
    private void sendSSL(Email email) throws Exception {
        initProperties();
        MimeMessage msg = new MimeMessage(getSession());
        MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
        helper.setFrom(email.getFromEmail());
        helper.setTo(email.getToEmail());
        helper.setSubject(email.getSubject());
        helper.setText(email.getContent(), false);
        Transport.send(msg);

        log.info("给{}发送邮件成功", email.getToEmail());
    }

    private void initProperties() {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        if (props == null) {
            props = new Properties();
            props.setProperty("mail.smtp.host", host);
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", port);
            props.setProperty("mail.smtp.socketFactory.port", port);
            props.put("mail.smtp.auth", "true");
        }
    }

    private Session getSession() {
        return Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
}
