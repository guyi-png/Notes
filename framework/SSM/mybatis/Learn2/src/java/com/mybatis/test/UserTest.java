package com.mybatis.test;

import com.mybatis.dao.UserDao;
import com.mybatis.entity.User;

import com.mybatis.simulate.Resources;
import com.mybatis.simulate.SqlSession;
import com.mybatis.simulate.SqlSessionFactory;
import com.mybatis.simulate.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试
 */
public class UserTest {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = factoryBuilder.build(is);
        SqlSession session = factory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        List<User> users = userDao.getAll();
        for (User user : users){
            System.out.println(user);
        }
        session.close();
        is.close();
    }
}
