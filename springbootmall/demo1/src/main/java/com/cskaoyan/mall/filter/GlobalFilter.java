package com.cskaoyan.mall.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2019/8/30
 * Time: 10:48
 */
@Component
@javax.servlet.annotation.WebFilter(urlPatterns = "/api/admin/*")
public class GlobalFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");
        //把后台系统的所有接口拦住，但是需要放行登录login接口
        if ("GET".equals(request.getMethod()) || "POST".equals(request.getMethod())) {
            String requestURI = request.getRequestURI();
            //这一步用来替换掉前面的/api/admin然后后面只有login allAdmins updateAdmin 做相应的分发
            requestURI = requestURI.replace("/api/admin/admin/","");
            HttpSession session = request.getSession();
            String id = session.getId();
            System.out.println("过滤器的session===id"+id);
            String username = (String) session.getAttribute("username");
            if ("login".equals(requestURI)) {
//
                chain.doFilter(request, response);

//                String origin = "http://locahost:8080";
//                //直接把origin写在配置文件里，从配置文件里读取
//                //处于安全性考虑，当从该域名发送过来的请求，可以带上cookie,跨域（）默认不会带上cookie，需要设置一些参数
//                response.addHeader("Access-Control-Allow-Origin", origin);
//                response.addHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,PUT,DELETE");
//                response.addHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization,Content-Type");
//                response.setHeader("Access-Control-Allow-Credentials", "true");
            } else {
                if (username == null) {
                    //直接返回一串响应报文
                    return;
                } else {
                    chain.doFilter(request, response);
//                    String origin = "http://localhost:8080";
//                    //直接把origin写在配置文件里，从配置文件里读取
//                    //处于安全性考虑，当从该域名发送过来的请求，可以带上cookie,跨域（）默认不会带上cookie，需要设置一些参数
//                    response.addHeader("Access-Control-Allow-Origin", origin);
//                    response.addHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,PUT,DELETE");
//                    response.addHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization,Content-Type");
//                    response.setHeader("Access-Control-Allow-Credentials", "true");

                }
            }
        }else {
            chain.doFilter(request, response);
//
//            String origin = "http://localhost:8080";
//            response.addHeader("Access-Control-Allow-Origin", origin);
//            response.addHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,PUT,DELETE");
//            response.addHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization,Content-Type");
//            response.setHeader("Access-Control-Allow-Credentials", "true");
        }
    }


    public void init(FilterConfig config) throws ServletException {

    }

}
