package com.kangyonggan.app.service.impl;

import com.kangyonggan.app.mapper.MenuMapper;
import com.kangyonggan.app.model.Menu;
import com.kangyonggan.app.service.MenuService;
import com.kangyonggan.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kangyonggan
 * @since 12/6/18
 */
@Service
public class MenuServiceImpl extends BaseService<Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public boolean hasMenu(Long userId, String menuCode) {
        return menuMapper.selectExistsUserMenuCode(userId, menuCode);
    }
}
