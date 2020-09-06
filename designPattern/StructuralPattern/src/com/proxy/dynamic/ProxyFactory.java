package com.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Java动态代理:
 * https://www.jianshu.com/p/95970b089360
 */
public class ProxyFactory {
    // 维护一个对象
    private Object target;   //目标对象

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 提高target对象获取代理实例
    public Object getProxyInstance(){
        ClassLoader loader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        // 匿名实现接口
        InvocationHandler handler = new InvocationHandler() {
            /**
             * @param proxy 代理后的实例对象。
             *              可以使用反射获取代理对象的信息,也就是proxy.getClass().getName().
             *              可以将代理对象返回以进行连续调用，这就是proxy存在的目的，
             *              因为this并不是代理对象。
             * @param method，对象被调用方法。
             * @param args，调用时的参数。
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("看把你能的????");
                // 调用被代理对象(target)的对应方法
                Object returnResult = method.invoke(target, args);  //调用被代理类的对应方法
                System.out.println(returnResult);//俺从被代理类的方法来的
                System.out.println("-----------------");
                // 返回代理对象
                return proxy;
            }
        };
// newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
        /**
         * 说明： ClassLoader 指定当前目标对象使用的类加载器
         * interfaces 目标对象实现的接口类型，使用泛型方法确定类型
         * invocationHandler 事件处理，执行目标对象的方法时，会触发事件处理器方法，
         * 会把当前执行的目标对象的方法作为参数传入
         *
         * InvocationHandler作用就是，当代理对象的原本方法被调用的时候，
         * 会绑定执行一个方法，这个方法就是InvocationHandler里面定义的内容，
         * 同时会替代原本方法的结果返回。
         *
         * 返回一个代理对象
         */
        return Proxy.newProxyInstance(loader, interfaces, handler);
    }
}
