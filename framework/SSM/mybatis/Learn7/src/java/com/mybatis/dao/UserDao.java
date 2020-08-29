package com.mybatis.dao;

import com.mybatis.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

// 使用二级缓存
@CacheNamespace(blocking = true)
public interface UserDao {
    @Select("select * from `user`")
    @ResultMap("userMap")
    List<User> getAll();

    @Select("select * from `user` where id = #{id}")
    // 配置数据库字段和对象的属性映射关系
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "address", column = "address"),
            // 配置一对多
            @Result(
                    property = "accounts",
                    column = "id",
                    many = @Many(select = "com.mybatis.dao.AccountDao.getByUid",
                    fetchType = FetchType.LAZY)
            )
    })
    User findById(Integer id);

    @Select("select * from `user` where username like concat('%', #{name}, '%')")
    @ResultMap("userMap")
    List<User> findByName(String username);
}
