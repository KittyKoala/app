package com.kangyonggan.app.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kangyonggan
 * @since 12/6/18
 */
@Controller
@RequestMapping("/")
public class IndexController {

    /**
     * 首页
     *
     * @return
     */
    @GetMapping
    public String index() {
        return "web/index";
    }

    /**
     * 作品
     *
     * @return
     */
    @GetMapping("works")
    public String works() {
        return "web/works";
    }

}
