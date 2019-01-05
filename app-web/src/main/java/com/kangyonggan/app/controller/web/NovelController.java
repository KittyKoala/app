package com.kangyonggan.app.controller.web;

import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Novel;
import com.kangyonggan.app.model.NovelQueue;
import com.kangyonggan.app.model.Section;
import com.kangyonggan.app.service.NovelQueueService;
import com.kangyonggan.app.service.NovelService;
import com.kangyonggan.app.service.SectionService;
import com.kangyonggan.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private SectionService sectionService;

    @Autowired
    private NovelQueueService novelQueueService;

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

    /**
     * 章节列表
     *
     * @param novelId
     * @param model
     * @return
     */
    @GetMapping("{novelId:[\\d]+}")
    public String sectionList(@PathVariable("novelId") Long novelId, Model model) {
        Novel novel = novelService.findNovelByNovelId(novelId);
        List<Section> sections = sectionService.findSections(novelId);
        Section lastSection = sectionService.findLastSection(novelId);
        NovelQueue novelQueue = novelQueueService.findNovelQueue(novelId);

        model.addAttribute("novel", novel);
        model.addAttribute("sections", sections);
        model.addAttribute("lastSection", lastSection);
        model.addAttribute("novelQueue", novelQueue);
        return "web/novel/sections";
    }

    /**
     * 章节详情
     *
     * @param novelId
     * @param sectionId
     * @param model
     * @return
     */
    @GetMapping("{novelId:[\\d]+}/{sectionId:[\\d]+}")
    public String sectionDetail(@PathVariable("novelId") Long novelId, @PathVariable("sectionId") Long sectionId, Model model) {
        Novel novel = novelService.findNovelByNovelId(novelId);
        Section section = sectionService.findSection(sectionId);
        Section prevSection = sectionService.findPrevSection(novelId, sectionId);
        Section nextSection = sectionService.findNextSection(novelId, sectionId);
        if (nextSection == null) {
            // 没有下一章的时候给出更新状态
            NovelQueue novelQueue = novelQueueService.findNovelQueue(novelId);
            model.addAttribute("novelQueue", novelQueue);
        }

        model.addAttribute("novel", novel);
        model.addAttribute("section", section);
        model.addAttribute("prevSection", prevSection);
        model.addAttribute("nextSection", nextSection);
        return "web/novel/section";
    }

    /**
     * 更新小说
     *
     * @param novelId 小说ID
     * @return 响应
     */
    @GetMapping("{novelId:[\\d]+}/pull")
    @ResponseBody
    public Response pull(@PathVariable("novelId") Long novelId) {
        if (novelQueueService.exists(novelId)) {
            return Response.getFailureResponse("小说已经加入更新队列，无需重复操作");
        }
        novelService.pullNovels(String.valueOf(novelId));
        return Response.getSuccessResponse();
    }
}
