package com.kangyonggan.app.service;

import com.kangyonggan.app.model.Dict;

import java.util.List;

/**
 * @author kangyonggan
 * @since 8/11/18
 */
public interface DictService {

    /**
     * 根据字典类型查找字典
     *
     * @param dictType
     * @return
     */
    List<Dict> findDictsByDictType(String dictType);

    /**
     * 查找字典
     *
     * @param dictType
     * @param dictCode
     * @return
     */
    Dict findDictByDictTypeAndDictCode(String dictType, String dictCode);
}
