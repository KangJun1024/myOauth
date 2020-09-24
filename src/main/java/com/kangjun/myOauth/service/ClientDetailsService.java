package com.kangjun.myOauth.service;

import com.kangjun.myOauth.entity.ClientDetails;
import com.kangjun.myOauth.exception.ClientRegistrationException;

/**
 * 客户端信息注册授权中心
 * client_id
 * client_secret
 */
public interface ClientDetailsService {

    /**
     * 通过clientId 获取客户端详情
     * @param clientId
     * @return
     * @throws ClientRegistrationException
     */
    ClientDetails getClientByClientId(String clientId) throws ClientRegistrationException;
}
