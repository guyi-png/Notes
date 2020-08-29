package com.spring1.calculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 为 CalculatorImpl 代理
 */
public class CalculatorLoggingProxy {
    // 要代理的对象
    private static final Calculator target = new CalculatorImpl();

    // 获得代理实例
    public static Calculator getProxyInstance() {

        // 匿名实现接口， 当调用代理对象的方法时， 执行invoke()方法
        /*
         * proxy： 正在返回的那个代理对象， 一般情况下，在invoke方法中不使用proxy
         * method：正在被调用的方法
         * args：调用方法时传入的参数
         */
        InvocationHandler handler = (proxy, method, args) -> {
            String name = method.getName();
            int result = 0;
            try {
                // 前置通知
                System.out.println("begin to calculate: "+name);
                // 调用的方法
                result = (int)method.invoke(target, args);
                // 返回通知，上面的方法不报错从会执行
                System.out.println("result:"+result);
            } catch (Exception e) {
                // 异常通知， 出异常才执行
                System.out.println("exception");
                e.printStackTrace();
            }
            // 后置通知
            System.out.println("the result of the calculation: "+ result);
            return result;
        };
        // 代理对象由哪个类加载器负责加载
        ClassLoader classLoader = target.getClass().getClassLoader();
        // 代理对象的类型，获取类中有的那些方法
        Class<?>[] interfaces = target.getClass().getInterfaces();

        return (Calculator) Proxy.newProxyInstance(classLoader, interfaces, handler);
    }
}
