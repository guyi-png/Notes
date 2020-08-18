package com.bookstore.mvc.model.service;

import com.bookstore.mvc.model.dao.UserDAOImpl;
import com.bookstore.mvc.model.domain.User;

public class UserService {
    private final  UserDAOImpl userDAO =  new UserDAOImpl();

    //通过用户名获取用户信息
    public User getUserByUsername(String name){
        return userDAO.getUser(name);
    }
}
