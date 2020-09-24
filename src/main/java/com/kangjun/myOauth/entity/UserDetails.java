package com.kangjun.myOauth.entity;


import java.util.List;

public interface UserDetails {

    /**
     * 用户权限
     */
    List<GrantedAuthority> getAuthorities();

    /**
     * 用户ID
     */
    Long getUserId();

    /**
     * 用户密码
     */
    String getPassword();

    /**
     * 用户姓名
     */
    String getUsername();

    /**
     * 账户是否锁定
     */
    boolean isAccountNonLocked();


}
