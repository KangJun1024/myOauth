package com.kangjun.myOauth.exception;

import com.kangjun.myOauth.constant.enums.ResultCode;
import lombok.Data;

/**
 *  权限认证异常基类
 */
@Data
public class AuthenticationException extends RuntimeException{

    private Integer code;

    private String msg;

    public AuthenticationException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }
}
