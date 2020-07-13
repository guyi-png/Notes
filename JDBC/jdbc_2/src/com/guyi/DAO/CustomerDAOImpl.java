package com.guyi.DAO;

import java.sql.Connection;
import java.util.List;

/**
 * 继承BaseDAO
 * 实现CustomerDAO接口
 */
public class CustomerDAOImpl extends BaseDAO<Customer> implements CustomerDAO{

    @Override
    public void insert(Connection connect, Customer customer) {
        String sql = "insert into customer(name, email, birth) values(?, ?, ?)";
        update(connect, sql, customer.getName(),customer.getEmail(), customer.getBirth());
    }

    @Override
    public void deleteById(Connection connect, int id) {
        String sql = "delete from customer where id = ?";
        update(connect, sql, id);
    }

    @Override
    public void updateById(Connection connect, int id, Customer customer) {
        String sql = "update customer set name=?, email=?, birth=? where id = ?";
        update(connect, sql, customer.getName(), customer.getEmail(), customer.getBirth(), id);
    }

    @Override
    public Customer getCustomerById(Connection connect, int id) {
        String sql = "select id, name, email, birth from customer where id = ?";
        Customer instance = getInstance(connect, sql, id);
        return instance;
    }

    @Override
    public List<Customer> getAllCustomer(Connection connect) {
        String sql = "select id, name, email, birth from customer";
        List<Customer> list = getForList(connect, sql);
        return list;
    }

    @Override
    public long getCount(Connection connect) {
        String sql = "select count(*) from customer";
        return getValue(connect, sql);
    }
}
