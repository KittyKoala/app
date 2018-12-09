package com.kangyonggan.app.freemarker;

import com.kangyonggan.app.model.User;
import com.kangyonggan.app.service.MenuService;
import com.kangyonggan.app.util.SpringUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 16/4/29
 */
public class HasMenuTag extends AbstractSuperTag {

    private MenuService menuService;

    String getMenuCode(Map params) {
        return getParam(params, "code");
    }

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        User user = getUser();
        if (user != null) {
            String menuCode = getMenuCode(params);
            if (menuCode != null) {
                boolean hasMenu = getMenuService().hasMenu(user.getUserId(), menuCode);

                if (hasMenu) {
                    renderBody(env, body);
                }
            }
        }
    }

    private MenuService getMenuService() {
        if (menuService == null) {
            menuService = SpringUtils.getBean(MenuService.class);
        }
        return menuService;
    }
}
