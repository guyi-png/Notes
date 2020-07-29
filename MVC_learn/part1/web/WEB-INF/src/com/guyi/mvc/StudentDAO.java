package com.guyi.mvc;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StudentDAO {
    public List<Student> getAll(){
        // 用于保存数据
        List<Student> students = new ArrayList<>();
        Connection connect = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //jdbc技术
            Class.forName("com.mysql.jdbc.Driver");
//            InputStream is = ClassLoader.getSystemResourceAsStream("jdbc.properties");  //inStream parameter is null
            InputStream is = StudentDAO.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(is);
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            connect = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM students;";
            statement = connect.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String gender = resultSet.getString(3);
                int age = resultSet.getInt(4);
                // 将获取的数据放入对象
                Student student = new Student(id,name,gender,age);
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (connect != null)
                    connect.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return students;
    }

    public void deleteById(int id){
        InputStream is = null;
        Connection connect = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            is = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = Properties.class.getConstructor().newInstance();
            properties.load(is);
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connect = DriverManager.getConnection(url, username, password);
            String sql = "DELETE FROM students WHERE id =?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1,id);
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (connect != null)
                    connect.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
