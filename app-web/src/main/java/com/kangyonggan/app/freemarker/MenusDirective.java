package com.kangyonggan.app.freemarker;

import com.kangyonggan.app.model.Menu;
import com.kangyonggan.app.model.User;
import com.kangyonggan.app.service.MenuService;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 8/10/18
 */
@Component
public class MenusDirective extends AbstractSuperTag {

    @Autowired
    private MenuService menuService;

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        User user = getUser();
        List<Menu> menus = new ArrayList<>();
        if (user != null) {
            menus = menuService.findMenusByUserId(user.getUserId());
        }
        env.setVariable("_menus", ObjectWrapper.DEFAULT_WRAPPER.wrap(menus));
        renderBody(env, body);
    }
}
