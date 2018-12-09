package com.kangyonggan.app.mapper;

import com.kangyonggan.app.model.Role;
import com.kangyonggan.common.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Mapper
public interface RoleMapper extends MyMapper<Role> {

    /**
     * 判断用户是否拥有某角色
     *
     * @param userId
     * @param roleCode
     * @return
     */
    boolean selectExistsUserRoleCode(@Param("userId") Long userId, @Param("roleCode") String roleCode);
}