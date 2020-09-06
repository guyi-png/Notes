package com.singleton;

/**
 * 枚举
 *      不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象
 *      推荐使用
 */
public enum SingletonWayEight {
    INSTANCE;

    public void printMessage(){
        System.out.println("枚举");
    }
}
