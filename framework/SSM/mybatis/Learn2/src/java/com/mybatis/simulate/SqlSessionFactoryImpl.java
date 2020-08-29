package com.mybatis.simulate;

/**
 * SqlSessionFactory的实现类
 */
public class SqlSessionFactoryImpl implements SqlSessionFactory {
    Configuration config;

    public SqlSessionFactoryImpl(Configuration configuration){
        config = configuration;
    }

    /**
     * 用于创建一个新的操作数据库的对象
     * @return
     */
    @Override
    public SqlSession openSession() {
        return new SqlSessionImpl(config);
    }
}
