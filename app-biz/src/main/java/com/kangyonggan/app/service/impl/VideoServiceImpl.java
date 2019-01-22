package com.kangyonggan.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.constants.YesNo;
import com.kangyonggan.app.model.Video;
import com.kangyonggan.app.service.VideoService;
import com.kangyonggan.app.util.StringUtil;
import com.kangyonggan.common.BaseService;
import com.kangyonggan.common.Params;
import com.kangyonggan.common.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

/**
 * @author kangyonggan
 * @since 1/11/19
 */
@Service
public class VideoServiceImpl extends BaseService<Video> implements VideoService {

    @Override
    public List<Video> searchVideos(Params params) {
        Example example = new Example(Video.class);
        Query query = params.getQuery();

        Example.Criteria criteria = example.createCriteria();
        String userId = query.getString("userId");
        if (StringUtils.isNotEmpty(userId)) {
            criteria.andEqualTo("userId", userId);
        }
        String title = query.getString("title");
        if (StringUtils.isNotEmpty(title)) {
            criteria.andLike("title", StringUtil.toLike(title));
        }

        String sort = params.getSort();
        String order = params.getOrder();
        if (!StringUtil.hasEmpty(sort, order)) {
            example.setOrderByClause(sort + " " + order.toUpperCase());
        } else {
            example.setOrderByClause("video_id DESC");
        }

        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        return myMapper.selectByExample(example);
    }

    @Override
    public void saveVideo(Video video) {
        myMapper.insertSelective(video);
    }

    @Override
    public Video getVideo(Long videoId) {
        return myMapper.selectByPrimaryKey(videoId);
    }

    @Override
    public void updateVideo(Video video) {
        myMapper.updateByPrimaryKeySelective(video);
    }

    @Override
    public void deleteVideos(String videoIds) {
        if (StringUtils.isEmpty(videoIds)) {
            return;
        }
        String[] ids = videoIds.split(",");
        Example example = new Example(Video.class);
        example.createCriteria().andIn("videoId", Arrays.asList(ids));

        Video video = new Video();
        video.setIsDeleted(YesNo.YES.getCode());

        myMapper.updateByExampleSelective(video, example);
    }

    @Override
    public void restoreVideos(String videoIds) {
        if (StringUtils.isEmpty(videoIds)) {
            return;
        }
        String[] ids = videoIds.split(",");
        Example example = new Example(Video.class);
        example.createCriteria().andIn("videoId", Arrays.asList(ids));

        Video video = new Video();
        video.setIsDeleted(YesNo.NO.getCode());

        myMapper.updateByExampleSelective(video, example);
    }

    @Override
    public List<Video> findAllVideo(int pageNum) {
        Example example = new Example(Video.class);
        example.createCriteria().andEqualTo("isDeleted", YesNo.NO.getCode());

        example.setOrderByClause("video_id DESC");

        PageHelper.startPage(pageNum, 6);
        return myMapper.selectByExample(example);
    }

    @Override
    public Video findVideoById(Long id) {
        Video video = new Video();
        video.setVideoId(id);
        video.setIsDeleted(YesNo.NO.getCode());
        return myMapper.selectOne(video);
    }
}
