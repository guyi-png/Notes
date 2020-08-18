package com.java.listener;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

/**
 * 实现了HttpSessionBindingListener接口的Java类的对象
 * 被绑定到session中或解除绑定时的事件监听器
 *
 * 实现了HttpSessionBindingListener接口的JavaBean对象
 * 可以感知自己被活化(内存中的session)和钝化(磁盘中的session)的事件
 */
public class Customer implements
        HttpSessionBindingListener, HttpSessionActivationListener, Serializable {

    // customer的对象与session绑定时调用
    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("绑定到session");
        System.out.println(httpSessionBindingEvent.getName()
            +httpSessionBindingEvent.getValue()+httpSessionBindingEvent.getSession());
        System.out.println(this==httpSessionBindingEvent.getValue());// true
    }

    // customer的对象与session解绑时调用
    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("解绑");
    }

    // 绑定到session对象中的对象在随着session钝化之前调用
    @Override
    public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {
        System.out.println("从内存中写入磁盘");
    }

    // 绑定到session对象中的对象在随着session活化之后调用
    @Override
    public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {
        System.out.println("从磁盘中读取到内存");
    }
}
