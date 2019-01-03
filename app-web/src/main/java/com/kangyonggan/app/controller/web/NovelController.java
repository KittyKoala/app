package com.kangyonggan.app.controller.web;

import com.kangyonggan.app.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 小说
 *
 * @author kangyonggan
 * @since 1/3/19
 */
@Controller
@RequestMapping("novel")
public class NovelController extends BaseController {

    /**
     * 全部小说
     *
     * @param model
     * @return
     */
    @GetMapping
    public String index(Model model) {
        return "web/novel/index";
    }

}
