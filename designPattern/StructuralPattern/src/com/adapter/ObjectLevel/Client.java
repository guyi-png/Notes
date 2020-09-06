package com.adapter.ObjectLevel;

public class Client {
    public static void main(String[] args) {
        System.out.println("对象适配器模式");
        // 拿手机
        Phone phone = new Phone();
        // 找充电器
        Charger charger = new Charger();
        // new一个插座给充电器
        charger.setSocket(new Socket());
        // 充电
        phone.charging(charger);
    }
}
