package com.mybatis.dao;

import com.mybatis.entity.Criteria;
import com.mybatis.entity.User;
import com.mybatis.entity.UserTwo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 数据库中的字段与UserTwo类的属性名不一致时， 首先想到可以为字段 取别名,
 * 另外在mybatis中可以通过配置的方式映射, 具体配置查看UserDao.xml文件
 */
public interface UserTwoDao {
    @Select("select * from `user`")
    List<UserTwo> getAll();

    @Update("insert into `user`(username,birthday, sex, address) " +
            "values(#{username}, #{userBirthday}, #{userSex}, #{userAddress})")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "userId",
            keyColumn = "id", resultType = Integer.class, before = false)
    void saveUser(UserTwo user);

    @Update("update `user` set username=#{username} where id=#{userId}")
    void update(UserTwo user);

    @Update("delete from `user` where id = #{id}")
    void delete(Integer id);

    @Select("select * from `user` where id = #{id}")
    User findById(Integer id);

    @Select("select * from `user` where username like concat('%', #{name}, '%')")
    List<User> findByName(String username);

    @Select("select count(*) from `user`")
    int getTotal();

    @Select("select * from `user` where username like concat('%', #{userTwo.username}, '%') and id &lt; #{maxId}")
    List<User> findUserByCriteria(Criteria criteria);
}
