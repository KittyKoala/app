package com.kangyonggan.app.controller.api;

import com.kangyonggan.app.service.RoleService;
import com.kangyonggan.app.service.UserService;
import com.kangyonggan.app.util.IdNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private RoleService roleService;

    /**
     * 校验电子邮箱是否可用
     *
     * @param email
     * @return
     */
    @GetMapping("email")
    @ResponseBody
    public boolean email(@RequestParam("email") String email) {
        return !userService.existsEmail(email);
    }

    /**
     * 校验身份证是否可用
     *
     * @param idNo
     * @return
     */
    @GetMapping("idNo")
    @ResponseBody
    public boolean idNo(@RequestParam("idNo") String idNo) {
        return IdNoUtil.isIdNo18(idNo);
    }

    /**
     * 校验角色代码是否可用
     *
     * @param roleCode
     * @return
     */
    @GetMapping("roleCode")
    @ResponseBody
    public boolean roleCode(@RequestParam("roleCode") String roleCode) {
        return !roleService.existsRoleCode(roleCode);
    }

}
