package com.guyi.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 操作数据库的工具类
 */
public class JDBCUtils {

    public static Connection getConnection() throws Exception{
        //建立连接
        InputStream is = ClassLoader.getSystemResourceAsStream("jdbc.properties");
        Properties properties = Properties.class.getConstructor().newInstance();
        properties.load(is);

        String aClass = properties.getProperty("driverClass");
        Class.forName(aClass);

        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        return DriverManager.getConnection(url , user , password);
    }

    public static void closeResource(Statement s, Connection connect){
        //关闭资源
        if (s != null){
            try {
                s.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connect != null){
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResource(Statement s, Connection connect, ResultSet resultSet){
        //关闭资源
        if (s != null){
            try {
                s.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connect != null){
            try {
                connect.close();
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
