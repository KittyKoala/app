package com.kangyonggan.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.constants.YesNo;
import com.kangyonggan.app.model.Novel;
import com.kangyonggan.app.service.NovelService;
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
 * @since 1/4/19
 */
@Service
public class NovelServiceImpl extends BaseService<Novel> implements NovelService {

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
}
