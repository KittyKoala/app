package com.kangyonggan.app.util;

import com.kangyonggan.app.constants.RedisKey;
import com.kangyonggan.app.exception.BizException;
import com.kangyonggan.app.interceptor.InterceptorHelper;
import com.kangyonggan.app.model.User;
import com.kangyonggan.app.service.RedisService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author kangyonggan
 * @since 8/16/18
 */
@Log4j2
public class RedisSession {

    private static RedisService redisService;

    /**
     * session在redis的key前缀
     */
    private static final String PREFIX = "APP:SESSION:";

    /**
     * session有效期
     */
    private static final long TIMEOUT = 1;

    /**
     * session有效期单位
     */
    private static final TimeUnit TIME_UNIT = TimeUnit.DAYS;

    /**
     * 向redis中存当前用户的值
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean put(String key, Serializable value) {
        String jsessionid = InterceptorHelper.getJSessionId();
        return redisService().set(PREFIX + jsessionid + ":" + key, value, TIMEOUT, TIME_UNIT);
    }

    /**
     * 从redis中取当前用户的值
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        String jsessionid = InterceptorHelper.getJSessionId();
        return redisService().get(PREFIX + jsessionid + ":" + key);
    }

    /**
     * 从redis中取当前用户的值
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        String jsessionid = InterceptorHelper.getJSessionId();
        return (String) redisService().get(PREFIX + jsessionid + ":" + key);
    }

    /**
     * 从redis中取当前用户的值
     *
     * @param key
     * @param clazz
     * @return
     */
    public static <T> T get(String key, Class<T> clazz) {
        String jsessionid = InterceptorHelper.getJSessionId();
        return (T) redisService().get(PREFIX + jsessionid + ":" + key);
    }

    /**
     * 从redis中删除指定key的当前用户的信息
     *
     * @param key
     * @return
     */
    public static Object delete(String key) {
        String jsessionid = InterceptorHelper.getJSessionId();
        return redisService().delete(PREFIX + jsessionid + ":" + key);
    }

    /**
     * 保存当前登录用户到redis, 并向客户端cookie中回写jsessionid
     *
     * @param user
     * @return 返回jsessionid
     */
    public static String saveUser(User user) {
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletResponse response = requestAttributes.getResponse();
            String jsessionid = InterceptorHelper.getJSessionId();

            // 优先使用原先cookie中的jsessionid
            if (StringUtils.isEmpty(jsessionid)) {
                long id = redisService().incr(RedisKey.KEY_JSESSIONID);
                jsessionid = UUID.randomUUID().toString().replaceAll("-", "") +
                        StringUtils.leftPad(String.valueOf(id), 5, "0");
            }

            Cookie cookie = new Cookie("JSESSIONID", jsessionid);
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);

            redisService().set(PREFIX + jsessionid, user, TIMEOUT, TIME_UNIT);
            log.info("用户信息存入redis成功，JSESSIONID={}", jsessionid);
            return jsessionid;
        } catch (Exception e) {
            throw new BizException("保存当前登录用户异常", e);
        }
    }

    /**
     * 从redis删除当前用户
     *
     * @return
     */
    public static void removeUser() {
        try {
            String jsessionid = InterceptorHelper.getJSessionId();
            redisService().deleteAll(PREFIX + jsessionid + "*");
            log.info("用户信息从redis删除成功，JSESSIONID={}", jsessionid);
        } catch (Exception e) {
            throw new BizException("从redis删除当前用户异常", e);
        }
    }

    /**
     * 从redis获取当前登录用户
     *
     * @return
     */
    public static User currentUser() {
        try {
            String jsessionid = InterceptorHelper.getJSessionId();
            return (User) redisService().getAndUpdateExpire(PREFIX + jsessionid, TIMEOUT, TIME_UNIT);
        } catch (Exception e) {
            throw new BizException("获取当前用户异常", e);
        }
    }

    /**
     * 从redis获取当前登录用户ID
     *
     * @return
     */
    public static Long currentUserId() {
        User user = currentUser();
        if (user != null) {
            return user.getUserId();
        }
        return null;
    }

    private static RedisService redisService() {
        if (redisService == null) {
            redisService = SpringUtils.getBean(RedisService.class);
        }

        return redisService;
    }
}
