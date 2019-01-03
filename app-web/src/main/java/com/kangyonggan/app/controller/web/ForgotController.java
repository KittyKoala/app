package com.kangyonggan.app.controller.web;

import com.kangyonggan.app.constants.EmailType;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.dto.UserDto;
import com.kangyonggan.app.model.Email;
import com.kangyonggan.app.model.User;
import com.kangyonggan.app.service.EmailService;
import com.kangyonggan.app.service.UserService;
import com.kangyonggan.app.util.DateUtil;
import com.kangyonggan.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 忘记密码
 *
 * @author kangyonggan
 * @since 1/3/19
 */
@Controller
@RequestMapping("forgot")
public class ForgotController extends BaseController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Value("${email.expireTime}")
    private String expireTime;

    /**
     * 忘记密码页面
     *
     * @return
     */
    @GetMapping
    public String index() {
        return "web/forgot/index";
    }

    /**
     * 找回密码
     *
     * @param code
     * @param userDto
     * @return
     */
    @PostMapping
    @ResponseBody
    public Response register(@RequestParam("code") String code, UserDto userDto) {
        Response response = Response.getSuccessResponse();

        Email email = emailService.findEmailByTypeAndToEmail(EmailType.FORGOT.getType(), userDto.getEmail());
        if (email == null || email.getIsDeleted() == 1 || new Date().after(DateUtil.plusMinutes(email.getCreatedTime(), Long.parseLong(expireTime)))) {
            return response.failure("验证码已失效，请重新获取");
        }

        if (!code.equalsIgnoreCase(email.getCode())) {
            return response.failure("验证码错误");
        }

        // 校验邮箱是否存在
        User user = userService.findUserByEmail(userDto.getEmail());
        if (user == null) {
            return response.failure("电子邮箱尚未注册");
        }

        // 更新密码
        User u = new User();
        u.setUserId(user.getUserId());
        u.setPassword(userDto.getPassword());
        userService.updateUserPassword(u);

        // 删除验证码
        emailService.deleteEmail(email);

        return response;
    }

    /**
     * 找回密码成功页面
     *
     * @return
     */
    @GetMapping("success")
    public String success() {
        return "web/forgot/success";
    }

}
