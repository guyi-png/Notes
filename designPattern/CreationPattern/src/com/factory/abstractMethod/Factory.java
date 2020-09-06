package com.factory.abstractMethod;

/**
 * 抽象的工厂方法
 */
public abstract class Factory {
    // 让工厂子类具体的实现此方法
    public abstract Pizza createPizza(String type);
}
