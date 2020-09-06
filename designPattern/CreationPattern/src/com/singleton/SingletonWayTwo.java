package com.singleton;

/**
 * 饿汉式（静态代码块）
 */
public class SingletonWayTwo {
    private SingletonWayTwo (){}

    private static SingletonWayTwo instance;

    //使用静态代码块初始化实例
    static {
        instance = new SingletonWayTwo();
    }

    public static SingletonWayTwo getInstance(){
        return instance;
    }

    public void printMessage(){
        System.out.println("饿汉式（静态代码块）");
    }
}
