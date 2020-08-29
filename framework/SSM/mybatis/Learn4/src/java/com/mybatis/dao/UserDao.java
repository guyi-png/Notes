package com.mybatis.dao;

import com.mybatis.entity.Criteria;
import com.mybatis.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao {
    @Select("select * from `user`")
    List<User> getAll();

    @Update("insert into `user`(username,birthday, sex, address) " +
            "values(#{username}, #{userBirthday}, #{userSex}, #{userAddress})")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "userId",
            keyColumn = "id", resultType = Integer.class, before = false)
    void saveUser(User user);

    @Update("update `user` set username=#{username} where id=#{userId}")
    void update(User user);

    @Update("delete from `user` where id = #{id}")
    void delete(Integer id);

    @Select("select * from `user` where id = #{id}")
    User findById(Integer id);

    @Select("select * from `user` where username like concat('%', #{name}, '%')")
    List<User> findByName(String username);

    @Select("select count(*) from `user`")
    int getTotal();

    /**
     * 根据传入参数的属性 做条件返回数据
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据 筛选类 获取数据
     */
    List<User> findUserByCriteria(Criteria criteria);
}
