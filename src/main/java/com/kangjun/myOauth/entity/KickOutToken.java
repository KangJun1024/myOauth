package com.kangjun.myOauth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 被踢掉的 token
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class KickOutToken implements Serializable {

    /**
     * 凭证
     */
    private String token;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * 客户端类型
     */
    private String clientType;
}
