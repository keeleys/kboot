package cc.sitec.kboot.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : cc.sitec.kboot.interceptor</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2018年08月09日</li>
 * <li>@author     : keeley</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HttpSession session = request.getSession();
            //判断session中是否有用户信息
            if (session.getAttribute("user") == null) {
                if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
                    //ajax请求
                    response.sendError(401);
                } else {
                    response.sendRedirect(request.getContextPath() + "#/login");
                }
                return false;
            }
        }
        return true;
    }
}
