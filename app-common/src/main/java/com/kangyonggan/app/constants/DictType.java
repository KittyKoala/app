package com.kangyonggan.app.constants;

import com.kangyonggan.app.annotation.Enum;
import lombok.Getter;

/**
 * 字典类型枚举
 *
 * @author kangyonggan
 * @since 8/9/18
 */
@Enum
public enum DictType {

    /**
     * 证件类型
     */
    ID_TYPE("ID_TYPE", "证件类型"),

    /**
     * 字体
     */
    FONT("FONT", "字体");

    /**
     * 类型代码
     */
    @Getter
    private final String code;

    /**
     * 类型名称
     */
    @Getter
    private final String name;

    DictType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}