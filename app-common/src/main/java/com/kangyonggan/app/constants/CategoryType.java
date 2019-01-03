package com.kangyonggan.app.constants;

import com.kangyonggan.app.annotation.Enum;
import lombok.Getter;

/**
 * 栏目类型枚举
 *
 * @author kangyonggan
 * @since 8/9/18
 */
@Enum
public enum CategoryType {

    /**
     * 导航栏目
     */
    NAV_BAR("NAV_BAR", "导航栏目");

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

    CategoryType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}