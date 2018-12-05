package com.kangyonggan.app.freemarker;

import com.kangyonggan.app.model.Dict;
import com.kangyonggan.app.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Component
public class DictTag extends AbstractFunctionTag {

    @Autowired
    private DictService dictService;

    /**
     * 获取字典列表, 根据type
     *
     * @param arguments 参数
     * @return 返回字典列表
     */
    public List<Dict> list(List arguments) {
        if (!hasLessTwoArgs(arguments)) {
            throw new RuntimeException("获取字典列表必须指定字典类型dict_type！");
        }
        String dictType = arguments.get(1).toString();
        return dictService.findDictsByDictType(dictType);
    }

    /**
     * 获取字典的值, 根据type和code
     *
     * @param arguments 参数
     * @return 返回字典的值
     */
    public String value(List arguments) {
        if (!hasLessThreeArgs(arguments)) {
            throw new RuntimeException("获取字典列表必须指定字典类型dict_type和dict_code！");
        }
        String dictType = arguments.get(1).toString();
        String dictCode = arguments.get(2).toString();
        String defaultValue = dictCode;
        if (hasLessFourArgs(arguments)) {
            defaultValue = arguments.get(3).toString();
        }
        Dict dict = dictService.findDictByDictTypeAndDictCode(dictType, dictCode);
        return dict != null ? dict.getValue() : defaultValue;
    }

}
