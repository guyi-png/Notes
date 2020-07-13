package com.guyi.DAO;

import com.guyi.utils.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  封装对数据库表的通用操作
 */
public abstract class BaseDAO<E> {

    private Class<E> aClass;

    //再(BaseDAO的)子类实例化时会调用这个代码块
    {   //this指向(BaseDAO的)子类的对象,    获得带泛型的父类
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        Type[] typeArguments = paramType.getActualTypeArguments();//获取带泛型的父类的 参数
        aClass = (Class<E>) typeArguments[0]; //第一个参数

    }

    //DML通用方法
    public int update(Connection connect, String sql, Object... args){
        PreparedStatement ps = null;
        try {
            ps = connect.prepareStatement(sql);
            for (int i =0; i < args.length; i++){
                ps.setObject(i+1, args[i]);
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(ps, null);
        }
        return 0;
    }

    // 查询数据并转为对应对象
    public E getInstance(Connection connect, String sql, Object... args){
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = connect.prepareStatement(sql);
            for (int i =0; i < args.length; i++){
                ps.setObject(i + 1, args[i]);
            }

            resultSet = ps.executeQuery();
            // 获取结果集的元数据
            ResultSetMetaData rsmd = resultSet.getMetaData();
            // 通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();

            if (resultSet.next()){
                E e = aClass.getConstructor().newInstance();
                //处理每一行的中每一列的数据
                for (int i =0; i < columnCount; i++){
                    // 获取每一列数据
                    Object columnValue = resultSet.getObject(i + 1);
                    // 获取字段(属性)名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    // 通过字段名获得类中声明的对应的属性
                    Field field = aClass.getDeclaredField(columnLabel);
                    // 将类中对应的属性设为可访问
                    field.setAccessible(true);
                    field.set(e, columnValue);
                }
                return e;
            }
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(ps, connect, resultSet);
        }
        return null;
    }

    public List<E> getForList(Connection connect, String sql, Object... args){
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<E> list = new ArrayList<>();
        try {
            ps = connect.prepareStatement(sql);

            resultSet = ps.executeQuery();
            // 获取结果集的元数据
            ResultSetMetaData rsmd = resultSet.getMetaData();
            // 通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();

            while (resultSet.next()){
                E e = aClass.getConstructor().newInstance();
                //处理每一行的中每一列的数据
                for (int i =0; i < columnCount; i++){
                    // 获取每一列数据
                    Object columnValue = resultSet.getObject(i + 1);
                    // 获取字段(属性)名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    // 通过字段名获得类中声明的对应的属性
                    Field field = aClass.getDeclaredField(columnLabel);
                    // 将类中对应的属性设为可访问
                    field.setAccessible(true);
                    field.set(e, columnValue);
                }
                list.add(e);
            }
            return list;
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(ps, connect, resultSet);
        }
        return null;
    }

    // 用于查询特殊值的方法
    public <T> T getValue(Connection connect, String sql, Object... args){
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = connect.prepareStatement(sql);
            for (int i =0; i < args.length; i++){
                ps.setObject(i + 1, args[i]);
            }
            resultSet = ps.executeQuery();
            if (resultSet.next()){
                return (T) resultSet.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(ps, null, resultSet);
        }
        return null;
    }
}
