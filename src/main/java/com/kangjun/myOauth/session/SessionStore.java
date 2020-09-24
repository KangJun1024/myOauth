package com.kangjun.myOauth.session;


import com.kangjun.myOauth.entity.AccessToken;
import com.kangjun.myOauth.entity.KickOutToken;
import com.kangjun.myOauth.entity.UserDetails;
import com.kangjun.myOauth.exception.AuthenticationException;

/**
 * session 管理
 */
public interface SessionStore {

    void store(UserDetails userDetails, AccessToken accessToken, String clientId, KickOutToken kickOutToken) throws AuthenticationException;

}
