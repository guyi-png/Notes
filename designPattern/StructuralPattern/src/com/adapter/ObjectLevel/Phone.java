package com.adapter.ObjectLevel;

/**
 * dst类，目标类
 */
public class Phone {
    // 充电
    public void charging(Voltage5V voltage5V){
        int dst = voltage5V.output5V(); // 5V
        System.out.println("充电电压="+dst+"伏");
        if (dst == 5){
            System.out.println("开始充电");
        }else{
            System.out.println("炸了");
        }
    }
}
