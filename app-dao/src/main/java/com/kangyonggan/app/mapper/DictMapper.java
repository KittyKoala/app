package com.kangyonggan.app.mapper;

import com.kangyonggan.app.model.Dict;
import com.kangyonggan.common.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Mapper
public interface DictMapper extends MyMapper<Dict> {
}