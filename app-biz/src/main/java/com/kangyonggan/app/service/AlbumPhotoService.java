package com.kangyonggan.app.service;

import com.kangyonggan.app.model.AlbumPhoto;
import com.kangyonggan.common.Params;

import java.util.List;

/**
 * @author kangyonggan
 * @since 1/10/19
 */
public interface AlbumPhotoService {

    /**
     * 搜索照片
     *
     * @param params
     * @return
     */
    List<AlbumPhoto> searchAlbumPhotos(Params params);

    /**
     * 保存照片
     *
     * @param albumPhoto
     */
    void saveAlbumPhoto(AlbumPhoto albumPhoto);

    /**
     * 查找照片
     *
     * @param photoId
     * @return
     */
    AlbumPhoto getAlbumPhoto(Long photoId);

    /**
     * 更新照片
     *
     * @param albumPhoto
     */
    void updateAlbumPhoto(AlbumPhoto albumPhoto);

    /**
     * 批量删除照片
     *
     * @param photoIds
     */
    void deleteAlbumPhotos(String photoIds);

    /**
     * 批量恢复照片
     *
     * @param photoIds
     */
    void restoreAlbumPhotos(String photoIds);

    /**
     * 查找照片
     *
     * @param albumId
     * @param pageNum
     * @return
     */
    List<AlbumPhoto> findAlbumPhotos(Long albumId, int pageNum);
}
