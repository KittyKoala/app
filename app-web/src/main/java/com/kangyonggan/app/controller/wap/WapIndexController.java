package com.kangyonggan.app.controller.wap;

import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.model.Novel;
import com.kangyonggan.app.model.NovelQueue;
import com.kangyonggan.app.model.Section;
import com.kangyonggan.app.service.NovelQueueService;
import com.kangyonggan.app.service.NovelService;
import com.kangyonggan.app.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2019/1/6 0006
 */
@Controller
@RequestMapping("wap")
public class WapIndexController extends BaseController {

    @Autowired
    private NovelService novelService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private NovelQueueService novelQueueService;

    /**
     * wap首页
     *
     * @return
     */
    @GetMapping
    public String index(Model model) {
        List<Novel> novels = novelService.findAllNovels();
        model.addAttribute("novels", novels);
        return "wap/index";
    }

    /**
     * 章节列表
     *
     * @param novelId
     * @param model
     * @return
     */
    @GetMapping("novel/{novelId:[\\d]+}")
    public String sectionList(@PathVariable("novelId") Long novelId, Model model) {
        Novel novel = novelService.findNovelByNovelId(novelId);
        if (novel == null) {
            return "wap/novel/none";
        }
        List<Section> sections = sectionService.findSections(novelId);
        Section lastSection = sectionService.findLastSection(novelId);
        NovelQueue novelQueue = novelQueueService.findNovelQueue(novelId);

        model.addAttribute("novel", novel);
        model.addAttribute("sections", sections);
        model.addAttribute("lastSection", lastSection);
        model.addAttribute("novelQueue", novelQueue);
        return "wap/novel/sections";
    }

    /**
     * 章节详情
     *
     * @param novelId
     * @param sectionId
     * @param model
     * @return
     */
    @GetMapping("novel/{novelId:[\\d]+}/{sectionId:[\\d]+}")
    public String sectionDetail(@PathVariable("novelId") Long novelId, @PathVariable("sectionId") Long sectionId, Model model) {
        Novel novel = novelService.findNovelByNovelId(novelId);
        if (novel == null) {
            return "wap/novel/none";
        }
        Section section = sectionService.findSection(sectionId);
        if (section == null) {
            return "wap/novel/none";
        }
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
        return "wap/novel/section";
    }
}
