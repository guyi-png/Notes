package com.guyi.preparedStatement;

import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * statement 在操作数据时， 会出现sql注入的问题，避免使用
 * 通过preparedStatement对数据库增删改查(CRUD)
 * 以 增为例
 */
public class PreparedStatementTest {
    public static void main(String[] args) throws Exception {
        //建立连接
        InputStream is = ClassLoader.getSystemResourceAsStream("jdbc.properties");
        Properties properties = Properties.class.getConstructor().newInstance();
        properties.load(is);

        Class.forName(properties.getProperty("driverClass"));

        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        Connection connect = DriverManager.getConnection(url , user , password);

        //插入
        testInsert(connect);

    }

    public static void testInsert(Connection connect){
        PreparedStatement ps = null;
        try{
            //sql对应mysql中的sql语句, 进行插入操作
            String sql = "INSERT INTO customer(name, email, birth) VALUES(?,?,?)";  //?是占位符
            ps = connect.prepareStatement(sql);
            // 填充占位符, 中文有问题
            ps.setString(1,"吉安");  //对应customer(name, email, birth)中的name
            ps.setString(2, "ja@qq.com");//对应customer(name, email, birth)中的email

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("1623-1-5");
            ps.setDate(3, new Date(date.getTime()));

            //执行操作
            ps.execute();

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            //关闭资源
            if (ps != null){
                try {
                    ps.close();
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

    }
}
