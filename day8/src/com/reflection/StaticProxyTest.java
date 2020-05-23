package com.reflection;

/**
 * 静态代理:
 * 特点： 代理类和被代理类在编译期间就已经确定了
 *
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        NikeClothFactory nike = new NikeClothFactory();
        ProxyClothFactory proxy = new ProxyClothFactory(nike);
        proxy.produceCloth();
    }
}

interface ClothFactory{
    void produceCloth();
}

//代理类
class ProxyClothFactory implements ClothFactory{
    private final ClothFactory factory; //用被代理的类的对象初始化化时使用

    public ProxyClothFactory(ClothFactory factory){
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂做准备工作");
        factory.produceCloth();   //调用被代理类的对象的方法
        System.out.println("代理工厂做一些后续的工作");
    }
}

//被代理类
class NikeClothFactory implements ClothFactory{
    @Override
    public void produceCloth() {
        System.out.println("nike工厂生产一批衣服");
    }
}
