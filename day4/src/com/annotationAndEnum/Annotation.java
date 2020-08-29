package com.annotationAndEnum;

import java.util.ArrayList;

/**
 * 注解的使用
 * jdk5.0新增Annotation
 * Annotation 其实就是代码里的特殊标记,这些标记可以在编译,类加载,运行时被读取,
 * 并执行相应的处理.通过使用Annotation,您可以在不改变原有逻辑的情况下,在源文件中嵌入一些补充信息
 * 在JavaSE中使用例如标记过时的功能,忽视警告等,在JavaEE/Android中Annotation也很重要
 * 例如配置应用程序的任何切面,代替JavaEE旧版中所遗留的繁多的代码和XML配置等
 *
 * 在编译时进行格式检查(jdk内置的三个注解)
 * @Override: 限定重写父类方法,只使用于方法
 * @Deprecated: 用于表示所修饰的元素(类,方法)已过时,通常修饰结构危险或存在更好的选择
 * @SuppressWarnings: 抑制编译器警告
 *
 * jdk8 中注解的新特性: 可重复注解(@Repeatable),类注解
 *
 */
public class Annotation {
    public static void main(String[] args) {
        Person p = new Student();
        p.walk();

        @SuppressWarnings("unused")
        int num  = 10;

        @SuppressWarnings({"rawtypes","unused"})
        ArrayList list = new ArrayList();
    }
}

class Person{
    private String name;
    private int age;

    public Person(){}

//    @MyAnnotation(value="Hello")
    @MyAnnotation
//    @MyAnnotation
    public Person(String name, int age){
        this.name =  name;
        this.age = age;
    }

    public void walk(){
        System.out.println("人走路");
    }

    public void eat(){
        System.out.println("人吃饭");
    }

}

interface Description{
    void show();
}

class Student extends Person implements Description{
    @Override
    public void walk() {
        System.out.println("学生走路");
    }

    @Override
    public void show() {
        System.out.println("haha");
    }

    @Deprecated
    public void poor(){
        System.out.println("Hello World");
    }
}
