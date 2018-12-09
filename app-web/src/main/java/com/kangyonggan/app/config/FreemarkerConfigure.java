package com.kangyonggan.app.config;

import com.kangyonggan.app.freemarker.AppTags;
import com.kangyonggan.app.freemarker.FuncTag;
import com.kangyonggan.app.freemarker.MenusDirective;
import com.kangyonggan.freemarker.BlockDirective;
import com.kangyonggan.freemarker.ExtendsDirective;
import com.kangyonggan.freemarker.OverrideDirective;
import com.kangyonggan.freemarker.SuperDirective;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author kangyonggan
 * @since 8/6/18
 */
@Configuration
public class FreemarkerConfigure {

    @Autowired
    freemarker.template.Configuration configuration;

    @Autowired
    private MenusDirective menusDirective;

    @Value("${app.name}")
    private String appName;

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        configuration.setSharedVariable("block", new BlockDirective());
        configuration.setSharedVariable("override", new OverrideDirective());
        configuration.setSharedVariable("extends", new ExtendsDirective());
        configuration.setSharedVariable("super", new SuperDirective());

        configuration.setSharedVariable("appName", appName);
        configuration.setSharedVariable("func", new FuncTag());
        configuration.setSharedVariable("app", new AppTags());
        configuration.setSharedVariable("menus", menusDirective);
    }

}
