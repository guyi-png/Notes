package com.mvc.model.customer;

import com.mvc.model.dao.Criteria;

import java.util.List;

                /*暂无具体实现*/

public class CustomerDAOXmlImpl implements CustomerDAO {
    @Override
    public List<Customer> getForListByCriteria(Criteria criteria) {
        System.out.println("getForListByCriteria");
        return null;
    }

    @Override
    public List<Customer> getAllCustomer() {
        System.out.println("getAllCustomer");
        return null;
    }

    @Override
    public void insert(Customer customer) {
        System.out.println("insert");
    }

    @Override
    public void insertIncludeId(Customer customer) {
        System.out.println("insertIncludeId");
    }

    @Override
    public Customer getCustomerById(int id) {
        System.out.println("getCustomerById");
        return null;
    }

    @Override
    public void alter(Customer customer) {
        System.out.println("alter");
    }

    @Override
    public void deleteById(int id) {
        System.out.println("deleteById");
    }

    @Override
    public long getCount() {
        System.out.println("getCount");
        return 0;
    }

    @Override
    public long getCountWithName(String name) {
        System.out.println("getCountWithName");
        return 0;
    }
}
