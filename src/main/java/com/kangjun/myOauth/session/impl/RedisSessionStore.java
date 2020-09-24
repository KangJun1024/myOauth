package com.kangjun.myOauth.session.impl;

import com.kangjun.myOauth.constant.AccessTokenConstant;
import com.kangjun.myOauth.entity.AccessToken;
import com.kangjun.myOauth.entity.KickOutToken;
import com.kangjun.myOauth.entity.UserDetails;
import com.kangjun.myOauth.exception.AuthenticationException;
import com.kangjun.myOauth.session.SessionStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *  全局Session Redis存储
 */
@Component
@AllArgsConstructor
@Slf4j
public class RedisSessionStore implements SessionStore {

    private RedisTemplate redisTemplate;

    @Override
    public void store(UserDetails userDetails, AccessToken accessToken, String clientId, KickOutToken kickOutToken) throws AuthenticationException {
        // 存储当前用户ID绑定的token 如果不用在此登录 将上一次用户存储的token标识为无效
        // 查询当前token 是否绑定用户
        String userKey = AccessTokenConstant.TOKEN_USER_PREFIX + "_" + userDetails.getUserId() + "_" + clientId;
        Object bindingToken = redisTemplate.opsForValue().get(userKey);
        if (bindingToken == null) {
            // 用户绑定token
            redisTemplate.opsForValue().set(userKey, accessToken.getAccess_token());
        } else {
            // 删除掉被踢掉的token
            redisTemplate.delete(bindingToken.toString());
            // 重新绑定
            redisTemplate.opsForValue().set(userKey, accessToken.getAccess_token());
            // 设置被踢掉的token
            kickOutToken.setToken(bindingToken.toString());
            String kickTokenKey = AccessTokenConstant.TICK_OUT_TOKEN_PREFIX + "_" + bindingToken.toString();
            redisTemplate.opsForValue().set(kickTokenKey, kickOutToken);
        }
}

    }