package com.kangjun.myOauth.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 客户端详情
 *  获取token需提供的信息
 */
@Data
@Accessors(chain = true)
public class ClientDetails implements Serializable {

    /**
     * 客户端ID
     */
    private String clientId;


    /**
     * 客户端秘钥
     */
    private String clientSecret;


    /**
     * 应用名称
     */
    private String applicationName;


    /**
     * token 过期时间 单位/分钟
     */
    private Integer tokenExpireTime = 10;


}
