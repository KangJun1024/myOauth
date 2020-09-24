package com.kangjun.myOauth.handler;

import com.kangjun.myOauth.entity.AccessToken;
import com.kangjun.myOauth.entity.UserDetails;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理器
 */
public interface AuthenticationSuccessHandler {

    /**
     * 认证成功处理逻辑
     * @param response
     * @param userInfo  用户信息
     * @param accessToken  访问token
     * @throws IOException
     */
    void onAuthenticationSuccess(HttpServletResponse response, UserDetails userInfo, AccessToken accessToken) throws IOException;
}
