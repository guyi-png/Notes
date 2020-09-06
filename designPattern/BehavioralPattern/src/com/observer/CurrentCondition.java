package com.observer;

/**
 * 观察者实现类
 */
public class CurrentCondition implements Observer {
    private double temperature;
    private double pressure;
    private double humidity;

    @Override
    public void update(double temperature, double pressure, double humidity) {
        this.humidity = humidity;
        this.temperature = temperature;
        this.pressure = pressure;
        System.out.println("现在情况更新了数据");
        display();
    }

    private void display() {
        System.out.println("今天的气温"+temperature);
        System.out.println("今天的气压"+pressure);
        System.out.println("今天的湿度"+humidity);
    }
}
