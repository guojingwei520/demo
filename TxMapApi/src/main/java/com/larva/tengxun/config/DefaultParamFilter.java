package com.larva.tengxun.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 郭景伟Larva
 * @Date 2022-11-11 10:38
 * @describe: 过滤所有带notoken路径的请求。截取请求参数。
 **/
@WebFilter(urlPatterns = "/key/*", filterName = "DefaultParamFilter")
public class DefaultParamFilter extends OncePerRequestFilter {
    @Value("${tengxun.map.key}")
    private String key;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request.setAttribute("param","?"+request.getQueryString()+"&key="+key);
        filterChain.doFilter(request, response);
    }
}
