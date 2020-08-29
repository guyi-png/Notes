package com.mybatis.dao;



import com.mybatis.entity.User;
import com.mybatis.simulate.Select;

import java.util.List;

/**
 * 用户的持久层接口
 *
 */
public interface UserDao {
    /**
     * @return 返回所有的数据
     */
    // 通过注解写sql语句
    @Select("select * from `user`")
    List<User> getAll();
}
