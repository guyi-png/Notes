package com.guyi.DBUtils;

import com.guyi.DAO.Customer;
import com.guyi.Pool.PoolTest;
import com.guyi.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Apache-DAUtils工具类库，
 * 对jdbc的封装，简化jdbc编码的工作量
 */



public class QueryRunnerTest {
    public void testInsert(){
        Connection connect = null;
        try {
            connect = PoolTest.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "insert into customer(name, email, birth) values(?,?,?)";
            int insertCount = runner.update(connect, sql, "菜虚困", "cxk@qq.com", "1840-07-05");
            System.out.println("插入了"+insertCount+"条记录");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, connect);
        }
    }

    public void testQuery(){
        Connection connect = null;
        try {
            QueryRunner runner = new QueryRunner();
            connect = PoolTest.getConnection();
            String sql = "select id, name, email, birth from customer where id = ?";
            BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);
            Customer customer = runner.query(connect, sql, handler, 1);
            System.out.println(customer);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, connect);
        }
    }

    public void testQuery1(){
        Connection connect = null;
        try {
            QueryRunner runner = new QueryRunner();
            connect = PoolTest.getConnection();
            String sql = "select id, name, email, birth from customer where id = ?";
            BeanListHandler<Customer> handler = new BeanListHandler<>(Customer.class);
            List<Customer> customers = runner.query(connect, sql, handler, 1);
            customers.forEach(System.out::println);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, connect);
        }
    }

    public void testQuery2() {
        Connection connect = null;
        try {
            QueryRunner runner = new QueryRunner();
            connect = PoolTest.getConnection();
            String sql = "select id, name, email, birth from customer where id = ?";
            MapHandler handler = new MapHandler();
            Map<String, Object> map = runner.query(connect, sql, handler, 1);
            System.out.println(map);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, connect);
        }
    }

    public void testQuery3() {
        Connection connect = null;
        try {
            QueryRunner runner = new QueryRunner();
            connect = PoolTest.getConnection();
            String sql = "select count(*) from customer";
            ScalarHandler handler = new ScalarHandler();
            long count = runner.query(connect, sql, handler);
            System.out.println(count);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, connect);
        }
    }
}

