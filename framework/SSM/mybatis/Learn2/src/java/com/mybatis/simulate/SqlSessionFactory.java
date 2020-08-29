package com.mybatis.simulate;

public interface SqlSessionFactory {
    /**
     * 用于打开一个新的SqlSession对象
     * @return
     */
    SqlSession openSession();
}
