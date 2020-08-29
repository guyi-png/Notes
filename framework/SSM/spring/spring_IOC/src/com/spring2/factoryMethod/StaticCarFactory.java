package com.spring2.factoryMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态的工厂方法
 */
public class StaticCarFactory {
    private static final Map<String, Car> cars = new HashMap<>();

    static {
        cars.put("audi", new Car("audi", 300000));
        cars.put("ford", new Car("ford", 400000));
    }

    public static Car getCar(String name){
        return cars.get(name);
    }
}
