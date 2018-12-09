package com.kangyonggan.app.freemarker;

import com.kangyonggan.app.model.User;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 8/10/18
 */
@Log4j2
public class GuestTag extends AbstractSuperTag {

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        User user = getUser();
        if (user == null) {
            renderBody(env, body);
        }
    }
}
