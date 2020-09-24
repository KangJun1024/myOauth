package com.kangjun.myOauth.handler;

import com.kangjun.myOauth.exception.AuthenticationException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthenticationFailureHandler {

    void onAuthenticationFailure(HttpServletResponse response, AuthenticationException exception) throws IOException;
}