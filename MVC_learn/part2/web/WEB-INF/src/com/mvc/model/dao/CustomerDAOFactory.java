package com.mvc.model.dao;

import com.mvc.model.customer.CustomerDAO;
import com.mvc.model.customer.CustomerDAOImpl;
import com.mvc.model.customer.CustomerDAOXmlImpl;

import java.util.HashMap;
import java.util.Map;

public class CustomerDAOFactory {
    private CustomerDAOFactory(){
        daos.put("jdbc", new CustomerDAOImpl());
        daos.put("xml", new CustomerDAOXmlImpl());  //放入CustomerDAO的实现类
    }

    // 单例模式，饿汉式
    private static final CustomerDAOFactory instance = new CustomerDAOFactory();

    public static CustomerDAOFactory getInstance(){  //获取实例
        return instance;
    }

    // 实现通过存储的名字(jdbc,xml等)获取相应的实现类
    private Map<String, CustomerDAO> daos = new HashMap<>();

    private static String type = null;

    public static void setType(String type) {
        CustomerDAOFactory.type = type;
    }

    public CustomerDAO getCustomerDAO(){
        return daos.get(type);
    }
}
