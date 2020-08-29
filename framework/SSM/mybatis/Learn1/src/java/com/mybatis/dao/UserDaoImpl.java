package com.mybatis.dao;

import com.mybatis.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao{
    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<User> getAll() {
        // 使用工厂创建SqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        // 使用session执行查询的操作, com.mybatis.dao.UserDao.getAll全类名加方法
        List<User> list = session.selectList("com.mybatis.dao.UserDao.getAll");
        session.close();
        // 返回查询的结果
        return list;
    }
}
