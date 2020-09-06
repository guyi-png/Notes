package com.bridge;

/**
 * 桥接模式
 *      结构型设计模式，
 *      桥接模式是指将 实现 与 抽象 放在两个不同的类层次，是两个层次可以独立改变
 *      bridge pattern 基于类的最小设计原则，通过使用封装，聚合及继承等行为让不同
 *      的类承担不同的职责，主要特点是把抽象与行为实现分离开来，从而可以保证各部分
 *      的独立性以及应对他们的功能扩展 .
 *
 * 桥接模式的注意事项与细节：
 *      实现了抽象和实现部分的分离， 从而极大的提高了系统的灵活性，让抽象的部分与实现部分
 *      独立起来，有助于系统的进行分层设计，从而产生更好的结构化系统
 *      对于系统的高层部分，只需要知道 抽象部分 和 实现部分接口 就可以了，
 *      桥接模式减少了子类的个数，降低了系统的管理和维护成本
 *      桥接模式要求正确的识别两个独立变化的维度，因此其使用范围有一定的局限性
 *
 *   jdbc中使用过 桥接模式
 *          DriverManger 做 桥， 依赖了java.sql.Connection接口，
 *          实际使用其具体实现 mysql或oracle等的connection类
 *
 *   适用场景：
 *          对于哪些不希望使用继承或因为多层次继承导致系统类的个数急剧增加的系统
 *          常见场景：
 *          jdbc驱动程序
 *          银行转账系统
 *          消息管理系统
 */
public class Client {
    public static void main(String[] args) {
        Phone phone = new FoldedPhone(new HuaWei());

        phone.open();
        phone.close();
        phone.call();

        System.out.println("------------------");
        Phone phone1 = new FlipPhone(new XiaoMi());

        phone1.open();
        phone1.close();
        phone1.call();
    }
}
