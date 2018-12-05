package com.kangyonggan.app.freemarker;

import java.util.List;
import java.util.UUID;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
public class FuncTag extends AbstractFunctionTag {

    /**
     * 获取UUID
     *
     * @param arguments 参数
     * @return 返回UUID
     */
    public String uuid(List arguments) {
        if (!hasLessTwoArgs(arguments)) {
            throw new RuntimeException("获取UUID, 时没有指定前缀");
        }
        String prefix = arguments.get(1).toString();
        return prefix + UUID.randomUUID().toString().replaceAll("-", "");
    }

}
