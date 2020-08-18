package com.bookstore.mvc.model.DAOutils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * jdbc操作的工具类
 */
public class Utils {


    private static DataSource source = null;
    static {
        // 数据源只能被初始化一次
        InputStream is = null;
        try {
            is = Utils.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(is);
            source = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static Connection getConnection() {
        try {
            return source.getConnection();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public static void closeResource(Connection connect){
        //关闭资源
        if (connect != null){
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResource(Connection connect,Statement s){
        //关闭资源
        if (s != null){
            try {
                s.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        closeResource(connect);
    }


    public static void closeResource(Connection connect, Statement s, ResultSet resultSet){
        //关闭资源
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        closeResource(connect,s);
    }

    public static void closeResource(Statement s, ResultSet resultSet){
        if (s != null){
            try {
                s.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
