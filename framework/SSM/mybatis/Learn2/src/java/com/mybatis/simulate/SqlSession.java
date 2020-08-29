package com.mybatis.simulate;

import com.mybatis.dao.UserDao;

/**
 * 自定义mybatis中和数据库交互的核心类
 * 它里面可以创建dao的代理对象
 */
public interface SqlSession {
    /**
     * @param clazz 被代理类
     * @param <T>
     * @return
     */
    UserDao getMapper(Class<?> clazz);

    /**
     * 释放资源
     */
    void close();
}
