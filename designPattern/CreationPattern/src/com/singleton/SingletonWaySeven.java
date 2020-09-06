package com.singleton;

/**
 * 静态内部类
 *      静态内部类和非静态内部类一样，都是在被调用时才会被加载
 *      类的静态属性只会在第一次加载类的时候初始化，所以在这里，jvm帮忙我们保证了线程的安全性
 *      线程安全，实现懒加载，效率高
 *      推荐使用
 */
public class SingletonWaySeven {
    private SingletonWaySeven (){}

    // 静态内部类
    private static class innerClass{
        private final static SingletonWaySeven instance = new SingletonWaySeven();
    }

    // 使用静态内部的属性
    public static SingletonWaySeven getInstance(){
        return innerClass.instance;
    }

    public void printMessage(){
        System.out.println("静态内部类");
    }
}
