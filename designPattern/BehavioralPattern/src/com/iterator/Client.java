package com.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 迭代器模式
 * 基本介绍基本介绍
 *      1)迭代器模式(Iterator Pattern)是常用的设计模式,属于行为型模式
 *      2)如果我们的集合元素是用不同的方式实现的,有数组,还有java的集合类,
 *      或者还有其他方式,当客户端要遍历这些集合元素的时候就要使用多种遍历方式,
 *      而且还会暴露元素的内部结构,可以考虑使用迭代器模式解决。
 *      3)选代器模式,提供一种遍历集合元素的统一接口,用一致的方法遍历集合元素,
 *      不需要知道集合对象的底层表示,即:不暴露其内部的结构。
 *
 *      参照： java.util.iterator相关
 *
 *
 *      迭代器模式在JDK-ArrayList集合应用的源码分析
 *              内部类Itr充当具体实现迭代器Iterator的类, 作为ArrayList内部类List就是充当了聚合接口,
 *              含有一个iterator()方法,返回一个选代器对象ArrayList是实现聚合接口List的子类,
 *              实现了iterator()Iterator接口系统提供送代器模式解决了不同集合(ArrayList ,LinkedList)
 *              统一遍历问题
 *      缺点：
 *          每个聚合对象都要一个迭代器， 会生成多个迭代器不好管理的类
 */
public class Client {
    public static void main(String[] args) {
        ComputerCollege computerCollege = new ComputerCollege();
        computerCollege.addDepartment("计算机的某系", "准备就绪");
        computerCollege.addDepartment("计算机的另系", "ok");

        InfoCollege infoCollege = new InfoCollege();
        infoCollege.addDepartment("信息的某系","行行行");
        infoCollege.addDepartment("信息的另系","okey" );

        List<College> colleges = new ArrayList<>();
        colleges.add(computerCollege);
        colleges.add(infoCollege);

        Output output = new Output(colleges);

        output.printCollege();
    }
}
