package com.mybatis.dao;

import com.mybatis.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    /**
     * 获取所有User数据并包含至多的Account数据
     */
    List<User> getAllIncludeAccount();

    /**
     * 获取所有User数据并包含至多的Role数据
     */
    List<User> getAllIncludeRole();


    @Select("select * from `user` where id = #{id}")
    User getById(Integer id);
}
