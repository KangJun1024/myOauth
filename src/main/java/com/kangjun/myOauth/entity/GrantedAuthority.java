package com.kangjun.myOauth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  已授予的权限
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrantedAuthority {

    /**
     * 权限名称
     */
    private String roleName;

}
