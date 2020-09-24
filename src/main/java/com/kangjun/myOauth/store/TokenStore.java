package com.kangjun.myOauth.store;

import com.kangjun.myOauth.entity.AccessToken;
import com.kangjun.myOauth.entity.ClientDetails;
import com.kangjun.myOauth.entity.UserDetails;
import com.kangjun.myOauth.exception.AuthenticationException;

/**
 *  存储 token 基类
 */
public interface TokenStore {

    /**
     * @param accessToken token
     * @param userDetails 用户详情对象
     */
    void store(AccessToken accessToken, UserDetails userDetails, ClientDetails clientDetails) throws AuthenticationException;

}