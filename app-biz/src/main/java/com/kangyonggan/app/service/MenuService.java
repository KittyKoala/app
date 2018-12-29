package com.kangyonggan.app.service;

import com.kangyonggan.app.model.Menu;

import java.util.List;

/**
 * @author kangyonggan
 * @since 12/6/18
 */
public interface MenuService {

    /**
     * 判断用户是否拥有某菜单
     *
     * @param userId
     * @param menuCodes
     * @return
     */
    boolean hasMenu(Long userId, String... menuCodes);

    /**
     * 查找用户菜单
     *
     * @param userId
     * @return
     */
    List<Menu> findMenusByUserId(Long userId);

    /**
     * 查找父菜单
     *
     * @param menuCode
     * @return
     */
    List<Menu> findParentMenusByCode(String menuCode);

    /**
     * 查找角色权限
     *
     * @param roleId
     * @return
     */
    List<Menu> findRoleMenus(Long roleId);

    /**
     * 查询所有菜单
     *
     * @return
     */
    List<Menu> findAllMenus();

    /**
     * 查找菜单
     *
     * @param code
     * @return
     */
    Menu findMenuByCode(String code);

    /**
     * 保存菜单
     *
     * @param menu
     */
    void saveMenu(Menu menu);

    /**
     * 删除菜单
     *
     * @param menu
     */
    void deleteMenu(Menu menu);

    /**
     * 更新菜单
     *
     * @param menu
     */
    void updateMenu(Menu menu);

    /**
     * 查找菜单
     *
     * @param menuId
     * @return
     */
    Menu findMenuByMenuId(Long menuId);

    /**
     * 校验菜单代码是否存在
     *
     * @param menuCode
     * @return
     */
    boolean existsMenuCode(String menuCode);
}
