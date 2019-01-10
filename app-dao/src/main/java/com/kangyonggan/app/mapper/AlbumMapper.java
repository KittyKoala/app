package com.kangyonggan.app.mapper;

import com.kangyonggan.app.model.Album;
import com.kangyonggan.common.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Mapper
public interface AlbumMapper extends MyMapper<Album> {

    /**
     * 更新照片个数
     *
     * @param albumId
     */
    void updateSize(Long albumId);
}