package com.kangjun.myOauth.filter;

import com.kangjun.myOauth.filter.framework.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  手机登录拦截子类
 *   重写基类抽象方法
 */
@Component
public class PhoneLoginFilter extends AbstractAuthenticationProcessingFilter {

    private static String LOGIN_URL = "/oauth/phone";

    /**
     *  重置父类拦截请求
     */
    public PhoneLoginFilter() {
        super(LOGIN_URL);
    }

    @Override
    public void loginFilter(HttpServletRequest req, HttpServletResponse resp) {

    }
}
