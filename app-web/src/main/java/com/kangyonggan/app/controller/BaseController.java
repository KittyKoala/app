package com.kangyonggan.app.controller;

import com.kangyonggan.app.model.User;
import com.kangyonggan.app.util.IpUtil;
import com.kangyonggan.app.util.RedisSession;
import com.kangyonggan.app.util.StringUtil;
import com.kangyonggan.common.Params;
import com.kangyonggan.common.Query;
import com.kangyonggan.common.Response;
import com.kangyonggan.common.web.ParamsInterceptor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Random;

/**
 * @author kangyonggan
 * @since 8/9/18
 */
@Log4j2
public class BaseController {

    /**
     * 异常捕获
     *
     * @param e 异常
     * @return 返回统一异常响应
     */
    @ExceptionHandler
    @ResponseBody
    public Response handleException(Exception e) {
        if (e != null) {
            log.warn("捕获到异常", e);
            return Response.getFailureResponse(e.getMessage());
        }

        return Response.getFailureResponse();
    }

    /**
     * 获取当前登录的用户
     *
     * @return
     */
    protected User currentUser() {
        return RedisSession.currentUser();
    }

    /**
     * 获取当前登录的用户ID
     *
     * @return
     */
    protected Long currentUserId() {
        return RedisSession.currentUserId();
    }

    /**
     * 获取一个验证码
     *
     * @param length
     * @return
     */
    protected String genCode(int length) {
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String code = String.valueOf(random.nextInt(10));
            captcha.append(code);
        }

        return captcha.toString();
    }

    /**
     * 获取请求IP
     *
     * @return
     */
    protected String getIpAddress() {
        return IpUtil.getIp(ParamsInterceptor.getRequest());
    }

    /**
     * 获取请求参数
     *
     * @return 返回请求参数
     */
    protected Params getRequestParams() {
        Params params = new Params();

        // 所有查询条件
        params.setQuery(getQuery());

        // 分页相关参数
        params.setPageSize(getIntegerParam("pageSize", getIntegerParam("limit", 10)));
        int offset = getIntegerParam("offset", 0);
        int pageNum = getIntegerParam("pageNum", offset / params.getPageSize() + 1);
        params.setPageNum(pageNum);

        // 排序相关
        String sort = params.getQuery().getString("sort");
        params.setSort(sort);
        params.setOrder(getStringParam("order", "asc"));
        params.getQuery().put("sort", params.getSort());
        params.getQuery().put("order", params.getOrder());

        return params;
    }

    /**
     * 获取查询条件
     *
     * @return 返回查询条件
     */
    protected Query getQuery() {
        Query query = new Query();
        Map<String, String[]> parameterMap = ParamsInterceptor.getParameterMap();
        for (String key : parameterMap.keySet()) {
            String[] value = parameterMap.get(key);
            if (value != null && value.length == 1) {
                if ("sort".equals(key)) {
                    query.put(key, StringUtil.camelToUnderLine(value[0]));
                } else {
                    query.put(key, value[0]);
                }
            } else {
                query.put(key, value);
            }
        }

        return query;
    }

    /**
     * 获取String类型的请求参数
     *
     * @param name 参数名
     * @return 返回参数值
     */
    protected String getStringParam(String name) {
        return ParamsInterceptor.getParameter(name);
    }

    /**
     * 获取String类型的请求参数, 带默认值
     *
     * @param name         参数名
     * @param defaultValue 默认值
     * @return 返回参数值
     */
    protected String getStringParam(String name, String defaultValue) {
        return ParamsInterceptor.getParameter(name, defaultValue);
    }

    /**
     * 获取int类型的请求参数
     *
     * @param name 参数名
     * @return 返回int型的参数值
     */
    protected int getIntegerParam(String name) {
        return Integer.parseInt(ParamsInterceptor.getParameter(name));
    }

    /**
     * 获取int类型的请求参数, 带默认值
     *
     * @param name         参数名
     * @param defaultValue 默认值
     * @return 返回int型的参数值
     */
    protected int getIntegerParam(String name, int defaultValue) {
        try {
            return Integer.parseInt(ParamsInterceptor.getParameter(name));
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
