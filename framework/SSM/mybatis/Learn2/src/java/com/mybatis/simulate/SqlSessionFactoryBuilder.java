package com.mybatis.simulate;

import java.io.InputStream;

/**
 * 就是简要模拟实现
 * 用于创建一个SqlSessionFactory的对象
 */
public class SqlSessionFactoryBuilder {
    /**
     *
     * @param is  用于通过Parser.parse()方法读取xml文件
     * @return
     */
    public SqlSessionFactory build(InputStream is){
        Configuration configuration = Parser.parse(is);
        // 给SqlSessionFactoryImpl传入配置信息
        return new SqlSessionFactoryImpl(configuration);
    }
}
