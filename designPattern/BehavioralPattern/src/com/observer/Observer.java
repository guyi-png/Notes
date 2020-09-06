package com.observer;

/**
 * 依赖方的接口
 */
public interface Observer {
    void update(double temperature, double pressure, double humidity);
}
