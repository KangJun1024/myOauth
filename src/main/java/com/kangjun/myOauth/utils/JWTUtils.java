package com.kangjun.myOauth.utils;

import cn.hutool.system.UserInfo;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 */
@UtilityClass
public class JWTUtils {

    private static final String SUBJECT_PREFIX = "lr_health";

    private static final String SECRET_KEY = "lr_heaht_Ihhdksjui";

    /**
     * 用户登录成功后生成Jwt
     */
    public String createJWT(long ttlMillis, UserInfo userInfo) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Map<String, Object> claims = new HashMap<>();
        if (userInfo != null) {
            claims.put("userInfo", userInfo);
        }
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setSubject(SUBJECT_PREFIX)

                .signWith(signatureAlgorithm, SECRET_KEY);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }
}
