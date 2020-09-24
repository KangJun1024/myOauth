package com.kangjun.myOauth.filter.framework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截请求基类
 *  请求路径拦截
 *  获取token
 */
@Slf4j
public abstract class AbstractAuthenticationProcessingFilter extends GenericFilterBean {

    //默认登录请求拦截路径
    private String loginUrl = "/oauth/token";

    //重置拦截地址
    public AbstractAuthenticationProcessingFilter(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //转换Http协议
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //请求地址路径匹配拦截
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        if(antPathMatcher.match(req.getServletPath(),loginUrl)){ //匹配拦截路径，子类重写基类抽象方法，执行各个子类方法
            this.loginFilter(req,resp);
        }else {
            //放行请求
            filterChain.doFilter(request,response);
        }

    }

    public abstract void loginFilter(HttpServletRequest req,HttpServletResponse resp);
}
