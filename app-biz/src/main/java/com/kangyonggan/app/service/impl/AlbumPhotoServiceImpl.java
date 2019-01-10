package com.kangyonggan.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.constants.YesNo;
import com.kangyonggan.app.model.AlbumPhoto;
import com.kangyonggan.app.service.AlbumPhotoService;
import com.kangyonggan.app.service.AlbumService;
import com.kangyonggan.app.util.StringUtil;
import com.kangyonggan.common.BaseService;
import com.kangyonggan.common.Params;
import com.kangyonggan.common.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

/**
 * @author kangyonggan
 * @since 1/10/19
 */
@Service
public class AlbumPhotoServiceImpl extends BaseService<AlbumPhoto> implements AlbumPhotoService {

    @Autowired
    private AlbumService albumService;

    @Override
    public List<AlbumPhoto> searchAlbumPhotos(Params params) {
        Example example = new Example(AlbumPhoto.class);
        Query query = params.getQuery();

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("albumId", query.get("albumId"));
        String description = query.getString("description");
        if (StringUtils.isNotEmpty(description)) {
            criteria.andEqualTo("description", StringUtil.toLike(description));
        }

        String sort = params.getSort();
        String order = params.getOrder();
        if (!StringUtil.hasEmpty(sort, order)) {
            example.setOrderByClause(sort + " " + order.toUpperCase());
        } else {
            example.setOrderByClause("sort ASC");
        }

        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        return myMapper.selectByExample(example);
    }

    @Override
    public void saveAlbumPhoto(AlbumPhoto albumPhoto) {
        myMapper.insertSelective(albumPhoto);

        // 更新照片个数
        albumService.updateSize(albumPhoto.getAlbumId());
    }

    @Override
    public AlbumPhoto getAlbumPhoto(Long photoId) {
        return myMapper.selectByPrimaryKey(photoId);
    }

    @Override
    public void updateAlbumPhoto(AlbumPhoto albumPhoto) {
        myMapper.updateByPrimaryKeySelective(albumPhoto);
    }

    @Override
    public void deleteAlbumPhotos(String photoIds) {
        if (StringUtils.isEmpty(photoIds)) {
            return;
        }
        String[] ids = photoIds.split(",");
        Example example = new Example(AlbumPhoto.class);
        example.createCriteria().andIn("photoId", Arrays.asList(ids));

        AlbumPhoto albumPhoto = new AlbumPhoto();
        albumPhoto.setIsDeleted(YesNo.YES.getCode());

        myMapper.updateByExampleSelective(albumPhoto, example);

    }

    @Override
    public void restoreAlbumPhotos(String photoIds) {
        if (StringUtils.isEmpty(photoIds)) {
            return;
        }
        String[] ids = photoIds.split(",");
        Example example = new Example(AlbumPhoto.class);
        example.createCriteria().andIn("photoId", Arrays.asList(ids));

        AlbumPhoto albumPhoto = new AlbumPhoto();
        albumPhoto.setIsDeleted(YesNo.NO.getCode());

        myMapper.updateByExampleSelective(albumPhoto, example);

    }

    @Override
    public List<AlbumPhoto> findAlbumPhotos(Long albumId, int pageNum) {
        Example example = new Example(AlbumPhoto.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("albumId", albumId);
        criteria.andEqualTo("isDeleted", YesNo.NO.getCode());

        example.setOrderByClause("sort ASC, created_time DESC");

        PageHelper.startPage(pageNum, 20);
        return myMapper.selectByExample(example);
    }
}
