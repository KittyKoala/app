package com.kangyonggan.app.service;

import com.kangyonggan.app.model.Album;
import com.kangyonggan.common.Params;

import java.util.List;

/**
 * @author kangyonggan
 * @since 1/10/19
 */
public interface AlbumService {

    /**
     * 查找所有相册
     *
     * @return
     */
    List<Album> findAllAlbums();

    /**
     * 搜索相册
     *
     * @param params
     * @return
     */
    List<Album> searchAlbums(Params params);

    /**
     * 保存相册
     *
     * @param album
     */
    void saveAlbum(Album album);

    /**
     * 获取相册
     *
     * @param albumId
     * @return
     */
    Album getAlbum(Long albumId);

    /**
     * 更新相册
     *
     * @param album
     */
    void updateAlbum(Album album);

    /**
     * 批量删除相册
     *
     * @param albumIds
     */
    void deleteAlbums(String albumIds);

    /**
     * 批量恢复相册
     *
     * @param albumIds
     */
    void restoreAlbums(String albumIds);

    /**
     * 更新照片个数
     *
     * @param albumId
     */
    void updateSize(Long albumId);

    /**
     * 查找相册
     *
     * @param albumId
     * @return
     */
    Album findAlbumByAlbumId(Long albumId);
}
