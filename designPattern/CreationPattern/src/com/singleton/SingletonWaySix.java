package com.singleton;

/**
 * 双重检查
 *      推荐使用
 */
public class SingletonWaySix {
    private SingletonWaySix(){}

    // volatile的作用是作为指令关键字，
    // 确保本条指令不会因编译器的优化而省略，且要求每次直接读值。
    private static volatile SingletonWaySix instance;

    // 两次检查instance是否为空， 同步代码块保证线程安全, 实现懒加载，效率也可以
    public static SingletonWaySix getInstance(){
        if (instance == null){
            synchronized (SingletonWaySix.class){
                if (instance == null){
                    instance = new SingletonWaySix();
                }
            }
        }
        return instance;
    }

    public void printMessage(){
        System.out.println("双重检查");
    }
}
