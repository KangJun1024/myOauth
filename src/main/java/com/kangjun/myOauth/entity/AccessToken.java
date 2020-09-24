package com.kangjun.myOauth.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 全局唯一接口调用凭据
 */
@Data
@Accessors(chain = true)
public class AccessToken {

    /**
     * token
     */
    private String access_token;

    /**
     * token类型
     */
    private String token_type = "bearer";

    /**
     * 过期时间 单位/分钟
     */
    private Integer expires_in = 10 * 1000;

    /**
     * 作用范围
     */
    private String scope = "all";


}
