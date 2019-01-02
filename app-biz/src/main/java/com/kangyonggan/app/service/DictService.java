package com.kangyonggan.app.service;

import com.kangyonggan.app.model.Dict;
import com.kangyonggan.common.Params;

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

    /**
     * 搜索字典
     *
     * @param params
     * @return
     */
    List<Dict> searchDicts(Params params);

    /**
     * 保存字典
     *
     * @param dict
     */
    void saveDict(Dict dict);

    /**
     * 查找字典
     *
     * @param dictId
     * @return
     */
    Dict findDictByDictId(Long dictId);

    /**
     * 更新字典
     *
     * @param dict
     */
    void updateDict(Dict dict);

    /**
     * 批量删除字典
     *
     * @param dictIds
     */
    void deleteDicts(String dictIds);

    /**
     * 批量恢复字典
     *
     * @param dictIds
     */
    void restoreDicts(String dictIds);

    /**
     * 是否存在字典
     *
     * @param dictType
     * @param dictCode
     * @return
     */
    boolean existsDict(String dictType, String dictCode);
}
