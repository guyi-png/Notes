package com.guyi.DAO;

import java.sql.Connection;
import java.util.List;

/**
 * 用于customer表的操作
 */
public interface CustomerDAO {
    // 将customer的对象添加到数据库
    void insert(Connection connect, Customer customer);
    //通过id删除数据库表中的信息
    void deleteById(Connection connect, int id);
    // 通过id找到对应的数据，并将数据修改为customer提供的信息
    void updateById(Connection connect, int id, Customer customer);
    // 提供id查询对应的数据
    Customer getCustomerById(Connection connect, int id);
    //查询表中所有记录
    List<Customer> getAllCustomer(Connection connect);
    // 查询表数据的条目数
    long getCount(Connection connect);
}
