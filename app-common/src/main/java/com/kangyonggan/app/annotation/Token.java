package com.kangyonggan.app.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防重复提交的令牌
 *
 * @author kangyonggan
 * @since 5/4/18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Token {

    /**
     * 令牌的key
     *
     * @return
     */
    String key();

    /**
     * 令牌的操作类型
     *
     * @return
     */
    Type type() default Type.GENERATE;

    enum Type {
        /**
         * 生成令牌
         */
        GENERATE,
        /**
         * 校验令牌
         */
        CHECK
    }
}

