package com.kangyonggan.app.freemarker;

import com.kangyonggan.app.model.User;
import com.kangyonggan.app.service.RoleService;
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
public class HasRoleTag extends AbstractSuperTag {

    private RoleService roleService;

    String getRoleCode(Map params) {
        return getParam(params, "code");
    }

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        User user = getUser();
        if (user != null) {
            String roleCode = getRoleCode(params);
            if (roleCode != null) {
                boolean hasRole = getRoleService().hasRole(user.getUserId(), roleCode);

                if (hasRole) {
                    renderBody(env, body);
                }
            }
        }
    }

    private RoleService getRoleService() {
        if (roleService == null) {
            roleService = SpringUtils.getBean(RoleService.class);
        }
        return roleService;
    }
}
