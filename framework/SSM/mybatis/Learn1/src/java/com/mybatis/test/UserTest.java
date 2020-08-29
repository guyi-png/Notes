package com.mybatis.test;

import com.mybatis.dao.UserDao;
import com.mybatis.dao.UserDaoImpl;
import com.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试
 */
public class UserTest {
    public static void main(String[] args) throws IOException {
        //读取配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //创建SqlSessionFactory
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = factoryBuilder.build(is);
        ////////////////////////////////////////////
        // 使用接口实现类调用方法
//        UserDao userDao = new UserDaoImpl(factory);
//        List<User> users = userDao.getAll();
        ////////////////////////////////////////////

        //使用SqlSessionFactory工厂
        SqlSession session = factory.openSession();
        //使用SqlSession创建Dao接口的代理对象
        UserDao userDao = session.getMapper(UserDao.class);
        // 使用代理对象执行方法
        List<User> users = userDao.getAll();
        for (User user : users){
            System.out.println(user);
        }
        // 释放资源
        session.close();
        is.close();
    }
}
