package com.kangyonggan.app.service.impl;

import com.github.ofofs.jca.annotation.Log;
import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.constants.RedisKey;
import com.kangyonggan.app.constants.YesNo;
import com.kangyonggan.app.model.Novel;
import com.kangyonggan.app.service.NovelService;
import com.kangyonggan.app.service.RedisService;
import com.kangyonggan.app.util.StringUtil;
import com.kangyonggan.common.BaseService;
import com.kangyonggan.common.Params;
import com.kangyonggan.common.Query;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kangyonggan
 * @since 1/4/19
 */
@Service
@Log4j2
public class NovelServiceImpl extends BaseService<Novel> implements NovelService {

    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Autowired
    private RedisService redisService;

    /**
     * 线程是否启动
     */
    private boolean isStarting = false;

    @Override
    public List<Novel> findAllNovels() {
        Example example = new Example(Novel.class);
        example.createCriteria().andEqualTo("isDeleted", YesNo.NO.getCode());

        example.setOrderByClause("novel_id desc");

        return myMapper.selectByExample(example);
    }

    @Override
    public List<Novel> searchNovels(Params params) {
        Example example = new Example(Novel.class);
        Query query = params.getQuery();

        Example.Criteria criteria = example.createCriteria();
        String source = query.getString("source");
        if (StringUtils.isNotEmpty(source)) {
            criteria.andEqualTo("source", source);
        }
        String code = query.getString("code");
        if (StringUtils.isNotEmpty(code)) {
            criteria.andEqualTo("code", code);
        }
        String name = query.getString("name");
        if (StringUtils.isNotEmpty(name)) {
            criteria.andLike("name", StringUtil.toLike(name));
        }
        String author = query.getString("author");
        if (StringUtils.isNotEmpty(author)) {
            criteria.andEqualTo("author", author);
        }

        String sort = params.getSort();
        String order = params.getOrder();
        if (!StringUtil.hasEmpty(sort, order)) {
            example.setOrderByClause(sort + " " + order.toUpperCase());
        } else {
            example.setOrderByClause("novel_id DESC");
        }

        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        return myMapper.selectByExample(example);
    }

    @Override
    public void saveNovel(Novel novel) {
        myMapper.insertSelective(novel);
    }

    @Override
    public Novel findNovelByNovelId(Long novelId) {
        return myMapper.selectByPrimaryKey(novelId);
    }

    @Override
    public void updateNovel(Novel novel) {
        myMapper.updateByPrimaryKeySelective(novel);
    }

    @Override
    public void deleteNovels(String novelIds) {
        if (StringUtils.isEmpty(novelIds)) {
            return;
        }
        String[] ids = novelIds.split(",");
        Example example = new Example(Novel.class);
        example.createCriteria().andIn("novelId", Arrays.asList(ids));

        Novel novel = new Novel();
        novel.setIsDeleted(YesNo.YES.getCode());

        myMapper.updateByExampleSelective(novel, example);
    }

    @Override
    public void restoreNovels(String novelIds) {
        if (StringUtils.isEmpty(novelIds)) {
            return;
        }
        String[] ids = novelIds.split(",");
        Example example = new Example(Novel.class);
        example.createCriteria().andIn("novelId", Arrays.asList(ids));

        Novel novel = new Novel();
        novel.setIsDeleted(YesNo.NO.getCode());

        myMapper.updateByExampleSelective(novel, example);
    }

    @Override
    public boolean existsNovel(String source, String code) {
        Novel novel = new Novel();
        novel.setSource(source);
        novel.setCode(code);
        return super.exists(novel);
    }

    @Override
    @Log
    public void pullNovels(String novelIds) {
        if (StringUtils.isEmpty(novelIds)) {
            return;
        }

        String[] ids = novelIds.split(",");
        for (String id : ids) {
            redisService.leftPush(RedisKey.KEY_NOVEL_PULL, Long.parseLong(id));
        }
        log.info("小说已经放入redis队列:{}", novelIds);
        popOrCheck(true);
    }

    /**
     * 如果没有线程在消费队列，则启动一个
     *
     * @param isCheck
     * @return
     */
    @Override
    public synchronized Long popOrCheck(boolean isCheck) {
        if (isCheck) {
            if (!isStarting) {
                startThread();
            }
            return null;
        } else {
            Long id = (Long) redisService.rightPop(RedisKey.KEY_NOVEL_PULL);
            if (id == null) {
                isStarting = false;
                log.info("队列中没有待更新的小说了，线程启动标识置为false");
            }

            return id;
        }
    }

    /**
     * 启动消费线程
     */
    private void startThread() {
        isStarting = true;
        executorService.execute(() -> {
            while (true) {
                try {
                    Long id = popOrCheck(false);

                    if (id == null) {
                        log.info("队列中没有待更新的小说了，关闭线程");
                        break;
                    }

                    // 更新小说
                    pullNovel(id);
                } catch (Exception e) {
                    log.error("消费线程出现异常, 继续处理下一个", e);
                }
            }
        });

        log.info("队列消费线程启动成功");
    }

    /**
     * 更新小说
     *
     * @param novelId
     */
    private void pullNovel(Long novelId) {
        Novel novel = myMapper.selectByPrimaryKey(novelId);
        if (novel == null) {
            log.info("小说{}不存在，无法更新", novelId);
            return;
        }

        log.info("小说{}更新完成", novel.getName());
    }
}
