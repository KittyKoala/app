package com.kangyonggan.app.controller.web;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.app.constants.RedisKey;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Video;
import com.kangyonggan.app.service.RedisService;
import com.kangyonggan.app.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author kangyonggan
 * @since 1/11/19
 */
@Controller
@RequestMapping("video")
public class VideoController extends BaseController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private RedisService redisService;

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

    /**
     * 视频详情界面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("{id:[\\d]+}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Video video = videoService.findVideoById(id);

        // 查看量防灌水
        String ip = getIpAddress();
        if (redisService.get(RedisKey.KEY_VIDEO_READ + video.getVideoId() + ":" + ip) == null) {
            // 有效期30分钟
            redisService.set(RedisKey.KEY_VIDEO_READ + video.getVideoId() + ":" + ip, "1", 30, TimeUnit.MINUTES);
            // 观看量加1
            video.setViewNum(video.getViewNum() + 1);
            videoService.updateVideo(video);
        }

        model.addAttribute("video", video);
        return "web/video/detail";
    }

}
