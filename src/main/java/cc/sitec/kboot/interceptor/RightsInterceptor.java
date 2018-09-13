package cc.sitec.kboot.interceptor;

import cc.sitec.kboot.controller.KbootController;
import cc.sitec.kboot.model.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

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
public class RightsInterceptor extends HandlerInterceptorAdapter {
    @Value("${system.super.user.id}")
    private Long superId;

    private PathMatcher matcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            HandlerMethod hm = (HandlerMethod) handler;

            if (!(hm.getBean() instanceof KbootController) && (!user.getId().equals(superId))) {
                Set<String> urls = (Set<String>) session.getAttribute("urls");
                String path = request.getServletPath();
                for (String url : urls) {
                    if (matcher.match(url, path)) {
                        //能匹配到当前的url表示已授权
                        return true;
                    }
                }

                if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
                    //ajax请求
                    response.sendError(403);
                } else {
                    response.sendRedirect(request.getContextPath() + "#/reject");
                }
                return false;
            }
        }
        return true;
    }
}
