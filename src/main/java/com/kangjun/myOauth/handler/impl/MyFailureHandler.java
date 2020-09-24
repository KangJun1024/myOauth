package com.kangjun.myOauth.handler.impl;

import cn.hutool.json.JSONObject;
import com.kangjun.myOauth.constant.ProcessResult;
import com.kangjun.myOauth.constant.ResultSupport;
import com.kangjun.myOauth.entity.AccessToken;
import com.kangjun.myOauth.entity.UserDetails;
import com.kangjun.myOauth.exception.AuthenticationException;
import com.kangjun.myOauth.handler.AuthenticationFailureHandler;
import com.kangjun.myOauth.handler.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  认证失败处理器
 */
public class MyFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletResponse response, AuthenticationException exception) throws IOException {
        ProcessResult processResult = new ProcessResult(exception.getCode(), exception.getMsg(), new JSONObject());
        ResponseForJsonHandler.handle(response, processResult);
    }
}
