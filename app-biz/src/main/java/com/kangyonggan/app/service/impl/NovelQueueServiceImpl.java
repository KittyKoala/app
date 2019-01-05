package com.kangyonggan.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.constants.NovelQueueStatus;
import com.kangyonggan.app.model.NovelQueue;
import com.kangyonggan.app.service.NovelQueueService;
import com.kangyonggan.common.BaseService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;

/**
 * @author kangyonggan
 * @since 2019/1/5 0005
 */
@Service
public class NovelQueueServiceImpl extends BaseService<NovelQueue> implements NovelQueueService {
    @Override
    public boolean exists(Long novelId) {
        Example example = new Example(NovelQueue.class);
        example.createCriteria().andEqualTo("novelId", novelId)
                .andIn("status", Arrays.asList(NovelQueueStatus.I.getCode(), NovelQueueStatus.N.getCode()));

        return myMapper.selectCountByExample(example) > 0;
    }

    @Override
    public void saveNovelQueue(long novelId) {
        NovelQueue novelQueue = new NovelQueue();
        novelQueue.setNovelId(novelId);

        myMapper.insertSelective(novelQueue);
    }

    @Override
    public NovelQueue findNextNovel() {
        Example example = new Example(NovelQueue.class);
        example.createCriteria().andIn("status", Arrays.asList(NovelQueueStatus.I.getCode(), NovelQueueStatus.N.getCode()));

        example.selectProperties("novelId", "status");
        example.setOrderByClause("status asc, queue_id asc");

        PageHelper.startPage(1, 1);
        NovelQueue novelQueue = myMapper.selectOneByExample(example);

        if (novelQueue != null && novelQueue.getStatus().equals(NovelQueueStatus.N.getCode())) {
            // 改为更新中
            NovelQueue queue = new NovelQueue();
            queue.setStatus(NovelQueueStatus.I.getCode());
            example = new Example(NovelQueue.class);
            example.createCriteria().andEqualTo("novelId", novelQueue.getNovelId()).andEqualTo("status", NovelQueueStatus.N.getCode());
            myMapper.updateByExampleSelective(queue, example);
        }

        return novelQueue;
    }

    @Override
    public void finished(Long novelId) {
        Example example = new Example(NovelQueue.class);
        example.createCriteria().andEqualTo("novelId", novelId).andEqualTo("status", NovelQueueStatus.I.getCode());

        NovelQueue queue = new NovelQueue();
        queue.setStatus(NovelQueueStatus.Y.getCode());
        myMapper.updateByExampleSelective(queue, example);
    }

    @Override
    public NovelQueue findNovelQueue(Long novelId) {
        Example example = new Example(NovelQueue.class);
        example.createCriteria().andEqualTo("novelId", novelId);
        example.setOrderByClause("queue_id desc");

        PageHelper.startPage(1, 1);
        return myMapper.selectOneByExample(example);
    }
}
