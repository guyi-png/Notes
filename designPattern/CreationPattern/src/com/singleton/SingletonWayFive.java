package com.singleton;

/**
 * 懒汉式（线程不安全， 同步代码块）
 *
 */
public class SingletonWayFive {
    private SingletonWayFive (){}

    private static SingletonWayFive instance;

    public static SingletonWayFive getInstance(){
        if (instance == null){
            synchronized (SingletonWayFive.class){
                instance = new SingletonWayFive();
            }
        }
        return instance;
    }

    public void printMessage(){
        System.out.println("懒汉式（线程不安全， 同步代码块）");
    }
}
