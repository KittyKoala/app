package com.kangyonggan.app.service;

import com.kangyonggan.app.model.Role;
import com.kangyonggan.common.Params;

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

    /**
     * 搜索角色
     *
     * @param params
     * @return
     */
    List<Role> searchRoles(Params params);

    /**
     * 保存角色
     *
     * @param role
     */
    void saveRole(Role role);

    /**
     * 查找角色
     *
     * @param roleId
     * @return
     */
    Role findRoleByRoleId(Long roleId);

    /**
     * 更新角色
     *
     * @param role
     */
    void updateRole(Role role);

    /**
     * 删除角色
     *
     * @param roleIds
     */
    void deleteRoles(String roleIds);

    /**
     * 恢复角色
     *
     * @param roleIds
     */
    void restoreRoles(String roleIds);

    /**
     * 判断角色代码是否存在
     *
     * @param roleCode
     * @return
     */
    boolean existsRoleCode(String roleCode);

    /**
     * 更新角色权限
     *
     * @param roleId
     * @param menuIds
     */
    void updateRoleMenus(Long roleId, String menuIds);
}
