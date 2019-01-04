package com.kangyonggan.app.constants;

import com.kangyonggan.app.annotation.Enum;
import lombok.Getter;

/**
 * 小说来源枚举
 *
 * @author kangyonggan
 * @since 8/9/18
 */
@Enum
public enum NovelSource {

    /**
     * 笔趣阁1
     */
    NS01("NS01", "笔趣阁1", "https://www.biquga.com"),

    /**
     * 笔趣阁2
     */
    NS02("NS02", "笔趣阁2", "https://www.biqubao.com"),

    /**
     * 笔趣阁3
     */
    NS03("NS03", "笔趣阁3", "https://www.qu.la"),

    /**
     * 800小说网
     */
    NS04("NS04", "800小说网", "http://www.800txt.net"),

    /**
     * 言情小说阁
     */
    NS05("NS05", "言情小说阁", "http://www.xianqihaotianmi.com");

    /**
     * 来源代码
     */
    @Getter
    private final String code;

    /**
     * 来源名称
     */
    @Getter
    private final String name;

    /**
     * 来源地址
     */
    @Getter
    private final String url;

    NovelSource(String code, String name, String url) {
        this.code = code;
        this.name = name;
        this.url = url;
    }
}
