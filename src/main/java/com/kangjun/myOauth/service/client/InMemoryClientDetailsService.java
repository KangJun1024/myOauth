package com.kangjun.myOauth.service.client;

import com.kangjun.myOauth.constant.enums.ResultCode;
import com.kangjun.myOauth.entity.ClientDetails;
import com.kangjun.myOauth.exception.ClientRegistrationException;
import com.kangjun.myOauth.service.ClientDetailsService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  基于内存存储客户端信息
 */
public class InMemoryClientDetailsService implements ClientDetailsService {

    private static Map<String,ClientDetails> clientDetailsMap;

    //初始化客户端信息
    static {
        clientDetailsMap = new ConcurrentHashMap<>();
        // web 后台管理
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientId("9E7D87A7BB354EE2872DDE63541B027E")
                .setApplicationName("后台管理")
                .setClientSecret("OUU3RDg3QTdCQjM1NEVFMjg3MkRERTYzNTQxQjAyN0U=")
                .setTokenExpireTime(2 * 60); // 过期时间2个小时
        ClientDetails clientDetails2 = new ClientDetails();
        clientDetails2.setClientId("88ED6A8DAC6F48EFB020FCA32E4715D2")
                .setApplicationName("终端的用户")
                .setClientSecret("ODhFRDZBOERBQzZGNDhFRkIwMjBGQ0EzMkU0NzE1RDI=")
                .setTokenExpireTime(30 * 12 * 60); // 过期时间30天

        clientDetailsMap.put(clientDetails.getClientId(), clientDetails);
        clientDetailsMap.put(clientDetails2.getClientId(), clientDetails2);
    }


    @Override
    public ClientDetails getClientByClientId(String clientId) throws ClientRegistrationException {
        ClientDetails clientDetails = clientDetailsMap.get(clientId);
        if (clientDetails == null) {
            throw new ClientRegistrationException(ResultCode.CLIENT_USER_NOT_EXIT);
        }
        return clientDetails;
    }
}
