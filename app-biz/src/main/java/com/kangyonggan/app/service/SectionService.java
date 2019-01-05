package com.kangyonggan.app.service;

import com.kangyonggan.app.model.Novel;
import com.kangyonggan.app.model.Section;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2019/1/5 0005
 */
public interface SectionService {

    /**
     * 查找最新章节
     *
     * @param novelId
     * @return
     */
    Section findLastSection(Long novelId);

    /**
     * 保存章节
     *
     * @param section
     */
    void saveSection(Section section);

    /**
     * 查找小说所有章节
     *
     * @param novelId
     * @return
     */
    List<Section> findSections(Long novelId);
}
