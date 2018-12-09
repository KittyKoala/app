package com.kangyonggan.app.service.impl;

import com.github.ofofs.jca.annotation.Cache;
import com.kangyonggan.app.constants.YesNo;
import com.kangyonggan.app.model.Dict;
import com.kangyonggan.app.service.DictService;
import com.kangyonggan.common.BaseService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
}
