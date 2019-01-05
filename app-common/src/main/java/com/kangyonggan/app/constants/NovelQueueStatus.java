package com.kangyonggan.app.constants;

import com.kangyonggan.app.annotation.Enum;
import lombok.Getter;

/**
 * 小说队列状态枚举
 *
 * @author kangyonggan
 * @since 8/9/18
 */
@Enum
public enum NovelQueueStatus {

    /**
     * 待更新
     */
    N("N", "待更新"),

    /**
     * 更新中
     */
    I("I", "更新中"),

    /**
     * 更新完成
     */
    Y("Y", "更新完成");

    /**
     * 状态代码
     */
    @Getter
    private final String code;

    /**
     * 状态名称
     */
    @Getter
    private final String name;

    NovelQueueStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }
}