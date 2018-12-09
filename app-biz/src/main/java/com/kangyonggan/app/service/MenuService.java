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
     * @param menuCode
     * @return
     */
    boolean hasMenu(Long userId, String menuCode);

    /**
     * 查找用户菜单
     *
     * @param userId
     * @return
     */
    List<Menu> findMenusByUserId(Long userId);
}
