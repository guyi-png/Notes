package com.singleton;

/**
 * 懒汉式（线程不安全）:
 *      可以起到Lazy Loading的效果， 但是只能在单线程下使用
 *      如果使用多线程，一个线程进入if判断语句后，还没有new对象，第二个线程也进入到if中
 *      这样将会出现多个instance对象
 */
public class SingletonWayThree {
    private SingletonWayThree (){}

    private static SingletonWayThree instance;

    // 在通过方法获取对象时初始化实例, 线程不安全
    public static SingletonWayThree getInstance(){
        if (instance == null){
            instance = new SingletonWayThree();
        }
        return instance;
    }

    public void printMessage(){
        System.out.println("懒汉式（线程不安全）");
    }
}
