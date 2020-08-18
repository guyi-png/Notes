package com.bookstore.mvc.model.connection;

import java.sql.Connection;

public class ConnectContext {
    private ConnectContext(){}

    private static final ConnectContext instance = new ConnectContext();

    public static ConnectContext getInstance(){
        return instance;
    }


    private final ThreadLocal<Connection> connectionThreadLocal
            = new ThreadLocal<>();

    // 让connect与线程本地存储绑定
    public void bind(Connection connect){
        connectionThreadLocal.set(connect);
    }

    // 获取同一线程的connect
    public Connection get(){
        return connectionThreadLocal.get();
    }

    public void remove(){
        connectionThreadLocal.remove();
    }
}
