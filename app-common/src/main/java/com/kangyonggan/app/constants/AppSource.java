package com.kangyonggan.app.constants;

import com.kangyonggan.app.annotation.Enum;
import lombok.Getter;

/**
 * 应用来源
 *
 * @author kangyonggan
 * @since 8/13/18
 */
@Enum
public enum AppSource {

    /**
     * PC端
     */
    PC("PC", "PC端"),

    /**
     * SA
     */
    SA("SA", "小程序");

    /**
     * 代码
     */
    @Getter
    private String code;

    /**
     * 名称
     */
    @Getter
    private String name;

    AppSource(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
