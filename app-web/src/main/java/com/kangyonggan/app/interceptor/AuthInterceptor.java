package com.kangyonggan.app.interceptor;

import com.kangyonggan.app.annotation.PermissionLogin;
import com.kangyonggan.app.annotation.PermissionMenu;
import com.kangyonggan.app.model.Menu;
import com.kangyonggan.app.service.MenuService;
import com.kangyonggan.app.util.RedisSession;
import com.kangyonggan.app.util.SpringUtils;
import com.kangyonggan.common.Resp;
import com.kangyonggan.common.Response;
import com.kangyonggan.common.web.ParamsInterceptor;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 登录认证、身份认证
 *
 * @author kangyonggan
 * @since 6/8/18
 */
@Log4j2
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private MenuService menuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            // 校验登录权限注解
            if (!validLogin(response, handlerMethod)) {
                return false;
            }

            // 校验菜单权限注解
            if (!validMenu(response, handlerMethod)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        // 写入父菜单
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            PermissionMenu permissionMenu = handlerMethod.getMethodAnnotation(PermissionMenu.class);
            if (permissionMenu != null && !InterceptorHelper.isAjaxRequest(request)) {
                List<Menu> menus = getMenuService().findParentMenusByCode(permissionMenu.value()[0]);
                modelAndView.addObject("_openMenus", menus);
                modelAndView.addObject("_baseUrl", request.getRequestURI());
            }
        }
    }

    /**
     * 校验菜单权限注解
     *
     * @param response
     * @param handlerMethod
     * @return
     * @throws Exception
     */
    private boolean validMenu(HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        PermissionMenu permissionMenu = handlerMethod.getMethodAnnotation(PermissionMenu.class);
        if (permissionMenu != null) {
            if (!isLogin(response)) {
                return false;
            }

            boolean hasMenu = getMenuService().hasMenu(RedisSession.currentUserId(), permissionMenu.value());
            if (!hasMenu) {
                if (InterceptorHelper.isAjaxRequest(ParamsInterceptor.getRequest())) {
                    // 9997: 权限不足
                    Response resp = Response.getFailureResponse(Resp.PERMISSION_DENIED.getRespCo(), Resp.PERMISSION_DENIED.getRespMsg());
                    InterceptorHelper.writeResponse(response, resp);
                } else {
                    // 重定向到403界面
                    response.sendRedirect("/error/403");
                }

                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否登录，没登录就重定向到登录
     *
     * @param response
     * @return
     * @throws IOException
     */
    private boolean isLogin(HttpServletResponse response) throws IOException {
        // 判断是否登录
        if (RedisSession.currentUser() == null) {
            if (InterceptorHelper.isAjaxRequest(ParamsInterceptor.getRequest())) {
                // 9998: 登录失效
                Response resp = Response.getFailureResponse(Resp.INVALID_LOGIN.getRespCo(), Resp.INVALID_LOGIN.getRespMsg());
                InterceptorHelper.writeResponse(response, resp);
            } else {
                // 重定向到登录页
                response.sendRedirect("/login?redirectUrl=" + ParamsInterceptor.getRequest().getRequestURI());
            }

            return false;
        }
        return true;
    }

    /**
     * 校验登录权限注解
     *
     * @param response
     * @param handlerMethod
     * @return
     * @throws Exception
     */
    private boolean validLogin(HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        PermissionLogin permissionMenu = handlerMethod.getMethodAnnotation(PermissionLogin.class);
        if (permissionMenu != null) {
            // 判断是否登录
            if (!isLogin(response)) {
                return false;
            }
        }
        return true;
    }

    private MenuService getMenuService() {
        if (menuService == null) {
            menuService = SpringUtils.getBean(MenuService.class);
        }
        return menuService;
    }
}
