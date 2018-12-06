package com.kangyonggan.app.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author kangyonggan
 * @since 8/10/18
 */
@Component
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext context;

    public SpringUtils() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static void autowire(Object bean) {
        context.getAutowireCapableBeanFactory().autowireBean(bean);
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clzz) {
        return context.getBean(clzz);
    }
}
