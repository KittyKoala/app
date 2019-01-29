package com.kangyonggan.app.freemarker;

import com.kangyonggan.app.constants.CategoryType;
import com.kangyonggan.app.model.Category;
import com.kangyonggan.app.service.CategoryService;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 8/10/18
 */
@Component
public class CategoryDropDirective extends AbstractSuperTag {

    @Autowired
    private CategoryService categoryService;

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        List<Category> categories = categoryService.findCategoriesByType(CategoryType.NAV_BAR.getCode());
        env.setVariable("_categories", ObjectWrapper.DEFAULT_WRAPPER.wrap(categories));
        renderBody(env, body);
    }
}
