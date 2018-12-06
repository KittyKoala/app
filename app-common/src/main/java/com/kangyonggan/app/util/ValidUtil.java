package com.kangyonggan.app.util;

import com.github.ofofs.jca.annotation.Util;
import com.kangyonggan.app.annotation.Valid;
import com.kangyonggan.app.exception.ValidException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author kangyonggan
 * @since 8/9/18
 */
@Util
public class ValidUtil {

    /**
     * 字段校验
     *
     * @param object
     */
    public static void valid(Object object) {
        // 获取对象所有字段，包括父类字段
        List<Field> fields = Reflections.getAccessibleFields(object);

        // 遍历所有字段并校验
        for (Field field : fields) {
            Valid valid = field.getAnnotation(Valid.class);
            if (valid != null) {
                valid(object, field, valid);
            }
        }
    }

    /**
     * 校验
     *
     * @param object
     * @param field
     * @param valid
     */
    private static void valid(Object object, Field field, Valid valid) {
        Object value = Reflections.getFieldValue(object, field.getName());
        if (value == null && valid.required()) {
            String requiredText = valid.requiredText();
            if (StringUtils.isEmpty(requiredText)) {
                requiredText = String.format("%s不能为空", field.getName());
            }
            throw new ValidException(requiredText);
        }

        if (value instanceof String) {
            validString((String) value, valid, field);
        } else if (value instanceof Integer) {
            validNumber(value, valid, field);
        } else if (value instanceof Long) {
            validNumber(value, valid, field);
        } else if (value instanceof BigDecimal) {
            validNumber(value, valid, field);
        } else if (value instanceof Float) {
            validNumber(value, valid, field);
        } else if (value instanceof Double) {
            validNumber(value, valid, field);
        } else if (value instanceof Short) {
            validNumber(value, valid, field);
        }
    }

    /**
     * 校验字符串
     *
     * @param value
     * @param valid
     * @param field
     */
    private static void validString(String value, Valid valid, Field field) {
        if (StringUtils.isEmpty(value) && !valid.required()) {
            return;
        }
        // 校验长度
        if (valid.length() > 0 && value.length() != valid.length()) {
            String lengthText = valid.lengthText();
            if (StringUtils.isEmpty(lengthText)) {
                lengthText = String.format("%s只能%d位", field.getName(), valid.length());
            }
            throw new ValidException(lengthText);
        }
        // 校验最小长度
        if (value.length() < valid.minLength()) {
            String minLengthText = valid.minLengthText();
            if (StringUtils.isEmpty(minLengthText)) {
                minLengthText = String.format("%s至少%d位", field.getName(), valid.minLength());
            }
            throw new ValidException(minLengthText);
        }
        // 校验最大长度
        if (value.length() > valid.maxLength()) {
            String maxLengthText = valid.maxLengthText();
            if (StringUtils.isEmpty(maxLengthText)) {
                maxLengthText = String.format("%s至多%d位", field.getName(), valid.maxLength());
            }
            throw new ValidException(maxLengthText);
        }
        // 校验正则表达式
        if (StringUtils.isNotEmpty(valid.regex()) && !value.matches(valid.regex())) {
            String regexText = valid.regexText();
            if (StringUtils.isEmpty(regexText)) {
                regexText = String.format("%s不合法", field.getName());
            }
            throw new ValidException(regexText);
        }
    }

    /**
     * 校验数字类型
     *
     * @param value
     * @param valid
     * @param field
     */
    private static void validNumber(Object value, Valid valid, Field field) {
        BigDecimal val = new BigDecimal(String.valueOf(value));
        BigDecimal min = BigDecimal.valueOf(valid.min());
        BigDecimal max = BigDecimal.valueOf(valid.max());
        // 校验最小值
        if (val.compareTo(min) < 0) {
            String minText = valid.minText();
            if (StringUtils.isEmpty(minText)) {
                minText = String.format("%s不能小于%d", field.getName(), valid.min());
            }
            throw new ValidException(minText);
        }
        // 校验最大值
        if (val.compareTo(max) > 0) {
            String maxText = valid.maxText();
            if (StringUtils.isEmpty(maxText)) {
                maxText = String.format("%s不能大于%d", field.getName(), valid.max());
            }
            throw new ValidException(maxText);
        }
    }


}
