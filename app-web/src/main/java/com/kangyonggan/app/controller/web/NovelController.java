package com.kangyonggan.app.controller.web;

import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Novel;
import com.kangyonggan.app.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 小说
 *
 * @author kangyonggan
 * @since 1/3/19
 */
@Controller
@RequestMapping("novel")
public class NovelController extends BaseController {

    @Autowired
    private NovelService novelService;

    /**
     * 全部小说
     *
     * @param model
     * @return
     */
    @GetMapping
    public String index(Model model) {
        List<Novel> novels = novelService.findAllNovels();
        model.addAttribute("novels", novels);
        return "web/novel/index";
    }

}
