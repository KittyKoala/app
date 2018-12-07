package com.kangyonggan.app.controller.web;

import com.kangyonggan.app.constants.AppConstants;
import com.kangyonggan.app.constants.AppSource;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.LoginLog;
import com.kangyonggan.app.model.User;
import com.kangyonggan.app.service.LoginLogService;
import com.kangyonggan.app.service.UserService;
import com.kangyonggan.app.util.Digests;
import com.kangyonggan.app.util.Encodes;
import com.kangyonggan.app.util.RedisSession;
import com.kangyonggan.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Controller
@RequestMapping("/")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginLogService loginLogService;

    /**
     * 登录界面
     *
     * @param redirectUrl
     * @param model
     * @return
     */
    @GetMapping("login")
    public String index(@RequestParam(value = "redirectUrl", required = false, defaultValue = "") String redirectUrl,
                        Model model) {
        model.addAttribute("redirectUrl", redirectUrl);
        return "web/login/index";
    }

    /**
     * 登录
     *
     * @param captcha
     * @param user
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public Response login(@RequestParam("captcha") String captcha, User user) {
        Response response = Response.getSuccessResponse();
        String realCaptcha = RedisSession.getString(AppConstants.KEY_CAPTCHA);

        // 清除验证码
        RedisSession.delete(AppConstants.KEY_CAPTCHA);

        if (!captcha.equalsIgnoreCase(realCaptcha)) {
            return response.failure("验证码错误或已失效，请重新获取");
        }

        User dbUser = userService.findUserByEmail(user.getEmail());
        if (dbUser == null) {
            return response.failure("电子邮箱不存在");
        }
        if (dbUser.getIsDeleted() == 1) {
            return response.failure("电子邮箱已被锁定");
        }

        byte[] salt = Encodes.decodeHex(dbUser.getSalt());
        byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, AppConstants.HASH_INTERATIONS);
        String password = Encodes.encodeHex(hashPassword);
        if (!dbUser.getPassword().equals(password)) {
            return response.failure("密码错误");
        }

        // 把登录信息放入redis
        String jsessionid = RedisSession.saveUser(dbUser);

        // 保存登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setJsessionid(jsessionid);
        loginLog.setUserId(dbUser.getUserId());
        loginLog.setIpAddress(getIpAddress());
        loginLog.setAppSource(AppSource.PC.getCode());
        loginLog.setEmail(dbUser.getEmail());

        loginLogService.saveLoginLog(loginLog);
        return response;
    }

    /**
     * 注销
     *
     * @return
     */
    @GetMapping("logout")
    public String logout() {
        RedisSession.removeUser();
        return "redirect:/";
    }
}
