package com.mvc.model.customer;

import com.mvc.model.dao.BaseDAO;
import com.mvc.model.dao.Criteria;

import java.util.List;

public class CustomerDAOImpl extends BaseDAO<Customer> implements CustomerDAO {

    // 通过条件查
    @Override
    public List<Customer> getForListByCriteria(Criteria criteria) {
        String sql = "SELECT id, name, address, phone FROM customers WHERE name LIKE ? AND address LIKE ? AND phone LIKE ?";
        return getForList(sql, criteria.getName(), criteria.getAddress(), criteria.getPhone());
    }

    @Override
    public List<Customer> getAllCustomer() {
        String sql = "SELECT * FROM customers";
        return getForList(sql);
    }

    @Override
    public void insert(Customer customer) {
        String sql = "INSERT INTO customers(name,address,phone) VALUES(?, ?, ?)";
        update(sql, customer.getName(), customer.getAddress(), customer.getPhone());
    }

    @Override
    public void insertIncludeId(Customer customer) {
        String sql = "INSERT INTO customers VALUES(?,?,?,?)";
        update(sql,customer.getId(), customer.getName(),customer.getAddress(),customer.getPhone());
    }

    @Override
    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        return getInstance(sql, id);
    }

    @Override
    public void alter(Customer customer) {
        String sql = "UPDATE customers SET name=?, address=?, phone=? WHERE id = ?";
        update(sql, customer.getName(), customer.getAddress(), customer.getPhone(), customer.getId());
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM customers WHERE id= ?";
        update(sql, id);
    }

    @Override
    public long getCount() {
        String sql = "SELECT COUNT(*) FROM customers";
        return getValue(sql);
    }

    @Override
    public long getCountWithName(String name) {
        String sql = "SELECT COUNT(*) FROM customers WHERE name= ? ";
        return getValue(sql, name);
    }
}
