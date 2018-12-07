package com.kangyonggan.app;

import com.kangyonggan.app.constants.EmailType;
import com.kangyonggan.app.service.EmailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kangyonggan
 * @since 12/6/18
 */
public class EmailServiceTest extends AbstractTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void send() {
        emailService.sendEmailCode("java2@kangyonggan.com", EmailType.REGISTER.getType(), "123456", "127.0.0.1");
    }

}
