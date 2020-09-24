package com.kangjun.myOauth.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  JSON 格式响应处理器 jackson
 */
public class ResponseForJsonHandler {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void handle(HttpServletResponse response, Object body) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().print(objectMapper.writeValueAsString(body));
    }
}
