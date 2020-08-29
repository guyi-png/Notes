package com.spring2.lifecycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ioc容器对bean生命周期进行管理的过程：
 *  - 通过构造器或工厂方法创建bean实例
 *  - 为bean的属性设置 值或对其他bean的引用
 *  - 将bean实例传递给bean后置处理器的postProcessBeforeInitialization方法
 *  - 调用bean的初始化方法 init-method
 *  - 将bean实例传递给bean后置处理器的postProcessAfterInitialization方法
 *  - 使用bean实例
 *  - 当ioc容器关闭时， 调用bean的销毁方法
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new
                ClassPathXmlApplicationContext("bean-lifecycle.xml");
        Car car = (Car) ctx.getBean("car");
        ctx.close(); //关闭ioc容器
        System.out.println(car);
    }
}
