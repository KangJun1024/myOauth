package com.kangjun.myOauth.handler.impl;

import com.kangjun.myOauth.constant.ResultSupport;
import com.kangjun.myOauth.entity.AccessToken;
import com.kangjun.myOauth.entity.UserDetails;
import com.kangjun.myOauth.handler.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  认证成功处理器
 */
public class MySuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletResponse response, UserDetails userInfo, AccessToken accessToken) throws IOException {
        ResponseForJsonHandler.handle(response, ResultSupport.ok(accessToken));
    }
}
