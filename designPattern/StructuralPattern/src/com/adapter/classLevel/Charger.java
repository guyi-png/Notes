package com.adapter.classLevel;

/**
 * 充电器(adapter),  实现输出5V的接口(Voltage5V),  继承Socket
 */
public class Charger extends Socket implements Voltage5V {
    @Override
    public int output5V() {
        int src = output220V();
        int dst = src / 44;
        return dst;
    }
}

