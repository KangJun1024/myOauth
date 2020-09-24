package com.kangjun.myOauth.exception;


import com.kangjun.myOauth.constant.enums.ResultCode;

/**
 * 参数不合法异常
 * @author kangjun
 * @since 2020/08/03 18:04:15
 */
public class InvalidParamsException extends AuthenticationException {

    public InvalidParamsException(ResultCode resultCode) {
        super(resultCode);
    }

}
