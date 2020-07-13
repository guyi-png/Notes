package com.guyi.connection;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.sql.*;
import java.util.Properties;

/**
 * 以MySQL为例,  连接数据库的方法
 */
public class ConnectionTest {
    public static void main(String[] args) throws Exception {
        getConnection();
    }

    // 方式一
    public static void testConnection1() throws SQLException {
        // driver驱动，需要导入驱动的jar包
        Driver driver = new com.mysql.jdbc.Driver();
        /*
        jdbc:mysql:  协议
        localhost:3306 服务器和端口号
        test为某个数据库的名
        * */
        String url = "jdbc:mysql://localhost:3306/test";
        // 将用户和密码等放到properties
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password", "root"); //mysql的用户名和密码

        //获取连接
        Connection connect = driver.connect(url, info);

        System.out.println(connect);
    }

    //方式二： 对方式一的迭代
    public static void testConnection2() throws Exception{
        //使用反射获取Driver实现类的对象
        Class aClass = Class.forName("com.mysql.jdbc.Driver");
        Constructor constructor = aClass.getConstructor();
        Driver driver = (Driver)constructor.newInstance();

        //要连接的数据库
        String url = "jdbc:mysql://localhost:3306/test";
        //设置用户和密码
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");
        //获取连接
        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }

    //方式三： 将Driver换为DriverManager
    public static void testConnection3() throws Exception{
        Class aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver)aClass.getConstructor().newInstance();

        // 注册驱动
        DriverManager.registerDriver(driver);

        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root", password = "root";

        Connection connect = DriverManager.getConnection(url, user, password);
        System.out.println(connect);
    }

    //方式四, 简化
    public static void testConnection4() throws Exception {
        // 在加载Driver类时自动就注册了驱动
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root", password = "root";

        Connection connect = DriverManager.getConnection(url, user, password);
        System.out.println(connect);
    }

    //方式五, 再简化
    public static void testConnection5() throws Exception {
        /*
        lib\mysql-connector-java-5.1.47.jar!\META-INF\services\java.sql.Driver
        中有mysql的驱动类名
        * */
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root", password = "root";

        Connection connect = DriverManager.getConnection(url, user, password);
        System.out.println(connect);
    }

    //方式六， 实用,解耦，数据与代码的分离，减少打包
    public static void getConnection() throws Exception {
        //读取配置文件中的信息
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String aClass = properties.getProperty("driverClass");

        //注册驱动
        Class.forName(aClass);

        Connection connect = DriverManager.getConnection(url, user, password);

        System.out.println(connect);
    }
}
