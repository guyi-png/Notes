package com.visitor;

/**
 * 访问者模式：
 *      访问者模式基本介绍访问者模式基本介绍
 *      1)访问者模式(Visitor Pattern),封装一些作用于某种数据结构的各元素的操作,
 *      它可以在不改变数据结构的前提下定义作用于这些元素的新的操作。
 *      2)主要将数据结构与数据操作分离,解决数据结构和操作耦合性问题
 *      3)访问者模式的基本工作原理是:在被访问的类里面加一个对外提供接待访问者的接口
 *      4)访问者模式主要应用场景是:需要对一个对象结构中的对象进行很多不同操作(这些操作彼此没有关联),
 *      同时需要避免让这些操作"污染"这些对象的类,可以选用访问者模式解决
 */
public class Client {
    public static void main(String[] args) {
        Person woman = new Woman("翠花");
        Person man = new Man("杠精");

        ObjectStructure structure = new ObjectStructure();
        // 添加人物
        structure.attach(man);
        structure.attach(woman);
        // 投票
        structure.displayResult(new Success(), new Failure());
    }
}
