package com.builder;

/**
 * 建造者模式：
 *      建造者模式又叫 生成器模式， 是一种对象构建模式，它可以将复杂的对象的建造过程抽象出来
 *      使这个抽象过程的不同实现方法可以建造不同表现得对象
 *      建造者模式 使一步一步创建一个复制的对象的， 他们允许用户只通过指定复杂对象的类型和内容就可以构建他们
 *      不必知道具体的构建细节
 * 建造者模式的四个角色：
 *      1. product（产品角色）： 一个具体的产品对象
 *      2. builder（抽象的建造者）： 创建一个product对象的各个部件指定的接口
 *      3. concreteBuilder（具体实现建造者）： 实现接口， 构建和装配各个部件
 *      4. director（指挥者）：   构建一个实现builder接口的对象，它主要是用于创建一个复杂的对象，
 *      作用：  一是， 隔离了客户端与对象的生产过程， 二是， 负责控制产品对象的生产过程
 *
 *      jdk中java.lang.StringBuilder 使用了建造者模式
 *      Appendable 接口定义了多个append方法（抽象方法）， 即Appendable 为builder（抽象的建造者），定义了抽象方法
 *      AbstractStringBuilder抽象类实现了Appendable接口方法，它就是concreteBuilder（具体实现建造者），但不能实例化
 *      StringBuilder既充当了指挥者角色，同时充当了具体的建造者
 *
 * 建造者模式的注意事项和细节
 *      客户端不必知道产品内部组成的细节， 将产品本身与产品的创建过程解耦，使得相同的创建过程可以创建不同的产品对象
 *      每个具体建造者都相对独立， 而与其他的具体建造者无关，因此可以很方便地替换建造者或增加新的具体建造者，用户使用不同的
 *      具体建造者既可得到不同的产品对象
 *      可以更加精细地控制产品的创建过程。增加新的具体建造者无须修改原有的代码
 *      建造者模式所创建的产品一般具有较多的共同点，其组成的部分相似，如果产品之间的差异型很大，则不适合使用
 *
 * 房子类 ： 产品
 */
public class House {
    private String base;  // 地基
    private String walls; //墙
    private String rooftop; //房顶

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getWalls() {
        return walls;
    }

    public void setWalls(String walls) {
        this.walls = walls;
    }

    public String getRooftop() {
        return rooftop;
    }

    public void setRooftop(String rooftop) {
        this.rooftop = rooftop;
    }

    @Override
    public String toString() {
        return "House{" +
                "base='" + base + '\'' +
                ", walls='" + walls + '\'' +
                ", rooftop='" + rooftop + '\'' +
                '}';
    }
}
