package com.cskaoyan.mall.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("GET".equals(request.getMethod()) || "POST".equals(request.getMethod())) {
            String requestURI = request.getRequestURI();
            //这一步用来替换掉前面的/api/admin然后后面只有login allAdmins updateAdmin 做相应的分发
            requestURI = requestURI.replace("/api/admin/admin", "");
            HttpSession session = request.getSession();
            String id = session.getId();
            String username = (String) session.getAttribute("username");
            if ("/login".equals(requestURI)) {
                return true;
            } else {
                if (username == null) {
//                    request.getRequestDispatcher("/static/errorRef.jpg").forward(request, response);
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
