package com.java.authority;

import java.util.Objects;

/**
 * 权限类
 */
public class Authority {
    // 需要显示的权限名
    private String displayName;
    // 权限对应的url
    private String url;

    public Authority(){}

    public Authority(String displayName, String url) {
        this.displayName = displayName;
        this.url = url;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        // 只比较url值
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;
        Authority authority = (Authority) o;
        return url.equals(authority.url);
    }
}
