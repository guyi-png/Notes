package com.mybatis.dao;


import com.mybatis.entity.User;

import java.util.List;

public interface UserDao {

    User getById(Integer id);

    /**
     * 获取所有User数据并包含至多的Account数据
     */
    List<User> getAllIncludeAccount();

    /**
     * 获取所有User数据并包含至多的Role数据
     */
    List<User> getAllIncludeRole();
}
