package com.kangyonggan.app.controller.api;


import com.kangyonggan.app.constants.EmailType;
import com.kangyonggan.app.constants.Regex;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Email;
import com.kangyonggan.app.service.EmailService;
import com.kangyonggan.app.service.UserService;
import com.kangyonggan.common.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author kangyonggan
 * @since 11/22/18
 */
@Controller
@RequestMapping("api/email")
@Log4j2
public class ApiEmailController extends BaseController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Value("${email.codeLength}")
    private int codeLength;

    /**
     * 发送邮箱验证码
     *
     * @param type
     * @param email
     * @return
     */
    @PostMapping("{type:[\\w]+}")
    @ResponseBody
    public Response sendSms(@PathVariable("type") String type, @RequestParam("email") String email) {
        Response response = Response.getSuccessResponse();
        log.info("发邮件入参:type={}, email={}", type, email);
        if (!email.matches(Regex.EMAIL)) {
            log.warn("请输入正确的电子邮箱");
            return response.failure("请输入正确的电子邮箱");
        }

        boolean existsEmail = userService.existsEmail(email);
        type = type.toUpperCase();
        boolean needUnique = type.equals(EmailType.REGISTER.getType()) || type.equals(EmailType.CHANGE.getType());
        if (needUnique && existsEmail) {
            log.info("电子邮箱已被占用");
            return response.failure("电子邮箱已被占用");
        }

        if (type.equals(EmailType.FORGOT.getType()) && !existsEmail) {
            log.info("电子邮箱尚未注册");
            return response.failure("电子邮箱尚未注册");
        }

        // 获取一个随机验证码
        String code = genCode(codeLength);

        // 如果上次发送的验证码还在, 继续使用上次的验证码
        Email dbEmail = emailService.findEmailByTypeAndToEmail(type, email);
        if (dbEmail != null && dbEmail.getIsDeleted() != 1) {
            code = dbEmail.getCode();
            log.info("使用上次的邮箱验证码:{}", code);
        }

        // 发送验证码
        emailService.sendEmailCode(email, type, code, getIpAddress());
        return response;
    }

}
