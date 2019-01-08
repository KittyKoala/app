package com.kangyonggan.app.controller.web;

import com.kangyonggan.app.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kangyonggan
 * @since 1/8/19
 */
@Controller
@RequestMapping("tool")
public class ToolController extends BaseController {

    private static final String PATH_ROOT = "web/tool";

    /**
     * 工具
     *
     * @return
     */
    @GetMapping
    public String index() {
        return PATH_ROOT + "/index";
    }

}
