package com.guyi.Pool;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * C3P0数据库连接池
 * DBCP数据库连接池
 * Druid数据库连接池
 * ...
 */


public class PoolTest {
    public static void main(String[] args) {
        Connection connect =  null;
        try {
            connect = PoolTest.getConnection();
            System.out.println(connect);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connect != null)
                try {
                    connect.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }

    }

    private static final DataSource source = null;
    static {
        try {
            //加载配置文件
            InputStream is = ClassLoader.getSystemResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(is);
            //读取配置文件，创建数据库连接池源
            source = DruidDataSourceFanctory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        // 通过源获取连接
        return source.getConnection();
    }
}
