package com.kangyonggan.app.service.impl;

import com.github.ofofs.jca.annotation.Cache;
import com.github.ofofs.jca.annotation.CacheDel;
import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.constants.YesNo;
import com.kangyonggan.app.model.Dict;
import com.kangyonggan.app.service.DictService;
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
 * @since 8/11/18
 */
@Service
public class DictServiceImpl extends BaseService<Dict> implements DictService {

    @Override
    @Cache("dict:type:${dictType}")
    public List<Dict> findDictsByDictType(String dictType) {
        Example example = new Example(Dict.class);
        example.createCriteria().andEqualTo("isDeleted", YesNo.NO.getCode()).andEqualTo("dictType", dictType);

        example.selectProperties("dictCode", "value");

        example.setOrderByClause("sort asc");
        return myMapper.selectByExample(example);
    }

    @Override
    @Cache("dict:type:${dictType}:code:${dictCode}")
    public Dict findDictByDictTypeAndDictCode(String dictType, String dictCode) {
        Dict dict = new Dict();
        dict.setDictType(dictType);
        dict.setDictCode(dictCode);
        dict.setIsDeleted(YesNo.NO.getCode());

        return myMapper.selectOne(dict);
    }

    @Override
    public List<Dict> searchDicts(Params params) {
        Example example = new Example(Dict.class);
        Query query = params.getQuery();

        Example.Criteria criteria = example.createCriteria();
        String dictType = query.getString("dictType");
        if (StringUtils.isNotEmpty(dictType)) {
            criteria.andEqualTo("dictType", dictType);
        }
        String dictCode = query.getString("dictCode");
        if (StringUtils.isNotEmpty(dictCode)) {
            criteria.andEqualTo("dictCode", dictCode);
        }
        String value = query.getString("value");
        if (StringUtils.isNotEmpty(value)) {
            criteria.andEqualTo("value", value);
        }

        String sort = params.getSort();
        String order = params.getOrder();
        if (!StringUtil.hasEmpty(sort, order)) {
            example.setOrderByClause(sort + " " + order.toUpperCase());
        } else {
            example.setOrderByClause("dict_id DESC");
        }

        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        return myMapper.selectByExample(example);
    }

    @Override
    @CacheDel("dict:*")
    public void saveDict(Dict dict) {
        myMapper.insertSelective(dict);
    }

    @Override
    public Dict findDictByDictId(Long dictId) {
        return myMapper.selectByPrimaryKey(dictId);
    }

    @Override
    @CacheDel("dict:*")
    public void updateDict(Dict dict) {
        myMapper.updateByPrimaryKeySelective(dict);
    }

    @Override
    @CacheDel("dict:*")
    public void deleteDicts(String dictIds) {
        if (StringUtils.isEmpty(dictIds)) {
            return;
        }
        String[] ids = dictIds.split(",");
        Example example = new Example(Dict.class);
        example.createCriteria().andIn("dictId", Arrays.asList(ids));

        Dict dict = new Dict();
        dict.setIsDeleted(YesNo.YES.getCode());

        myMapper.updateByExampleSelective(dict, example);
    }

    @Override
    @CacheDel("dict:*")
    public void restoreDicts(String dictIds) {
        if (StringUtils.isEmpty(dictIds)) {
            return;
        }
        String[] ids = dictIds.split(",");
        Example example = new Example(Dict.class);
        example.createCriteria().andIn("dictId", Arrays.asList(ids));

        Dict dict = new Dict();
        dict.setIsDeleted(YesNo.NO.getCode());

        myMapper.updateByExampleSelective(dict, example);
    }

    @Override
    public boolean existsDict(String dictType, String dictCode) {
        Dict dict = new Dict();
        dict.setDictType(dictType);
        dict.setDictCode(dictCode);
        return super.exists(dict);
    }
}
