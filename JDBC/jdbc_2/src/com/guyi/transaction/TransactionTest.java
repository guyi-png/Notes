package com.guyi.transaction;

import com.guyi.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *  事务
 *  数据提交后不可回滚
 *  会自动提交的操作： DDL，DML，关闭连接
 */
public class TransactionTest {
    public static void main(String[] args) {
        testIsolation();
    }

    // 一.未考虑事务
    public static void testTransaction(){
        //String sql1 = "update balance set account = account - 100 where user='?'"; 是?,不是'?'
        String sql1 = "update balance set account = account - 100 where user=?";
        testUpdate(sql1, "AA");
        String sql2 = "update balance set account = account + 100 where user=?";
        testUpdate(sql2, "BB");
        System.out.println("ok");
    }

    //未考虑事务的通用方法
    public static int testUpdate(String sql, Object... args){
        Connection connect = null;
        PreparedStatement ps  = null;
        try {
            connect = JDBCUtils.getConnection();

            ps = connect.prepareStatement(sql);
            for (int i = 0; i < args.length; i++){
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(ps, connect);
        }
        return 0;
    }

    // 二.考虑事务
    public static void transactionTest(){
        Connection connect = null;
        try {
            // 使用同一个连接 connect， 避免关闭连接时自动提交
            connect = JDBCUtils.getConnection();
            // 避免自动提交
            connect.setAutoCommit(false);

            //String sql1 = "update balance set account = account - 100 where user='?'"; 是?,不是'?'
            String sql1 = "update balance set account = account - 100 where user=?";
            updateTest(connect, sql1, "AA");
            //如果出现错误
            //int i = 12  / 0;
            String sql2 = "update balance set account = account + 100 where user=?";
            updateTest(connect, sql2, "BB");

            // 以上sql语句为一个事务， 要一起提交
            connect.commit();
            System.out.println("ok");

        } catch (Exception e) {
            System.out.println("操作出现问题");
            if (connect != null) {
                // 让还未提交的数据回滚
                try {
                    connect.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                //让连接恢复自动提交功能，供数据库连接池使用
                try {
                    connect.setAutoCommit(true);
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        } finally {
            JDBCUtils.closeResource(null, connect);
        }
    }

    //考虑事务的通用方法
    public static int updateTest(Connection connect, String sql, Object... args){
        PreparedStatement ps  = null;
        try {
            ps = connect.prepareStatement(sql);

            for (int i = 0; i < args.length; i++){
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(ps, null);
        }
        return 0;
    }

    /*
    *   隔离级别：
    *   read uncommitted
    *   read committed
    *   repeatable read
    *   serializable
    * */
    public static void testIsolation(){
        Connection connect = null;
        try {
            connect = JDBCUtils.getConnection();
            // 获取事务的隔离性
            System.out.println(connect.getTransactionIsolation());  // mysql默认repeatable read
            // 设置事务的隔离级别
            connect.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connect != null){
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
