package com.kangyonggan.app.constants;

import com.kangyonggan.app.annotation.Enum;
import com.kangyonggan.app.util.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 8/12/18
 */
@Log4j2
@Component
public class EnumHelper {

    /**
     * 所有枚举信息
     */
    private Map<String, LinkedHashMap<String, Object>> enums = new HashMap<>();

    /**
     * 处理枚举类
     *
     * @param className
     */
    private void dealClass(String className) {
        try {
            Class clazz = Class.forName(className);
            LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
            Enum e = (Enum) clazz.getDeclaredAnnotation(Enum.class);
            if (e != null) {
                Object[] objs = clazz.getEnumConstants();
                for (Object obj : objs) {
                    Object codeValue = clazz.getDeclaredMethod("get" + StringUtil.firstToUpperCase(e.code())).invoke(obj);
                    Object nameValue = clazz.getDeclaredMethod("get" + StringUtil.firstToUpperCase(e.name())).invoke(obj);
                    linkedHashMap.put(String.valueOf(codeValue), nameValue);
                }

                String key = e.key();
                if (StringUtils.isEmpty(key)) {
                    key = clazz.getSimpleName();
                }
                enums.put(key, linkedHashMap);
            }

        } catch (Exception e) {
            log.error("在收集枚举信息时，处理异常:{}", className, e);
        }
    }

    /**
     * 获取枚举
     *
     * @param key
     * @return
     */
    public LinkedHashMap<String, Object> getEnumMap(String key) {
        LinkedHashMap<String, Object> map = enums.get(key);
        if (map == null) {
            dealClass(getClass().getPackage().getName() + "." + key);
            map = enums.get(key);
        }

        return map;
    }

}
