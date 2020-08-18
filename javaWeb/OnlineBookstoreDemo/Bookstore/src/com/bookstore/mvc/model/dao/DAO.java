package com.bookstore.mvc.model.dao;

import java.util.List;

public interface DAO<T> {
    /**
     * 向数据库插入数据
     * @param sql
     * @param args
     * @return
     */
    long insert(String sql, Object... args);

    /**
     * 更新数据库中的数据
     * @param sql
     * @param args
     */
    void update(String sql, Object... args);

    /**
     * 查询数据库中的单行数据
     * @param sql
     * @param args
     * @return
     */
    T query(String sql, Object... args);

    /**
     * 查询数据库中的多行数据
     * @param sql
     * @param args
     * @return
     */
    List<T> queryForList(String sql, Object... args);

    /**
     * 向数据库批量update数据
     * @param sql
     * @param args
     */
    void batch(String sql, Object[]... args);

    /**
     * 查看某个单值
     * @param sql
     * @param args
     * @param <E>
     * @return
     */
    <E> E querySingleValue(String sql, Object... args);
}
