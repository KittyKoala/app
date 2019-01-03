package com.kangyonggan.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kangyonggan
 * @since 8/10/18
 */
@Controller
@RequestMapping("error")
public class ErrorController extends BaseController {

    /**
     * 权限不足
     *
     * @return
     */
    @RequestMapping("403")
    public String error403() {
        return "error/403";
    }
}
