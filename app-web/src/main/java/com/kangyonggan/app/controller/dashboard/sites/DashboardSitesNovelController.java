package com.kangyonggan.app.controller.dashboard.sites;

import com.kangyonggan.app.annotation.PermissionMenu;
import com.kangyonggan.app.annotation.Token;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Novel;
import com.kangyonggan.app.service.NovelService;
import com.kangyonggan.app.util.FileHelper;
import com.kangyonggan.app.util.FileUpload;
import com.kangyonggan.common.Page;
import com.kangyonggan.common.Response;
import org.apache.commons.fileupload.FileUploadException;
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
@RequestMapping("dashboard/sites/novel")
public class DashboardSitesNovelController extends BaseController {

    private static final String PATH_ROOT = "dashboard/sites/novel";

    @Autowired
    private NovelService novelService;

    @Autowired
    private FileHelper fileHelper;

    /**
     * 小说列表界面
     *
     * @return
     */
    @GetMapping
    @PermissionMenu("SITES_NOVEL")
    public String index() {
        return PATH_ROOT + "/list";
    }

    /**
     * 小说列表查询
     *
     * @return
     */
    @GetMapping("list")
    @PermissionMenu("SITES_NOVEL")
    @ResponseBody
    public Page<Novel> list() {
        return new Page<>(novelService.searchNovels(getRequestParams()));
    }

    /**
     * 添加小说
     *
     * @param model
     * @return
     */
    @GetMapping("create")
    @PermissionMenu("SITES_NOVEL")
    @Token(key = "createNovel")
    public String create(Model model) {
        model.addAttribute("novel", new Novel());
        return PATH_ROOT + "/form-modal";
    }

    /**
     * 保存小说
     *
     * @param novel
     * @param file
     * @return
     * @throws FileUploadException
     */
    @PostMapping("save")
    @ResponseBody
    @PermissionMenu("SITES_NOVEL")
    @Token(key = "createNovel", type = Token.Type.CHECK)
    public Response save(Novel novel, @RequestParam(value = "file", required = false) MultipartFile file) throws FileUploadException {
        if (file != null && !file.isEmpty()) {
            String cover = fileHelper.genFileName("cover");
            FileUpload.upload(fileHelper.getFileUploadPath() + "cover/", cover, file);
            novel.setCover("upload/cover/" + cover + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
        }

        novelService.saveNovel(novel);
        return Response.getSuccessResponse();
    }

    /**
     * 编辑小说
     *
     * @param novelId
     * @param model
     * @return
     */
    @GetMapping("{novelId:[\\d]+}/edit")
    @PermissionMenu("SITES_NOVEL")
    @Token(key = "editNovel")
    public String edit(@PathVariable("novelId") Long novelId, Model model) {
        model.addAttribute("novel", novelService.getNovel(novelId));
        return PATH_ROOT + "/form-modal";
    }

    /**
     * 更新小说
     *
     * @param novel
     * @param file
     * @return
     * @throws FileUploadException
     */
    @PostMapping("update")
    @ResponseBody
    @PermissionMenu("SITES_NOVEL")
    @Token(key = "editNovel", type = Token.Type.CHECK)
    public Response update(Novel novel, @RequestParam(value = "file", required = false) MultipartFile file) throws FileUploadException {
        if (file != null && !file.isEmpty()) {
            String cover = fileHelper.genFileName("cover");
            FileUpload.upload(fileHelper.getFileUploadPath() + "cover/", cover, file);
            novel.setCover("upload/cover/" + cover + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
        }
        novelService.updateNovel(novel);
        return Response.getSuccessResponse();
    }

    /**
     * 批量删除小说
     *
     * @param novelIds 小说ID
     * @return 响应
     */
    @GetMapping("delete")
    @PermissionMenu("SITES_NOVEL")
    @ResponseBody
    public Response delete(@RequestParam("novelIds") String novelIds) {
        novelService.deleteNovels(novelIds);
        return Response.getSuccessResponse();
    }

    /**
     * 批量恢复小说
     *
     * @param novelIds 小说ID
     * @return 响应
     */
    @GetMapping("restore")
    @PermissionMenu("SITES_NOVEL")
    @ResponseBody
    public Response restore(@RequestParam("novelIds") String novelIds) {
        novelService.restoreNovels(novelIds);
        return Response.getSuccessResponse();
    }

    /**
     * 批量拉取小说
     *
     * @param novelIds 小说ID
     * @return 响应
     */
    @GetMapping("pull")
    @PermissionMenu("SITES_NOVEL")
    @ResponseBody
    public Response pull(@RequestParam("novelIds") String novelIds) {
        novelService.pullNovels(novelIds);
        return Response.getSuccessResponse();
    }

}
