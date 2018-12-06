package com.kangyonggan.app.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 角色权限
 *
 * @author kangyonggan
 * @since 2018/6/3 0003
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionRole {

    /**
     * 角色代码
     *
     * @return
     */
    String[] value();
}
