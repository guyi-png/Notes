package com.adapter.ObjectLevel;

/**
 *对象适配器模式：
 *      Socket为被适配的类，src类
 *
 * 对象适配器模式注意事项和细节
 *      对象适配器和类适配器其实是同种思想，只不过是实现方式不同
 *      根据合成复用原则， 使用组合替代继承， 所以它解决了类适配器必须继承src类的局限性
 *      也不再要求dst(Voltage5V)必须是接口， 成本更低，更灵活
 *
 *  springMVC中HandlerAdapter 使用到了Adapter模式
 *   大致：
 *          获取到请求后， dispatcher调用doDispatch()方法
 *          根据请求获取对应的controller,
 *          再根据 controller 获取 对应 adapter,
 *          由对应的 adapter 处理 controller,
 *          adapter处理完后返回 ModelAndView
 *
 */
public class Socket {
    public int output220V(){
        int src = 220;
        System.out.println("插座电压="+src+"伏");
        return src;
    }
}
