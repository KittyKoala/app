package com.kangyonggan.app.service;

import com.kangyonggan.app.model.Novel;
import com.kangyonggan.common.Params;

import java.util.List;

/**
 * @author kangyonggan
 * @since 1/4/19
 */
public interface NovelService {

    /**
     * 查找全部小说
     *
     * @return
     */
    List<Novel> findAllNovels();

    /**
     * 搜索小说
     *
     * @param params
     * @return
     */
    List<Novel> searchNovels(Params params);

    /**
     * 保存小说
     *
     * @param novel
     */
    void saveNovel(Novel novel);

    /**
     * 查找小说
     *
     * @param novelId
     * @return
     */
    Novel findNovelByNovelId(Long novelId);

    /**
     * 更新小说
     *
     * @param novel
     */
    void updateNovel(Novel novel);

    /**
     * 批量删除小说
     *
     * @param novelIds
     */
    void deleteNovels(String novelIds);

    /**
     * 批量恢复小说
     *
     * @param novelIds
     */
    void restoreNovels(String novelIds);

    /**
     * 判断小说是否存在
     *
     * @param source
     * @param code
     * @return
     */
    boolean existsNovel(String source, String code);
}
