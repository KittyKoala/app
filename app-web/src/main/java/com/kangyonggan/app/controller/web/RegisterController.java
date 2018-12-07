package com.kangyonggan.app.controller.web;

import com.kangyonggan.app.constants.EmailType;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.dto.UserDto;
import com.kangyonggan.app.model.Email;
import com.kangyonggan.app.service.EmailService;
import com.kangyonggan.app.service.UserService;
import com.kangyonggan.app.util.DateUtil;
import com.kangyonggan.common.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Controller
@RequestMapping("register")
@Log4j2
public class RegisterController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Value("${email.expireTime}")
    private String expireTime;

    /**
     * 注册页面
     *
     * @return
     */
    @GetMapping
    public String index() {
        return "web/register/index";
    }

    /**
     * 注册
     *
     * @param code
     * @param userDto
     * @return
     */
    @PostMapping
    @ResponseBody
    public Response register(@RequestParam("code") String code, UserDto userDto) {
        Response response = Response.getSuccessResponse();

        Email email = emailService.findEmailByTypeAndToEmail(EmailType.REGISTER.getType(), userDto.getEmail());
        if (email == null || email.getIsDeleted() == 1 || new Date().after(DateUtil.plusMinutes(email.getCreatedTime(), Long.parseLong(expireTime)))) {
            return response.failure("验证码已失效，请重新获取");
        }

        if (!code.equalsIgnoreCase(email.getCode())) {
            return response.failure("验证码错误");
        }

        // 校验邮箱是否存在
        if (userService.existsEmail(userDto.getEmail())) {
            return response.failure("电子邮箱已被注册");
        }

        // 保存用户
        userDto.setIpAddress(getIpAddress());
        userService.saveUser(userDto);

        // 删除验证码
        emailService.deleteEmail(email);

        return response;
    }

    /**
     * 注册成功页面
     *
     * @return
     */
    @GetMapping("success")
    public String success() {
        return "web/register/success";
    }

}
