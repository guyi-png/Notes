package com.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  动态代理
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        Human human = new SuperMan();
        //proxyMan 为代理类的对象
        Human proxyMan = (Human)ProxyFactory.getProxyInstance(human);
        String belief = proxyMan.getBelief();
        System.out.println(belief);
        proxyMan.eat("奥利给");
        //动态代理的动态
        NikeClothFactory nike = new NikeClothFactory();
        ClothFactory proxyFactory = (ClothFactory)ProxyFactory.getProxyInstance(nike);
        proxyFactory.produceCloth();
    }
}

interface Human{
    String getBelief();
    void eat(String food);
}

//被代理类
class SuperMan implements Human{
    @Override
    public void eat(String food) {
        System.out.println("superman喜欢吃"+ food);
    }

    @Override
    public String getBelief() {
        return "I believe I can fly!!";
    }
}
//AOP
class CommonUtil{
    public void method1(){
        System.out.println("通用方法一");
    }

        //xxxxx

    public void method2(){
        System.out.println("通用方法二");
    }
}

class ProxyFactory{
    public static Object getProxyInstance(Object obj) {  //传入被代理类的对象
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(obj);
        //下面的方法通过反射动态创建代理类的对象，并且代理类的对象调用方法时会调用被代理类的对象的方法
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
    }
}

class MyInvocationHandler implements InvocationHandler{
    private Object obj; //初始化为被代理对象
    public void bind(Object obj){  //传入被代理类的对象
        this.obj = obj;
    }
    @Override          //当通过代理类的对象调用某方法，就会自动调用invoke()
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        CommonUtil cu = new CommonUtil();
        cu.method1(); //调用通用方法
        Object returnValue= method.invoke(obj,args);    //此method作为代理类和被代理类调用的方法
        cu.method2();
        return returnValue;
    }
}