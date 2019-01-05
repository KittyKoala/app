package com.kangyonggan.app.mapper;

import com.kangyonggan.app.model.Record;
import com.kangyonggan.common.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Mapper
public interface RecordMapper extends MyMapper<Record> {
}