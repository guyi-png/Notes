package com.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *  需要导入的jar包； asm.jar  asm-common.jar  asm-tree.jar  cglib.jar
 */
public class ProxyFactory implements MethodInterceptor {
    // 聚合 被代理对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 返回代理实例
    public Object getProxyInstance(){
        // 创建一个工具类
        Enhancer enhancer = new Enhancer();  //enhancer n. [遗] 增强子；强化剂；增加者
        // 设置它的父类
        enhancer.setSuperclass(target.getClass());
        // 设置回调函数
        enhancer.setCallback(this);
        // 创建子类对象， 即代理对象
        return enhancer.create();
    }

    // 重写intercept方法，会调用目标对象的方法
    /**
     *
     * @param proxy cglib生成的代理对象
     * @param method 被代理对象方法
     * @param args 方法入参
     * @param methodProxy 代理方法
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib代理模式");

        Object returnResult = method.invoke(target, args);
        System.out.println(returnResult);
        System.out.println("---------------------");
        return proxy;
    }
}
