package com.kangyonggan.app.service;

import com.kangyonggan.app.model.Role;

import java.util.List;

/**
 * @author kangyonggan
 * @since 12/6/18
 */
public interface RoleService {

    /**
     * 判断用户是否拥有某角色
     *
     * @param userId
     * @param roleCode
     * @return
     */
    boolean hasRole(Long userId, String roleCode);

    /**
     * 查找用户角色
     *
     * @param userId
     * @return
     */
    List<Role> findRolesByUserId(Long userId);

    /**
     * 查找所有角色
     *
     * @return
     */
    List<Role> findAllRoles();

    /**
     * 删除用户角色
     *
     * @param userId
     */
    void deleteAllRolesByUserId(Long userId);
}
