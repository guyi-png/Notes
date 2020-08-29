package com.mybatis.dao;



import com.mybatis.entity.Criteria;
import com.mybatis.entity.User;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

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
//    @Select("select * from `user`")
    List<User> getAll();

    /**
     * @param user 将user的数据加入数据库
     */
//    @Update("insert into `user`(username,birthday, sex, address) " +
//            "values(#{username}, #{birthday}, #{sex}, #{address})")
    void saveUser(User user);

    /**
     * @param user 通过user的id属性更新对应数据
     */
//    @Update("update `user` set username=#{username} where id=#{id}")
    void update(User user);

    /**
     * 通过id删除对应的数据
     */
//    @Update("delete from `user` where id = #{id}")
    void delete(Integer id);

    /**
     * 通过id查询单行
     */
    User findById(Integer id);

    /**
     * 通过名字查询
     */
    List<User> findByName(String username);

    /**
     * 获取单值, 如条目数等
     */
    int getTotal();

    /**
     * 通过 条件类 筛选查询
     * @return
     */
    List<User> findUserByCriteria(Criteria criteria);
}
