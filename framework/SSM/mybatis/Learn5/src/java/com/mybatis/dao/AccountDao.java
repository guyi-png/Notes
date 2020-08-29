package com.mybatis.dao;

import com.mybatis.entity.Account;
import com.mybatis.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountDao {

    @Select("select * from `account` where uid = #{id}")
    List<Account> getByUid(User user);

    /**
     * 查询所有的账户的同时要获取到当前账户的所属用户的信息(username， address)
     */
//    @Select(
//            "select a.*, u.username, u.address from `account` a left outer join `user` u on u.id = a.uid;"
//    )
    List<Account> getAll();
}
