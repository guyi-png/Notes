package com.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 核心类：被观察者实现
 *      存储天气的数据
 *      管理观察者们
 *      通知所有的观察者
 */
public class WeatherData implements Subject{
    private List<Observer> observers;
    private double temperature;
    private double pressure;
    private double humidity;

    public void setData(double temperature, double pressure, double humidity){
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        // 让观察者知道
        notifyObserver();
    }

    @Override
    public void registerObserver(Observer observer) {
        if (observers ==null){
            observers = new ArrayList<>();
        }
        if (!observers.contains(observer)){
            observers.add(observer);
        }
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (int i=0; i < observers.size(); i++){
            observers.get(i).update(temperature, pressure, humidity);
            System.out.println("---------------------------------");
        }
    }
}
