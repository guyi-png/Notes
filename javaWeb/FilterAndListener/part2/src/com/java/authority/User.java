package com.java.authority;

import java.util.List;

/**
 * 用户类
 */
public class User {
    // 用户名
    private String username;
    // 用户的权限
    private List<Authority> authorities;

    public User(){}

    public User(String username, List<Authority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

}
