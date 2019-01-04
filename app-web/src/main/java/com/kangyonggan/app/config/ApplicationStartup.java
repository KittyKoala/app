package com.kangyonggan.app.config;

import com.kangyonggan.app.service.NovelService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Spring容器启动完成会的事件
 *
 * @author kangyonggan
 * @since 12/7/18
 */
@Log4j2
@Configuration
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private NovelService novelService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Spring容器启动完成");
        novelService.popOrCheck(true);
    }
}
