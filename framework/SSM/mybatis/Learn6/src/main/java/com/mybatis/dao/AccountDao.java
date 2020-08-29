package com.mybatis.dao;

import com.mybatis.entity.Account;
import com.mybatis.entity.User;

import java.util.List;

public interface AccountDao {
    /**
     * 查询所有的账户的同时要获取到当前账户的所属用户的信息(username， address)
     */
//    @Select(
//            "select a.*, u.username, u.address from `account` a left outer join `user` u on u.id = a.uid;"
//    )
    List<Account> getAll();

    List<Account> getByUid(Integer uid);
}
