package com.kangyonggan.app.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kangyonggan
 * @since 8/12/18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Enum {

    /**
     * 键
     *
     * @return
     */
    String key() default "";

    /**
     * 代码
     *
     * @return
     */
    String code() default "code";

    /**
     * 名称
     *
     * @return
     */
    String name() default "name";


}