package com.observer;

/**
 * 被依赖方的接口
 */
public interface Subject {
    /**
     * 注册观察者
     */
    void registerObserver(Observer observer);

    /**
     * 删除指定的观察者
     */
    void remove(Observer observer);

    /**
     * 通知所有的观察者
     */
    void notifyObserver();
}
