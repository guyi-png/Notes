package com.bookstore.mvc.model.dao;


import com.bookstore.mvc.model.DAOutils.Utils;
import com.bookstore.mvc.model.connection.ConnectContext;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.List;

/**
 *  data access object
 */
public class BaseDAO<T> implements DAO<T> {
    private Class<T> clazz;
    private final QueryRunner queryRunner = new QueryRunner();

    public BaseDAO(){
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType){
            ParameterizedType paramType = (ParameterizedType) superclass;
            Type[] typeArguments = paramType.getActualTypeArguments();
            if (typeArguments != null && typeArguments.length >0){
                if (typeArguments[0] instanceof Class){
                    clazz = (Class<T>) typeArguments[0];
                }
            }
        }
    }

    /**
     * @param sql
     * @param args
     * @return  返回insert时项的id
     */
    public long insert(String sql, Object... args){
        Connection connect = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        long id = 0;
        try {
            connect = ConnectContext.getInstance().get();

            statement =               // 生成键
                    connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (args != null){
                for (int i =0; i < args.length; i++){
                    statement.setObject(i+1, args[i]);
                }
            }
            statement.executeUpdate();
            // 获取生成的键
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                id = resultSet.getLong(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            Utils.closeResource(statement, resultSet);
        }
        return id;
    }

    public void update(String sql, Object... args){
        Connection connect = null;
        try {
            connect = ConnectContext.getInstance().get();
            queryRunner.update(connect, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public T query(String sql, Object... args){
        Connection connect = null;
        try {
            connect = ConnectContext.getInstance().get();
            return queryRunner.query(connect, sql, new BeanHandler<>(clazz), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<T> queryForList(String sql, Object... args){
        Connection connect = null;
        try {
            connect = ConnectContext.getInstance().get();
            return queryRunner.query(connect, sql, new BeanListHandler<>(clazz) , args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void batch(String sql, Object[]... args){
        Connection connect = null;
        try {
            connect = ConnectContext.getInstance().get();
            queryRunner.batch(connect, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public <E> E querySingleValue(String sql, Object... args){
        Connection connect = null;
        try {
            connect = ConnectContext.getInstance().get();
            return queryRunner.query(connect, sql, new ScalarHandler<>(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}

