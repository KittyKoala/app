package com.kangyonggan.app;

import com.kangyonggan.app.model.Menu;
import com.kangyonggan.app.service.MenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author kangyonggan
 * @since 12/10/18
 */
public class MenuServiceTest extends AbstractTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void testFindParentMenus() {
        List<Menu> menus = menuService.findParentMenusByCode("SYSTEM_USER");
        System.out.println(menus);
    }

}
