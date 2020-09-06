package com.templateMethod;

/**
 * 模板方法模式
 *      基本介绍
 *      1)模板方法模式(Template Method Pattern) ,又叫模板模式(Template Pattern),
 *      在一个抽象类公开定义了执行它的方法的模板,
 *      它的子类可以按需要重写方法实现,但调用将以抽象类中定义的方式进行.
 *      2)简单说,模板方法模式定义一个操作中的算法的骨架,
 *      而将一些步骤延迟到子类中,
 *      使得子类可以不改变一个算法的结构,就可以重定义该算法的某些特定步骤
 *      3)这种类型的设计模式属于行为型模式。
 *
 *      模板方法模式的钩子方法：
 *          在模板方法模式的父类中，我们可以定义一个方法，它默认表做如何事情，子类可以视情况要不要覆盖它，
 *          该方法称为 钩子方法
 *
 *  spring框架用到了模板方法模式
 *  在初始化ioc容器中
 *  AbstractApplicationContext的 refresh() 就是模板方法
 *  postProcessBeanFactory方法, onRefresh方法 就是钩子方法
 */
public class Client {
    public static void main(String[] args) {
//        SoyaMilk soyaMilk = new BlackBeanSoyaMilk();
        SoyaMilk soyaMilk1 = new PureSoyaMilk();

//        soyaMilk.make();

        soyaMilk1.make();
    }
}
