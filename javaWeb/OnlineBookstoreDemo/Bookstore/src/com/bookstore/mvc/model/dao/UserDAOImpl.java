package com.bookstore.mvc.model.dao;

import com.bookstore.mvc.model.domain.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User getUser(String username) {
        String sql = "SELECT * FROM user WHERE user_name = ?";
        return query(sql, username);
    }
}
