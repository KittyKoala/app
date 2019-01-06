package com.kangyonggan.app.service;


import com.kangyonggan.app.model.Record;
import com.kangyonggan.common.Params;

import java.util.List;

/**
 * @author kangyonggan
 * @since 10/3/18
 */
public interface RecordService {
    /**
     * 保存记录
     *
     * @param record
     */
    void saveRecord(Record record);

    /**
     * 查找记录
     *
     * @param openid
     * @param pageNum
     * @return
     */
    List<Record> findRecords(String openid, int pageNum);

    /**
     * 查找记录详情
     *
     * @param id
     * @return
     */
    Record findRecordById(Long id);

    /**
     * 搜索记录
     *
     * @param params
     * @return
     */
    List<Record> searchRecords(Params params);

    /**
     * 更新记录
     *
     * @param record
     */
    void updateRecord(Record record);

    /**
     * 批量删除记录
     *
     * @param ids
     */
    void deleteRecords(String ids);

    /**
     * 批量恢复记录
     *
     * @param ids
     */
    void restoreRecords(String ids);
}
