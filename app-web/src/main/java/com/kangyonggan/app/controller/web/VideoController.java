package com.kangyonggan.app.controller.web;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Video;
import com.kangyonggan.app.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author kangyonggan
 * @since 1/11/19
 */
@Controller
@RequestMapping("video")
public class VideoController extends BaseController {

    @Autowired
    private VideoService videoService;

    /**
     * 视频界面
     *
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping
    public String index(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, Model model) {
        List<Video> videos = videoService.findAllVideo(pageNum);
        PageInfo page = new PageInfo<>(videos);
        model.addAttribute("page", page);
        return "web/video/index";
    }

}
