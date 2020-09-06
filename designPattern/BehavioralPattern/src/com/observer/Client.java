package com.observer;

/**
 * 观察者模式:
 *      对象之间多对一依赖的一种设计方案。被依赖的对象为subject
 *      依赖的对象为observer， subject通知observer变化
 *
 *  Jdk中 Observable类使用了观察者模式
 *  Observable类为被观察者
 *  Observer为观察者的接口
 *
 *  Observable类和Observer接口: @Deprecated(since="9")
 */
public class Client {
    public static void main(String[] args) {
        // 被观察者 ， 一个
        WeatherData weatherData = new WeatherData();
        // 观察者， 多个
        Observer observer = new CurrentCondition();
        Observer observer1 = new BaiduSite();

        // 注册观察者
        weatherData.registerObserver(observer);
        weatherData.registerObserver(observer1);

        // 设置属性
        weatherData.setData(33, 100, 55.2);
    }
}
