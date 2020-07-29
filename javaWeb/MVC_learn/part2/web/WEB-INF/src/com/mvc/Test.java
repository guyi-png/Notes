package com.mvc;

import com.mvc.model.customer.Customer;
import com.mvc.model.customer.CustomerDAOImpl;


public class Test {
    static CustomerDAOImpl  customerDAO = new CustomerDAOImpl();

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setId(2);
        customer.setName("爱莉");
        customer.setAddress("圣枫学院");
        customer.setPhone("7658329231");
        customerDAO.insertIncludeId(customer);
    }
}
