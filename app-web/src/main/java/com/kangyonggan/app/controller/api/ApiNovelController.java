package com.kangyonggan.app.controller.api;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Novel;
import com.kangyonggan.app.model.Section;
import com.kangyonggan.app.service.NovelQueueService;
import com.kangyonggan.app.service.NovelService;
import com.kangyonggan.app.service.SectionService;
import com.kangyonggan.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2019/1/5 0005
 */
@RestController
@RequestMapping("api/novel")
public class ApiNovelController extends BaseController {

    @Autowired
    private NovelService novelService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private NovelQueueService novelQueueService;

    /**
     * 小说列表
     *
     * @return
     */
    @GetMapping
    public Response list() {
        Response response = Response.getSuccessResponse();
        List<Novel> novels = novelService.findAllNovels();

        response.put("novels", novels);
        response.put("debug", false);
        return response;
    }

    /**
     * 章节列表
     *
     * @param novelId
     * @param pageNum
     * @return
     */
    @GetMapping("{novelId:[\\d]+}")
    public Response list(@PathVariable("novelId") Long novelId,
                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        Response response = Response.getSuccessResponse();
        Novel novel = novelService.findNovelByNovelId(novelId);
        List<Section> sections = sectionService.findSectionsByPage(novelId, pageNum);
        PageInfo<Section> pageInfo = new PageInfo<>(sections);

        response.put("pageInfo", pageInfo);
        response.put("novelName", novel.getName());
        return response;
    }

    /**
     * 章节详情
     *
     * @param sectionId
     * @return
     */
    @GetMapping("section/{sectionId:[\\d]+}")
    public Response sectionDetail(@PathVariable("sectionId") Long sectionId, Model model) {
        Response response = Response.getSuccessResponse();

        Section section = sectionService.findSection(sectionId);
        Section prevSection = sectionService.findPrevSection(section.getNovelId(), sectionId);
        Section nextSection = sectionService.findNextSection(section.getNovelId(), sectionId);

        response.put("section", section);
        response.put("prevSection", prevSection);
        response.put("nextSection", nextSection);
        return response;
    }

    /**
     * 更新小说
     *
     * @param novelId 小说ID
     * @return 响应
     */
    @GetMapping("{novelId:[\\d]+}/pull")
    public Response pull(@PathVariable("novelId") Long novelId) {
        if (novelQueueService.exists(novelId)) {
            return Response.getFailureResponse("小说已经加入更新队列，无需重复操作");
        }
        novelService.pullNovels(String.valueOf(novelId));
        return Response.getSuccessResponse();
    }
}
