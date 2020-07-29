package com.mvc.model.customer;

import com.mvc.model.dao.Criteria;

import java.util.List;

public interface CustomerDAO {

    List<Customer> getForListByCriteria(Criteria criteria);

    List<Customer> getAllCustomer();

    void insert(Customer customer);

    void insertIncludeId(Customer customer);

    Customer getCustomerById(int id);

    void alter(Customer customer);

    void deleteById(int id);

    long getCount();

    long getCountWithName(String name);
}
