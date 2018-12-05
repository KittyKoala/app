package com.kangyonggan.app.freemarker;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 功能标签
 *
 * @author kangyonggan
 * @since 16/4/29
 */
public class AbstractFunctionTag implements TemplateMethodModelEx {

    /**
     * 执行
     *
     * @param arguments 参数列表
     * @return 返回执行结果
     * @throws TemplateModelException 可能会发生的异常
     */
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (arguments.isEmpty()) {
            return null;
        }

        try {
            Method method = getClass().getDeclaredMethod(arguments.get(0).toString(), new Class[]{List.class});
            return method.invoke(this, new Object[]{arguments});
        } catch (Exception e) {
            throw new TemplateModelException(e);
        }
    }

    /**
     * 判断至少有n个参数
     *
     * @param arguments 参数列表
     * @param minLength 最少个数
     * @return 最少n个返回true，否则返回false
     */
    protected boolean hasLessArgs(List arguments, int minLength) {
        return arguments.size() >= minLength;
    }

    /**
     * 判断至少有2个参数
     *
     * @param arguments 参数列表
     * @return 最少2个返回true，否则返回false
     */
    protected boolean hasLessTwoArgs(List arguments) {
        return arguments.size() >= 2;
    }

    /**
     * 判断至少有3个参数
     *
     * @param arguments 参数列表
     * @return 最少3个返回true，否则返回false
     */
    protected boolean hasLessThreeArgs(List arguments) {
        return arguments.size() >= 3;
    }

    /**
     * 判断至少有4个参数
     *
     * @param arguments 参数列表
     * @return 最少4个返回true，否则返回false
     */
    protected boolean hasLessFourArgs(List arguments) {
        return arguments.size() >= 4;
    }
}
