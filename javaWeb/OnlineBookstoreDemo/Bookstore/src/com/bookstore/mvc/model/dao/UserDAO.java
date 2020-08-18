package com.bookstore.mvc.model.dao;

import com.bookstore.mvc.model.domain.User;

public interface UserDAO {
    /**
     * 根据用户名获取User对象
     * @param username
     * @return
     */
    User getUser(String username);
}
