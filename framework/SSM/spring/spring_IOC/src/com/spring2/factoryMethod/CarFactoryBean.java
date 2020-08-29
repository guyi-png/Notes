package com.spring2.factoryMethod;


import org.springframework.beans.factory.FactoryBean;


/**
 * 实现了 FactoryBean<T> 接口也可以创建工厂方法
 */
public class CarFactoryBean implements FactoryBean<Car> {
    // 返回bean的对象
    @Override
    public Car getObject() throws Exception {
        return new Car("bmw", 470234);
    }

    // 返回bean的类型
    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }
}
