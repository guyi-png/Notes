package com.mybatis.simulate;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 用于创建数据源的
 */
public class Utils {

    public static Connection getConnection(Configuration config) {
        try {
            Class.forName(config.getDriver());

            return DriverManager.getConnection(config.getUrl(),
                    config.getUsername(), config.getPassword());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
