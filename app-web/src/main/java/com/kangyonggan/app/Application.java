package com.kangyonggan.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author kangyonggan
 * @since 7/31/18
 */
@SpringBootApplication
@EnableTransactionManagement
@PropertySource(value = "classpath:env/${spring.profiles.active}/app.properties", encoding = "UTF-8")
@MapperScan("com.kangyonggan.app.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
