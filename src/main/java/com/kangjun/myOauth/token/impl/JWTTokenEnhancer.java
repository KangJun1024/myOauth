package com.kangjun.myOauth.token.impl;

import cn.hutool.system.UserInfo;
import com.kangjun.myOauth.entity.AccessToken;
import com.kangjun.myOauth.entity.ClientDetails;
import com.kangjun.myOauth.entity.UserDetails;
import com.kangjun.myOauth.exception.AuthenticationException;
import com.kangjun.myOauth.token.AccessTokenEnhancer;
import com.kangjun.myOauth.utils.JWTUtils;

/**
 *  JWT token
 */
public class JWTTokenEnhancer implements AccessTokenEnhancer {
    @Override
    public AccessToken createToken(UserDetails userDetails, ClientDetails clientDetails) throws AuthenticationException {
        String token = JWTUtils.createJWT(clientDetails.getTokenExpireTime(), (UserInfo) userDetails);
        return new AccessToken()
                .setAccess_token(token)
                .setExpires_in(clientDetails.getTokenExpireTime());
    }
}
