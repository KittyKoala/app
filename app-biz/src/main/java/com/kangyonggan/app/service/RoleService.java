package com.kangyonggan.app.service;

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
}
