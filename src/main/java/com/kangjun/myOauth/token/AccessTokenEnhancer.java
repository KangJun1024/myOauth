package com.kangjun.myOauth.token;

import com.kangjun.myOauth.entity.AccessToken;
import com.kangjun.myOauth.entity.ClientDetails;
import com.kangjun.myOauth.entity.UserDetails;
import com.kangjun.myOauth.exception.AuthenticationException;

/**
 * 令牌生成器
 */
public interface AccessTokenEnhancer {

    /**
     * 生成全局访问令牌
     * @param userDetails 用户信息
     * @param clientDetails 客户端信息
     * @return
     * @throws AuthenticationException
     */
    AccessToken createToken(UserDetails userDetails, ClientDetails clientDetails) throws AuthenticationException;


}