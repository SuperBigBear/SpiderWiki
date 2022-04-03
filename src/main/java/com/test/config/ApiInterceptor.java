package com.test.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Component
public class ApiInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("*******ApiInterceptor.PreHandle*******");
        request.setAttribute("StartTime", System.currentTimeMillis());
        String url = request.getRequestURI();
        String contextPath = request.getContextPath();
        //rediect all links to /api/index
        if (!url.matches("^/api/index")) {
            System.out.println("Current request api url:\"" + url + "\" will be redirected to \"/api/index\"");
            response.sendRedirect("/api/index");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("*******ApiInterceptor.postHandle*******");
        long startTime = (Long) request.getAttribute("StartTime");
        request.removeAttribute("StartTime");
        long endTime = System.currentTimeMillis();
        System.out.println("Request api url:\"" + request.getRequestURI() + "\" Processed time:" + (endTime - startTime) + "ms");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("*******ApiInterceptor.afterCompletion*******");
    }
}
