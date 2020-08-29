package com.mybatis.simulate;

import java.io.InputStream;

/**
 * 就是简要模拟实现, 有点令人失望
 * 使用类的加载器读取配置文件的类
 */
public class Resources {
    /**
     * @return  在UserTest测试中    is给了SqlSessionFactoryBuilder的build方法
     */
    public static InputStream getResourceAsStream(String filePath){
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
