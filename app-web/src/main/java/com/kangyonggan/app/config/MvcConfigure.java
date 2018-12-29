package com.kangyonggan.app.config;

import com.kangyonggan.app.interceptor.AuthInterceptor;
import com.kangyonggan.app.interceptor.TokenInterceptor;
import com.kangyonggan.common.web.ParamsInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author kangyonggan
 * @since 5/15/18
 */
@Configuration
public class MvcConfigure implements WebMvcConfigurer {

    /**
     * 文件跟路径
     */
    @Value("${app.file.upload}")
    private String fileUploadPath;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 处理请求
        registry.addInterceptor(new ParamsInterceptor()).addPathPatterns("/**");
        // 防重复提交
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**");
        // 登录认证
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
    }

    /**
     * 处理上传文件的路径
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + fileUploadPath);
    }
}
