package com.prototype;

import java.util.Objects;

/**
 * java中Object类是所有类的根类， Object类中提供了clone()方法，
 * 该方法可以将一个Java对象复制一份，但是需要实现cloneable接口
 * 该接口表该类能够复制且具有复制的能力
 * 原型模式：
 *      原型模式是指：用原型实例指定创建对象的种类并且通过拷贝这些原型，创建新的对象
 *      原型模式是一种创建型模式，允许一个对象再创建另一个可定制的对象，
 *      无需知道如何创建的细节
 *      工作原理是通过将一个原型对象传给那个要发动创建的对象，
 *      这个要发动创建的对象通过请求原型对象拷贝它们自己来实施创建， 即object.clone()
 *      形象的理解： 比如   孙悟空拔出猴毛， 变出其他的孙悟空
 *
 * 深拷贝：
 *      复制对象的所有基本数据类型的成员变量值
 *      为所有的引用类型的成员变量申请空间， 并复制每一个引用数据类型成员变量所引用的对象，
 *      直到该对象可达的所有的对象，就是说， 对象进行深拷贝要对整个对象进行拷贝
 *      实现方式：
 *          1. 重写clone方法实现深拷贝
 *          2. 对象序列化实现深拷贝 (推荐使用)
 *              to DeepCloneTarget.java
 *
 * spring 在xml文件中配置bean时可以指定scope 当声明为 prototype， 从ioc容器中
 * getBean时，spring源码在doGetBean方法中会调用原型对应的代码
 */
public class Sheep implements Cloneable{
    private String name;
    private int age;
    private String color;
    private Sheep friend;

    public void setFriend(Sheep friend) {
        this.friend = friend;
    }

    public Sheep getFriend() {
        return friend;
    }

    public Sheep(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sheep)) return false;
        Sheep sheep = (Sheep) o;
        return age == sheep.age &&
                Objects.equals(name, sheep.name) &&
                Objects.equals(color, sheep.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, color);
    }

    // 使用默认的clone方法（浅拷贝）
    @Override
    protected Sheep clone() {
        Sheep sheep = null;
        try {
            sheep = (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return sheep;
    }
}
