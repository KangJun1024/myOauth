package com.kangjun.myOauth.token.impl;

import cn.hutool.core.lang.UUID;
import com.kangjun.myOauth.entity.AccessToken;
import com.kangjun.myOauth.entity.ClientDetails;
import com.kangjun.myOauth.entity.UserDetails;
import com.kangjun.myOauth.exception.AuthenticationException;
import com.kangjun.myOauth.token.AccessTokenEnhancer;

/**
 * UUID token
 */
public class UUIDTokenEnhancer implements AccessTokenEnhancer {

    @Override
    public AccessToken createToken(UserDetails userDetails, ClientDetails clientDetails) throws AuthenticationException {
        return new AccessToken()
                .setAccess_token(UUID.randomUUID().toString().replace("-", "").toUpperCase())
                .setExpires_in(clientDetails.getTokenExpireTime());

    }
}
