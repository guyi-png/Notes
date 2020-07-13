package com.guyi.DAO;

import com.guyi.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 *  对实现类 CustomerDAOImpl 的测试
 */
public class Test {
    private static CustomerDAOImpl dao = new CustomerDAOImpl();

    public static void main(String[] args) {
        Connection connect = null;
        try {
            connect = JDBCUtils.getConnection();

            testGetCustomerById(connect);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connect != null){
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.getStackTrace();
                }
            }
        }
    }

    public static void testInsert(Connection connect){
        dao.insert(connect, new Customer(7,"阿勒","1234@qq.com",new Date(3245276512L)));
        System.out.println("ok");
    }

    public static void testDeleteById(Connection connect){
        dao.deleteById(connect, 8);
        System.out.println("ok");
    }

    public static void testUpdateById(Connection connect){
        dao.updateById(connect, 9, new Customer(9,"阿西", "4423@123.com", new Date(4245276512L) ) );
        System.out.println("ok");
    }

    public static void testGetCustomerById(Connection connect){
        Customer customer = dao.getCustomerById(connect, 5);
        System.out.println(customer);
        System.out.println("ok");
    }

    public static void testGetAllCustomer(Connection connect){
        List<Customer> list = dao.getAllCustomer(connect);
        Iterator<Customer> iterator = list.iterator();
        while (iterator.hasNext()){
            Customer customer = iterator.next();
            System.out.println(customer);
        }
        System.out.println("ok");
    }

    public static void testGetCount(Connection connect){
        long count = dao.getCount(connect);
        System.out.println("一共有"+count+"条数据");
        System.out.println("ok");
    }
}
