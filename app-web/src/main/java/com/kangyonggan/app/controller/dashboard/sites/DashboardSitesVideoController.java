package com.kangyonggan.app.controller.dashboard.sites;

import com.kangyonggan.app.annotation.PermissionMenu;
import com.kangyonggan.app.annotation.Token;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Video;
import com.kangyonggan.app.service.VideoService;
import com.kangyonggan.app.util.FileHelper;
import com.kangyonggan.app.util.FileUpload;
import com.kangyonggan.common.Page;
import com.kangyonggan.common.Response;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author kangyonggan
 * @since 1/4/19
 */
@Controller
@RequestMapping("dashboard/sites/video")
public class DashboardSitesVideoController extends BaseController {

    private static final String PATH_ROOT = "dashboard/sites/video";

    @Autowired
    private VideoService videoService;

    @Autowired
    private FileHelper fileHelper;

    /**
     * 视频列表界面
     *
     * @return
     */
    @GetMapping
    @PermissionMenu("SITES_VIDEO")
    public String index() {
        return PATH_ROOT + "/list";
    }

    /**
     * 视频列表查询
     *
     * @return
     */
    @GetMapping("list")
    @PermissionMenu("SITES_VIDEO")
    @ResponseBody
    public Page<Video> list() {
        return new Page<>(videoService.searchVideos(getRequestParams()));
    }

    /**
     * 添加视频
     *
     * @param model
     * @return
     */
    @GetMapping("create")
    @PermissionMenu("SITES_VIDEO")
    @Token(key = "createVideo")
    public String create(Model model) {
        model.addAttribute("video", new Video());
        return PATH_ROOT + "/form-modal";
    }

    /**
     * 保存视频
     *
     * @param video
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("save")
    @ResponseBody
    @PermissionMenu("SITES_VIDEO")
    @Token(key = "createVideo", type = Token.Type.CHECK)
    public Response save(Video video, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        upload(video, file);

        // 允许全屏
        video.setContent(video.getContent().replace("'allowfullscreen'", "allowfullscreen"));
        // http转https
        video.setContent(video.getContent().replace("http://", "https://"));

        video.setUserId(currentUserId());
        videoService.saveVideo(video);
        return Response.getSuccessResponse();
    }

    /**
     * 编辑视频
     *
     * @param videoId
     * @param model
     * @return
     */
    @GetMapping("{videoId:[\\d]+}/edit")
    @PermissionMenu("SITES_VIDEO")
    @Token(key = "editVideo")
    public String edit(@PathVariable("videoId") Long videoId, Model model) {
        model.addAttribute("video", videoService.getVideo(videoId));
        return PATH_ROOT + "/form-modal";
    }

    /**
     * 更新视频
     *
     * @param video
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("update")
    @ResponseBody
    @PermissionMenu("SITES_VIDEO")
    @Token(key = "editVideo", type = Token.Type.CHECK)
    public Response update(Video video, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        upload(video, file);

        // 允许全屏
        video.setContent(video.getContent().replace("'allowfullscreen'", "allowfullscreen"));
        // http转https
        video.setContent(video.getContent().replace("http://", "https://"));

        videoService.updateVideo(video);
        return Response.getSuccessResponse();
    }

    /**
     * 批量删除视频
     *
     * @param videoIds 视频ID
     * @return 响应
     */
    @GetMapping("delete")
    @PermissionMenu("SITES_VIDEO")
    @ResponseBody
    public Response delete(@RequestParam("videoIds") String videoIds) {
        videoService.deleteVideos(videoIds);
        return Response.getSuccessResponse();
    }

    /**
     * 批量恢复视频
     *
     * @param videoIds 视频ID
     * @return 响应
     */
    @GetMapping("restore")
    @PermissionMenu("SITES_VIDEO")
    @ResponseBody
    public Response restore(@RequestParam("videoIds") String videoIds) {
        videoService.restoreVideos(videoIds);
        return Response.getSuccessResponse();
    }

    /**
     * 文件上传
     *
     * @param video
     * @param file
     * @throws Exception
     */
    private void upload(Video video, MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            return;
        }
        String fileName = fileHelper.genFileName("video");
        FileUpload.upload(fileHelper.getFileUploadPath() + "video/", fileName, file);
        video.setCover("upload/video/" + fileName + FilenameUtils.getExtension(file.getOriginalFilename()));
    }
}
