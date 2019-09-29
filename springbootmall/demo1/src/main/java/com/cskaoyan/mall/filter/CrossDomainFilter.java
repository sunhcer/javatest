package com.cskaoyan.mall.filter;//package com.cs.demo1.filter;
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
///**
// * 跨域问题过滤
// */
//@Component
//@javax.servlet.annotation.WebFilter(urlPatterns = "/api/admin/*")
//public class CrossDomainFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("application/json;charset=UTF-8");
//        //把后台系统的所有接口拦住，但是需要放行登录login接口
//        if ("GET".equals(request.getMethod()) || "POST".equals(request.getMethod())) {
//            String requestURI = request.getRequestURI();
//            //这一步用来替换掉前面的/api/admin然后后面只有login allAdmins updateAdmin 做相应的分发
//            requestURI = requestURI.replace("/api/admin/admin/","");
//            HttpSession session = request.getSession();
//            String id = session.getId();
//            String username = (String) session.getAttribute("username");
//            if ("login".equals(requestURI)) {
//                chain.doFilter(request, response);
//                response.setHeader("Access-Control-Allow-Origin", request.getHeader("http://locahost:8080"));//设置允许跨域访问的地址
//                response.setHeader("Access-Control-Allow-Methods", "HEAD, POST, GET, OPTIONS, DELETE, PUT");
//                response.setHeader("Access-Control-Max-Age", "3600");
//                response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
//                response.setHeader("Access-Control-Allow-Credentials", "true"); //是否支持cookie跨域
//                chain.doFilter(request,response);
//
//            } else {
//                if (username == null) {
//                    //直接返回一串响应报文
//                    return;
//                } else {
//                    chain.doFilter(request, response);
//                    response.setHeader("Access-Control-Allow-Origin", request.getHeader("http://locahost:8080"));//设置允许跨域访问的地址
//                    response.setHeader("Access-Control-Allow-Methods", "HEAD, POST, GET, OPTIONS, DELETE, PUT");
//                    response.setHeader("Access-Control-Max-Age", "3600");
//                    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
//                    response.setHeader("Access-Control-Allow-Credentials", "true"); //是否支持cookie跨域
//                    chain.doFilter(request, response);
//
//                }
//            }
//        }else {
//            chain.doFilter(request, response);
//            response.setHeader("Access-Control-Allow-Origin", request.getHeader("http://locahost:8080"));//设置允许跨域访问的地址
//            response.setHeader("Access-Control-Allow-Methods", "HEAD, POST, GET, OPTIONS, DELETE, PUT");
//            response.setHeader("Access-Control-Max-Age", "3600");
//            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
//            response.setHeader("Access-Control-Allow-Credentials", "true"); //是否支持cookie跨域
//            chain.doFilter(request, response);
//        }
////
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
