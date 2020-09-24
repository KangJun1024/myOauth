package com.kangjun.myOauth.store.impl;

import com.kangjun.myOauth.constant.AccessTokenConstant;
import com.kangjun.myOauth.entity.AccessToken;
import com.kangjun.myOauth.entity.ClientDetails;
import com.kangjun.myOauth.entity.UserDetails;
import com.kangjun.myOauth.exception.AuthenticationException;
import com.kangjun.myOauth.store.TokenStore;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis 存储token
 */
@Component
@AllArgsConstructor
public class RedisTokenStore implements TokenStore {

    private RedisTemplate redisTemplate;


    /**
     * @param accessToken token
     * @param userDetails 用户详情对象
     * @param clientDetails
     * @throws AuthenticationException
     */
    @Override
    public void store(AccessToken accessToken, UserDetails userDetails, ClientDetails clientDetails) throws AuthenticationException {
        redisTemplate
                .opsForValue()
                .set(accessToken.getAccess_token(), userDetails, clientDetails.getTokenExpireTime(), TimeUnit.MINUTES);

        redisTemplate.opsForValue()
                .set(AccessTokenConstant.TOKEN_BINDING_CLIENT + "_" + accessToken.getAccess_token(), clientDetails);
    }
}
