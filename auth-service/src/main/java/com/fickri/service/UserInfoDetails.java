package com.fickri.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fickri.entity.UserInfo;

public class UserInfoDetails implements UserDetails{

    private String name;
    private String password;

    public UserInfoDetails (UserInfo userInfo){
        this.name = userInfo.getName();
        this.password = userInfo.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;    
    }

    @Override
    public String getUsername() {
        return this.name;
    }
}
