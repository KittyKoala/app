package com.kangyonggan.app.service.impl;

import com.kangyonggan.app.mapper.MenuMapper;
import com.kangyonggan.app.model.Menu;
import com.kangyonggan.app.service.MenuService;
import com.kangyonggan.common.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author kangyonggan
 * @since 12/6/18
 */
@Service
public class MenuServiceImpl extends BaseService<Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public boolean hasMenu(Long userId, String[] menuCodes) {
        return menuMapper.selectExistsUserMenuCodes(userId, menuCodes);
    }

    @Override
    public List<Menu> findMenusByUserId(Long userId) {
        List<Menu> menus = menuMapper.selectMenusByUserId(userId);
        List<Menu> wrapList = new ArrayList<>();
        return recursionLeafList(menus, wrapList, StringUtils.EMPTY);
    }

    @Override
    public List<Menu> findParentMenusByCode(String menuCode) {
        List<Menu> menus = new ArrayList<>();
        Menu menu = new Menu();
        menu.setMenuCode(menuCode);
        menu = myMapper.selectOne(menu);
        menus.add(menu);

        while (StringUtils.isNotEmpty(menu.getParentCode())) {
            menuCode = menu.getParentCode();
            menu = new Menu();
            menu.setMenuCode(menuCode);
            menu = myMapper.selectOne(menu);
            menus.add(menu);
        }

        Collections.reverse(menus);
        return menus;
    }

    @Override
    public List<Menu> findRoleMenus(Long roleId) {
        return menuMapper.selectMenusByRoleId(roleId);
    }

    @Override
    public List<Menu> findAllMenus() {
        Example example = new Example(Menu.class);
        example.setOrderByClause("sort asc");

        List<Menu> menus = myMapper.selectByExample(example);
        List<Menu> wrapList = new ArrayList<>();

        return recursionTreeList(menus, wrapList, StringUtils.EMPTY, 0L);
    }

    @Override
    public Menu findMenuByCode(String code) {
        Menu menu = new Menu();
        menu.setMenuCode(code);

        return myMapper.selectOne(menu);
    }

    @Override
    public void saveMenu(Menu menu) {
        myMapper.insertSelective(menu);
    }

    @Override
    public void deleteMenu(Menu menu) {
        if (menu.getMenuId() == null) {
            return;
        }
        myMapper.deleteByPrimaryKey(menu.getMenuId());
    }

    @Override
    public void updateMenu(Menu menu) {
        if (menu.getMenuId() == null) {
            return;
        }

        myMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public Menu findMenuByMenuId(Long menuId) {
        return myMapper.selectByPrimaryKey(menuId);
    }

    @Override
    public boolean existsMenuCode(String menuCode) {
        Menu menu = new Menu();
        menu.setMenuCode(menuCode);

        return super.exists(menu);
    }

    /**
     * 递归构造叶子节点
     *
     * @param from
     * @param toList
     * @param parentCode
     * @return
     */
    private List<Menu> recursionLeafList(List<Menu> from, List<Menu> toList, String parentCode) {
        if (CollectionUtils.isEmpty(from)) {
            return null;
        }

        for (int i = 0; i < from.size(); i++) {
            Menu menu = from.get(i);
            if (parentCode.equals(menu.getParentCode())) {
                List<Menu> leaf = new ArrayList<>();
                menu.setLeaf(leaf);
                toList.add(menu);
                recursionLeafList(from, leaf, menu.getMenuCode());
            }
        }
        return toList;
    }

    /**
     * 递归找出 parentCode 下边的所有子节点
     *
     * @param from
     * @param toList
     * @param parentCode
     * @param pid
     * @return
     */
    private List<Menu> recursionTreeList(List<Menu> from, List<Menu> toList, String parentCode, Long pid) {
        if (CollectionUtils.isEmpty(from)) {
            return null;
        }

        for (int i = 0; i < from.size(); i++) {
            Menu menu = from.get(i);
            if (parentCode.equals(menu.getParentCode())) {
                menu.setPid(pid);
                toList.add(menu);
                recursionTreeList(from, toList, menu.getMenuCode(), menu.getMenuId());
            }
        }
        return toList;
    }
}
