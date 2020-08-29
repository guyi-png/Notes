package com.mybatis.simulate;

import com.mybatis.dao.UserDao;
import com.mybatis.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlSessionImpl implements SqlSession {
    private Configuration config;
    private Connection connect;

    public SqlSessionImpl(Configuration config){
        this.config = config;
        this.connect = Utils.getConnection(config);
    }

    /**
     * 用于动态创建代理对象
     * 具体实现 略
     * 硬代码代替
     * @param clazz 被代理类
     * @param <T>
     * @return
     */
    @Override
    public UserDao getMapper(Class<?> clazz) {

        UserDao userDao = () -> {
            List<User> users = new ArrayList<>();
            try {
                Map<String, Mapper> mappers = config.getMappers();

                Mapper mapper = mappers.get("com.mybatis.dao.UserDao.getAll");

                assert mapper != null;

                String sql = mapper.getQueryString();
                PreparedStatement statement = connect.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                // 获取的数据装入实体类中
                // 硬代码，直接装入
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String sex = resultSet.getString("sex");
                    String address = resultSet.getString("address");
                    Date birthday = resultSet.getDate("birthday");

                    User user = new User(id, username, birthday, sex, address);
                    users.add(user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return users;
        };
        return userDao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        try {
            connect.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}