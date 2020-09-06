package com.singleton;

/**
 * 懒汉式（线程安全， 同步方法）:
 *      效率稍低
 */
public class SingletonWayFour {
    private SingletonWayFour (){}

    private static SingletonWayFour instance;

    // 在获取方法时添加 synchronized 关键字， 声明此方法为同步方法
    public static synchronized SingletonWayFour getInstance(){
        if (instance == null){
            instance = new SingletonWayFour();
        }
        return instance;
    }


    public void printMessage(){
        System.out.println("懒汉式（线程安全， 同步方法）");
    }
}
