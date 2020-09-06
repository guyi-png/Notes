package com.facade;

/**
 * 外观模式： 也叫过程模式，外观模式为子系统中的一组接口提供一个一致的界面
 * 此模式定义了一个高层接口，这个接口使得这一子系统更加容易使用，
 * 外观模式通过定义一个一致的接口， 用以屏蔽内部子系统的细节，
 * 使得调用端只需要这个接口发生调用， 而无需关心这个子系统的内部细节
 *
 * mybatis中使用了外观模式，
 * mybatis中Configuration是一个外观类,
 * 聚合了defaultObjectFactory, defaultObjectWrapperFactory,defaultReflectFactory
 *
 * 通过合理的使用外观模式，可以帮我们更好的划分访问的层次
 * 当系统需要进行分层设计时，可以考虑使用Facade模式
 * 不能过多的或者不合理的使用外观模式，使用外观模式要以让系统有层次，利于维护为目的
 */
public class Client {
    public static void main(String[] args) {
        Facade facade  = new Facade();
        facade.read();
        System.out.println("------------------");
        facade.play();
        System.out.println("------------------");
        facade.stop();
        System.out.println("------------------");
        facade.end();
    }
}
