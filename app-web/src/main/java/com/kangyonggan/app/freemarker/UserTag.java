package com.kangyonggan.app.freemarker;

import com.kangyonggan.app.dto.UserDto;
import com.kangyonggan.app.model.User;
import com.kangyonggan.app.service.UserService;
import com.kangyonggan.app.util.SpringUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 8/10/18
 */
@Log4j2
public class UserTag extends AbstractSuperTag {

    private UserService userService;

    String getProperty(Map params) {
        return getParam(params, "property");
    }

    String getDefault(Map params) {
        return getParam(params, "default");
    }

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        User user = getUser();
        if (user != null) {
            log.debug("User has login. Tag body will be evaluated.");
            renderBody(env, body);

            String property = getProperty(params);
            if (property != null) {
                UserDto userDto = userService().findUserDtoByUserId(user.getUserId());
                String result = getPrincipalProperty(userDto, property);

                if (StringUtils.isEmpty(result)) {
                    String dft = getDefault(params);
                    if (dft != null) {
                        result = dft;
                    }
                }

                env.getOut().write(result);
            }
        } else {
            log.debug("User does not login. Tag body will not be evaluated.");
        }
    }

    private UserService userService() {
        if (userService == null) {
            userService = SpringUtils.getBean(UserService.class);
        }
        return userService;
    }
}
