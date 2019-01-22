package com.kangyonggan.app.service;

import com.kangyonggan.app.model.Video;
import com.kangyonggan.common.Params;

import java.util.List;

/**
 * @author kangyonggan
 * @since 1/11/19
 */
public interface VideoService {

    /**
     * 搜索视频
     *
     * @param params
     * @return
     */
    List<Video> searchVideos(Params params);

    /**
     * 保存视频
     *
     * @param video
     */
    void saveVideo(Video video);

    /**
     * 获取视频
     *
     * @param videoId
     * @return
     */
    Video getVideo(Long videoId);

    /**
     * 更新视频
     *
     * @param video
     */
    void updateVideo(Video video);

    /**
     * 批量删除视频
     *
     * @param videoIds
     */
    void deleteVideos(String videoIds);

    /**
     * 批量恢复视频
     *
     * @param videoIds
     */
    void restoreVideos(String videoIds);

    /**
     * 查找视频
     *
     * @param pageNum
     * @return
     */
    List<Video> findAllVideo(int pageNum);

    /**
     * 查找视频
     *
     * @param id
     * @return
     */
    Video findVideoById(Long id);
}
