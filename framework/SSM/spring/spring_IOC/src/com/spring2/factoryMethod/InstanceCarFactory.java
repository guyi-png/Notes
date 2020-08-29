package com.spring2.factoryMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * 实例工厂方法: 需要创建本类的实例,在通过实例调用工厂方法
 */
public class InstanceCarFactory {
    private final Map<String, Car> cars;

    public InstanceCarFactory(){
        cars = new HashMap<>();
        cars.put("audi", new Car("audi", 530231));
        cars.put("ford", new Car("ford", 620938));
    }

    public Car getCar(String name){
        return cars.get(name);
    }
}
