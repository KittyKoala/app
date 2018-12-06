package com.kangyonggan.app.controller.web;

import com.kangyonggan.app.model.LoginLog;
import com.kangyonggan.app.model.User;
import com.kangyonggan.app.service.LoginLogService;
import com.kangyonggan.app.service.UserService;
import com.kangyonggan.app.util.RedisSession;
import com.kangyonggan.common.Response;
import com.kangyonggan.common.web.BaseController;
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
     * @param user
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public Response login(User user) {
        Response response = Response.getSuccessResponse();

        // 登录
        user = userService.login(user);

        // 把登录信息放入redis
        String jsessionid = RedisSession.saveUser(user);

        // 保存登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setJsessionid(jsessionid);
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
