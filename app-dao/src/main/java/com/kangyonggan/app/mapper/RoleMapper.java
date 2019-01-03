package com.kangyonggan.app.mapper;

import com.kangyonggan.app.model.Role;
import com.kangyonggan.common.mybatis.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
public interface RoleMapper extends MyMapper<Role> {

    /**
     * 判断用户是否拥有某角色
     *
     * @param userId
     * @param roleCode
     * @return
     */
    boolean selectExistsUserRoleCode(@Param("userId") Long userId, @Param("roleCode") String roleCode);

    /**
     * 删除用户角色
     *
     * @param userId
     */
    void deleteAllRolesByUserId(Long userId);

    /**
     * 查找用户角色
     *
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Long userId);

    /**
     * 删除角色权限
     *
     * @param roleId
     */
    void deleteRoleMenus(@Param("roleId") Long roleId);

    /**
     * 批量插入角色权限
     *
     * @param roleId
     * @param menuIds
     */
    void insertRoleMenus(@Param("roleId") Long roleId, @Param("menuIds") List<String> menuIds);
}