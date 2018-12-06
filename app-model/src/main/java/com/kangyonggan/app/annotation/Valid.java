package com.kangyonggan.app.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段校验
 *
 * @author kangyonggan
 * @since 8/9/18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Valid {

    /**
     * 是否为必填项
     *
     * @return
     */
    boolean required() default false;

    /**
     * 为空时的提示语。作用于所有支持类型的字段
     *
     * @return
     */
    String requiredText() default "";

    /**
     * 长度。仅作用于String类型的字段，大于0时才生效
     *
     * @return
     */
    int length() default -1;

    /**
     * 不等于长度提示语。仅作用于String类型的字段
     *
     * @return
     */
    String lengthText() default "";

    /**
     * 最小长度。仅作用于String类型的字段
     *
     * @return
     */
    int minLength() default -1;

    /**
     * 小于最小长度提示语。仅作用于String类型的字段
     *
     * @return
     */
    String minLengthText() default "";

    /**
     * 最大长度。仅作用于String类型的字段
     *
     * @return
     */
    int maxLength() default Integer.MAX_VALUE;

    /**
     * 大于最大长度提示语。仅作用于String类型的字段
     *
     * @return
     */
    String maxLengthText() default "";

    /**
     * 正则表达式。仅作用于String类型的字段
     *
     * @return
     */
    String regex() default "";

    /**
     * 不符合正则表达式提示语。仅作用于String类型的字段
     *
     * @return
     */
    String regexText() default "";

    /**
     * 最小值。仅作用于int和long型的字段。
     *
     * @return
     */
    long min() default Long.MIN_VALUE;

    /**
     * 小于最小值的提示语。仅作用于int和long型的字段。
     *
     * @return
     */
    String minText() default "";

    /**
     * 最大值。仅作用于int和long型的字段。
     *
     * @return
     */
    long max() default Long.MAX_VALUE;

    /**
     * 大于最大值的提示语。仅作用于int和long型的字段。
     *
     * @return
     */
    String maxText() default "";

}
