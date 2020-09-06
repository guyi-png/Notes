package com.adapter.ObjectLevel;

/**
 * 充电器(adapter),  实现输出5V的接口(Voltage5V)
 */
public class Charger implements Voltage5V {
    // 聚合关系
    private Socket socket;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public int output5V() {
        int src = 0;
        if (socket != null){
            src = socket.output220V();
        }
        int dst = src / 44;
        return dst;
    }
}

