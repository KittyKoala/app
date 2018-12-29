package com.kangyonggan.app.interceptor;

import com.kangyonggan.app.annotation.Token;
import com.kangyonggan.app.util.RedisSession;
import com.kangyonggan.app.util.StringUtil;
import com.kangyonggan.common.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author kangyonggan
 * @since 8/11/18
 */
@Log4j2
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            Token token = method.getMethodAnnotation(Token.class);
            if (token != null && token.type() == Token.Type.CHECK) {
                if (isRepeatSubmit(request, token)) {
                    if (InterceptorHelper.isAjaxRequest(request)) {
                        // 9996: token失效或重复提交
                        Response resp = Response.getFailureResponse("9996", "token失效或重复提交");
                        InterceptorHelper.writeResponse(response, resp);
                    } else {
                        // 重定向到repeat-submit
                        response.sendRedirect("/error/repeat-submit");
                    }

                    return false;
                }
            }
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            Token token = method.getMethodAnnotation(Token.class);
            if (token != null && token.type() == Token.Type.GENERATE) {
                String userToken = UUID.randomUUID().toString();
                modelAndView.addObject("_token", userToken);
                RedisSession.put(token.key(), userToken);
                log.info("生成一个token，key={}, token={}", token.key(), userToken);
            }
        }

        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 校验是否重复提交
     *
     * @param request 请求
     * @param token   令牌
     * @return 重复提交返回true，否则返回false
     */
    private boolean isRepeatSubmit(HttpServletRequest request, Token token) {
        try {
            String userToken = request.getParameter("_token");
            String sessionToken = RedisSession.getString(token.key());
            log.info("校验是否重复提交，key={}, token={}, sessionToken={}", token.key(), userToken, sessionToken);
            if (StringUtil.hasEmpty(userToken, sessionToken)) {
                return true;
            }
            return !userToken.equals(sessionToken);
        } catch (Exception e) {
            log.error("校验是否重复提交异常", e);
            return true;
        } finally {
            RedisSession.delete(token.key());
        }
    }

}
