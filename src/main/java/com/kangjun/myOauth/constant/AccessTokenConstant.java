package com.kangjun.myOauth.constant;

/**
 *  token常量
 */
public class AccessTokenConstant {

    /**
     * 用户绑定 token 前缀
     */
    public static final String TOKEN_USER_PREFIX = "ACCESS_CLIENT_USER";

    /**
     * 被踢掉的 token
     */
    public static final String TICK_OUT_TOKEN_PREFIX = "TICK_OUT_TOKEN";

    /**
     * token 绑定客户端ID
     */
    public static final String TOKEN_BINDING_CLIENT = "TOKEN_BINDING_CLIENT";

    /**
     * token 值
     */
    public final static String TOKEN = "Authorization";
    /**
     * token 类型
     */
    public final static String TOKEN_TYPE = "bearer";
}
