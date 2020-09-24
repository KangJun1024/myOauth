package com.kangjun.myOauth.exception;

import com.kangjun.myOauth.constant.enums.ResultCode;

/**
 *  权限认证
 *   客户端为注册异常
 */
public class ClientRegistrationException extends AuthenticationException {

    public ClientRegistrationException(ResultCode resultCode) {
        super(resultCode);
    }
}
