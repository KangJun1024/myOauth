package com.kangjun.myOauth.service;

import com.kangjun.myOauth.constant.ClientConstant;
import com.kangjun.myOauth.constant.enums.ResultCode;
import com.kangjun.myOauth.entity.AccessToken;
import com.kangjun.myOauth.entity.ClientDetails;
import com.kangjun.myOauth.entity.KickOutToken;
import com.kangjun.myOauth.entity.UserDetails;
import com.kangjun.myOauth.exception.AuthenticationException;
import com.kangjun.myOauth.exception.ClientRegistrationException;
import com.kangjun.myOauth.exception.UserNotFoundException;
import com.kangjun.myOauth.handler.AuthenticationFailureHandler;
import com.kangjun.myOauth.handler.AuthenticationSuccessHandler;
import com.kangjun.myOauth.session.SessionStore;
import com.kangjun.myOauth.store.TokenStore;
import com.kangjun.myOauth.token.AccessTokenEnhancer;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *  认证模板模式  基类
 */
@Slf4j
public abstract class AbstractAuthenticationProcessing implements ApplicationContextAware, InitializingBean {

    @Setter
    private HttpServletRequest request;

    @Setter
    private HttpServletResponse response;

    //获取客户端用户信息接口
    private ClientDetailsService clientDetailsService;

    //认证成功处理器
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    //认证失败处理器
    private AuthenticationFailureHandler authenticationFailureHandler;

    //token生成器
    private AccessTokenEnhancer accessTokenEnhancer;

    //token 存储
    private TokenStore tokenStore;

    //session 管理
    private SessionStore sessionStore;

    /**
     * 验证客户端  client_id  client_secret
     */
    private ClientDetails verifyClientDetails(){
        //0.0 获取client_id
        String clientId = getClientId();
        //0.1 获取客户端详情
        ClientDetails clientDetails = clientDetailsService.getClientByClientId(clientId);
        if (clientDetails == null) {
            throw new ClientRegistrationException(ResultCode.CLIENT_USER_NOT_EXIT);
        }else {
            //0.2 验证 client_secret
            String clientSecret = getClientSecret();
            if(!clientDetails.getClientSecret().equals(clientSecret)){
                throw new ClientRegistrationException(ResultCode.CLIENT_USER_PASSWORD_ERROR);
            }
        }


        return clientDetails;
    }


    /**
     * 验证获取用户详情  实现类实现
     */
    public abstract UserDetails getUserInfo(Map<String, Object> params);

    /**
     * 创建 Token
     */
    private AccessToken crateToken(UserDetails userInfo, ClientDetails clientDetails) {
        return accessTokenEnhancer.createToken(userInfo, clientDetails);
    }

    /**
     *  用户登录接口
     */
    public final void login() throws IOException{
        UserDetails userDetails;
        AccessToken accessToken;
        try {
            //0.1 验证获取客户端信息
            ClientDetails clientDetails = verifyClientDetails();
            //0.2 验证获取用户信息
            userDetails = getUserInfo(getParameterMap(this.request));
            if (userDetails == null) {
                throw new UserNotFoundException(ResultCode.FORBIDDEN_USER_NOT_EXIT);
            }
            //0.3 生成token信息
            accessToken = crateToken(userDetails, clientDetails);
            //0.4 存储token
            tokenStore.store(accessToken,userDetails,clientDetails);
            //0.5 存储被踢掉的token 存储被那个ip地址 以及浏览器地址
            KickOutToken kickOutToken = new KickOutToken()
                    .setClientType(request.getHeader("User-Agent"))
                    .setIpAddress(request.getRemoteAddr());
            //0.6 session 管理
            sessionStore.store(userDetails, accessToken, getClientId(), kickOutToken);

        } catch (AuthenticationException exception) {
            authenticationFailureHandler.onAuthenticationFailure(response, exception);
            return;
        }
        authenticationSuccessHandler.onAuthenticationSuccess(response, userDetails, accessToken);

    }


    /**
     * 获取clientId
     * @return
     */
    protected String getClientId(){
        //请求参数获取client_id
        String clientId = this.request.getParameter(ClientConstant.CLIENT_ID);
        if(clientId.isEmpty()){
            throw new ClientRegistrationException(ResultCode.CLIENT_PARAMS_ERROR);
        }
        return clientId;
    }

    /**
     * 获取clientSecret
     * @return
     */
    protected String getClientSecret(){
        //请求参数获取client_secret
        String clientSecret = this.request.getParameter(ClientConstant.CLIENT_SECRET);
        if(clientSecret.isEmpty()){
            throw new ClientRegistrationException(ResultCode.CLIENT_PARAMS_ERROR);
        }
        return clientSecret;
    }

    /**
     * 封装请求参数
     * @param request
     * @return
     */
    private Map<String, Object> getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map<?, ?> properties = request.getParameterMap();
        // 返回值Map
        Map<String, Object> returnMap = new HashMap<>();
        Iterator<?> entries = properties.entrySet().iterator();

        Map.Entry<String, Object> entry;
        String name;
        String value = "";
        Object valueObj;
        while (entries.hasNext()) {
            entry = (Map.Entry<String, Object>) entries.next();
            name = entry.getKey();
            valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.clientDetailsService = applicationContext.getBean(ClientDetailsService.class);
        this.authenticationSuccessHandler = applicationContext.getBean(AuthenticationSuccessHandler.class);
        this.authenticationFailureHandler = applicationContext.getBean(AuthenticationFailureHandler.class);
        this.accessTokenEnhancer = applicationContext.getBean(AccessTokenEnhancer.class);
        this.tokenStore = applicationContext.getBean(TokenStore.class);
        this.sessionStore = applicationContext.getBean(SessionStore.class);

    }

    @Override
    public void afterPropertiesSet() {
        if (clientDetailsService == null) {
            log.error("ClientDetailsService 未实例化");
            throw new ClientRegistrationException(ResultCode.CLIENT_INIT_ERROR);
        }
    }

}
