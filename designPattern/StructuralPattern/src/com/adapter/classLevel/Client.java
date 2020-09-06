package com.adapter.classLevel;

public class Client {
    public static void main(String[] args) {
        System.out.println("类适配器模式");
        // 拿手机
        Phone phone = new Phone();
        // 找充电器
        Charger charger = new Charger();
        // 充电
        phone.charging(charger);
    }
}
