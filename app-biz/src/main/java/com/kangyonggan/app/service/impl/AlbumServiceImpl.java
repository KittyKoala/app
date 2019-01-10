package com.kangyonggan.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.constants.YesNo;
import com.kangyonggan.app.mapper.AlbumMapper;
import com.kangyonggan.app.model.Album;
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
public class AlbumServiceImpl extends BaseService<Album> implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public List<Album> findAllAlbums() {
        Example example = new Example(Album.class);
        example.createCriteria().andEqualTo("isDeleted", YesNo.NO.getCode());

        example.setOrderByClause("sort asc");
        return myMapper.selectByExample(example);
    }

    @Override
    public List<Album> searchAlbums(Params params) {
        Example example = new Example(Album.class);
        Query query = params.getQuery();

        Example.Criteria criteria = example.createCriteria();
        String userId = query.getString("userId");
        if (StringUtils.isNotEmpty(userId)) {
            criteria.andEqualTo("userId", userId);
        }
        String albumName = query.getString("albumName");
        if (StringUtils.isNotEmpty(albumName)) {
            criteria.andLike("albumName", StringUtil.toLike(albumName));
        }

        String sort = params.getSort();
        String order = params.getOrder();
        if (!StringUtil.hasEmpty(sort, order)) {
            example.setOrderByClause(sort + " " + order.toUpperCase());
        } else {
            example.setOrderByClause("album_id DESC");
        }

        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        return myMapper.selectByExample(example);
    }

    @Override
    public void saveAlbum(Album album) {
        myMapper.insertSelective(album);
    }

    @Override
    public Album getAlbum(Long albumId) {
        return myMapper.selectByPrimaryKey(albumId);
    }

    @Override
    public void updateAlbum(Album album) {
        myMapper.updateByPrimaryKeySelective(album);
    }

    @Override
    public void deleteAlbums(String albumIds) {
        if (StringUtils.isEmpty(albumIds)) {
            return;
        }
        String[] ids = albumIds.split(",");
        Example example = new Example(Album.class);
        example.createCriteria().andIn("albumId", Arrays.asList(ids));

        Album album = new Album();
        album.setIsDeleted(YesNo.YES.getCode());

        myMapper.updateByExampleSelective(album, example);
    }

    @Override
    public void restoreAlbums(String albumIds) {
        if (StringUtils.isEmpty(albumIds)) {
            return;
        }
        String[] ids = albumIds.split(",");
        Example example = new Example(Album.class);
        example.createCriteria().andIn("albumId", Arrays.asList(ids));

        Album album = new Album();
        album.setIsDeleted(YesNo.NO.getCode());

        myMapper.updateByExampleSelective(album, example);
    }

    @Override
    public void updateSize(Long albumId) {
        albumMapper.updateSize(albumId);
    }

    @Override
    public Album findAlbumByAlbumId(Long albumId) {
        if (albumId == null) {
            return null;
        }
        Album album = new Album();
        album.setIsDeleted(YesNo.NO.getCode());
        album.setAlbumId(albumId);
        return myMapper.selectOne(album);
    }
}
