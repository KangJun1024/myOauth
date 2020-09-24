package com.kangjun.myOauth.provider;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.kangjun.myOauth.constant.PhoneLoginConstant;
import com.kangjun.myOauth.constant.enums.ResultCode;
import com.kangjun.myOauth.entity.UserDetails;
import com.kangjun.myOauth.exception.InvalidParamsException;
import com.kangjun.myOauth.service.AbstractAuthenticationProcessing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *  手机号验证码登录认证模板
 */
@Service
@Slf4j
public class PhoneLoginProvider extends AbstractAuthenticationProcessing {

    @Override
    public UserDetails getUserInfo(Map<String, Object> params) {

        return null;
    }



    /**
     * 获取用户类型
     */
    private String getUserType(Map params) {
        String userType = (String) params.get(PhoneLoginConstant.USER_TYPE);
        if (userType == null) {
            throw new InvalidParamsException(ResultCode.FORBIDDEN_USER_TYPE_NULL);
        }
        return userType;
    }

    /**
     * 获取手机号
     */
    private String getPhone(Map params) {
        String phone = (String) params.get(PhoneLoginConstant.PHONE);
        if (StrUtil.isEmpty(phone)) {
            throw new InvalidParamsException(ResultCode.BAD_REQUEST_TELEPHONE_ERROR);
        }
        if (StrUtil.isNotEmpty(phone) && !Validator.isMobile(phone)) {
            throw new InvalidParamsException(ResultCode.BAD_REQUEST_TELEPHONE_ERROR);
        }
        return phone;
    }

    /**
     * 获取密码
     */
    private String getPassword(Map params) {
        String password = (String) params.get(PhoneLoginConstant.PASSWORD);
        if (StrUtil.isEmpty(password)) {
            throw new InvalidParamsException(ResultCode.FORBIDDEN_USER_PASSWORD_NULL);
        }
        return password;
    }

}
