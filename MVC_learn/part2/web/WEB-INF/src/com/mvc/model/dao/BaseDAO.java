package com.mvc.model.dao;


import com.mvc.model.jdbcUtils.Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *  data access object
 */
public class BaseDAO<T> {
    private Class<T> aClass;
    private QueryRunner queryRunner = new QueryRunner();

    public BaseDAO(){
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType){
            ParameterizedType paramType = (ParameterizedType) superclass;
            Type[] typeArguments = paramType.getActualTypeArguments();
            if (typeArguments != null && typeArguments.length >0){
                if (typeArguments[0] instanceof Class){
                    aClass = (Class<T>) typeArguments[0];
                }
            }
        }

    }

    public void update(String sql, Object... args){
        Connection connect = null;
        try {
            connect = Utils.getConnection();
            queryRunner.update(connect, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            Utils.closeResource(connect);
        }
    }

    public T getInstance(String sql, Object... args){
        Connection connect = null;
        try {
            connect = Utils.getConnection();
            BeanHandler<T> handler = new BeanHandler<>(aClass);

            return queryRunner.query(connect, sql, handler, args);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            Utils.closeResource(connect);
        }
        return null;
    }

    public List<T> getForList(String sql, Object... args){
        Connection connect = null;
        try {
            connect = Utils.getConnection();
            return queryRunner.query(connect, sql, new BeanListHandler<T>(aClass) , args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            Utils.closeResource(connect);
        }
        return null;
    }

    public <E> E getValue(String sql, Object... args){
        Connection connect = null;
        try {
            connect = Utils.getConnection();
            return queryRunner.query(connect, sql, new ScalarHandler<E>(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            Utils.closeResource(connect);
        }
        return null;
    }
}

