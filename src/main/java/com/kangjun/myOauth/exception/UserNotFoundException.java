package com.kangjun.myOauth.exception;

import com.kangjun.myOauth.constant.enums.ResultCode;

/**
 *  用户 NotFound异常
 */
public class UserNotFoundException extends AuthenticationException {

    public UserNotFoundException(ResultCode resultCode) {
        super(resultCode);
    }

}
