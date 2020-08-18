package com.java.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 三域的监听器
 * request的生命周期：当发送一个请求时创建，当一个响应返回时销毁
 *
 * session的生命周期： 当第一次访问web应用的一个servlet时，并且Servlet中还需要创建session对象时创建，
 * 在session过期，显示调用invalidate()方法，当前web应用被卸载时销毁, 关闭浏览器并不意味着session被销毁
 *
 * application的生命周期: 贯穿于当前的WEB应用的生命周期，WEB应用加载而创建，WEB应用卸载而销毁
 */
public class TestListener
        implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    // application域创建时调用
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("contextInitialized");
    }

    // application域销毁时调用
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("contextDestroyed");
    }

    // request域创建时调用
    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

    }

    // request域销毁时调用
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    // session域创建时调用
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("session创建");
    }

    // session域销毁时调用
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("session销毁");
    }
}
