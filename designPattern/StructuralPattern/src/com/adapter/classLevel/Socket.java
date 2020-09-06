package com.adapter.classLevel;

/**
 * 适配器模式介绍：
 *      适配器模式（adapter pattern）将某个类的接口转换为客户端期望的另一个接口表示
 *      目地是兼容性，让原本因接口不匹配而不能一起工作的两个类可以协同工作，
 *      其别名为包装类(Wrapper)
 *      适配器模式属于结构型模式
 *      主要分为三类， 类适配器模式， 对象适配器模式， 接口适配器模式
 *
 * 类适配器：
 *      adapter类通过继承src类（源类），实现dst类（目标类）接口，完成src->dst(src与dst之间不兼容)
 *      生活中  插座220v（源） 通过充电器（adapter） 转为5v直流电（目标）为手机充电
 *      类适配器模式注意的事项与细节：
 *          java采用单继承机制， 所以类适配器需要继承src类 这是一个缺点。
 *          由于其继承了src类，所以它可以根据需要重写src类的方法，使得adapter灵活性增强
 *
 *
 *      Socket为被适配的类，src类
 */
public class Socket {
    public int output220V(){
        int src = 220;
        System.out.println("插座电压="+src+"伏");
        return src;
    }
}
