package com.kangyonggan.app.controller.api;

import com.kangyonggan.app.service.UserService;
import com.kangyonggan.app.util.IdNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kangyonggan
 * @since 8/9/18
 */
@Controller
@RequestMapping("api/validate")
public class ApiValidateController {

    @Autowired
    private UserService userService;

    /**
     * 校验电子邮箱是否可用
     *
     * @param email
     * @param oldEmail
     * @return
     */
    @PostMapping("email")
    @ResponseBody
    public boolean email(@RequestParam("email") String email, @RequestParam(value = "oldEmail", required = false, defaultValue = "") String oldEmail) {
        if (oldEmail.equals(email)) {
            return true;
        }

        return !userService.existsEmail(email);
    }

    /**
     * 校验身份证是否可用
     *
     * @param idNo
     * @return
     */
    @PostMapping("idNo")
    @ResponseBody
    public boolean idNo(@RequestParam("idNo") String idNo) {
        return IdNoUtil.isIdNo18(idNo);
    }

}
