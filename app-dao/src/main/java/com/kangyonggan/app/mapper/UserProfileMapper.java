package com.kangyonggan.app.mapper;

import com.kangyonggan.app.model.UserProfile;
import com.kangyonggan.common.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Mapper
public interface UserProfileMapper extends MyMapper<UserProfile> {
}