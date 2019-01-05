package com.kangyonggan.app.freemarker;

import com.kangyonggan.app.constants.EnumHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Component
public class EnumTag extends AbstractFunctionTag {

    @Autowired
    private EnumHelper enumHelper;

    /**
     * 获取枚举的名值对, 根据key
     *
     * @param arguments 参数
     * @return 返回枚举的所有名值对
     */
    public LinkedHashMap<String, Object> map(List arguments) {
        if (!hasLessTwoArgs(arguments)) {
            throw new RuntimeException("获取枚举的名值对时必须指定枚举的key！");
        }
        String key = arguments.get(1).toString();
        LinkedHashMap<String, Object> map = enumHelper.getEnumMap(key);
        if (map == null) {
            throw new RuntimeException("获取枚举的名值对时异常，key=" + key + "不存在！");
        }

        return map;
    }

    /**
     * 获取枚举的name, 根据key和code
     *
     * @param arguments 参数
     * @return 返回对应的name
     */
    public Object name(List arguments) {
        if (!hasLessThreeArgs(arguments)) {
            throw new RuntimeException("获取枚举的name时必须指定枚举的key和code！");
        }
        String key = arguments.get(1).toString();
        String code = arguments.get(2).toString();
        LinkedHashMap<String, Object> map = enumHelper.getEnumMap(key);
        if (map == null) {
            throw new RuntimeException("获取枚举的name时异常，key=" + key + "不存在！");
        }

        return map.getOrDefault(code, code);
    }

}
