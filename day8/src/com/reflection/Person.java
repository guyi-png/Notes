package com.reflection;

/**
 * 一个普通的类
 */
public class Person{
    private String name;
    public int age;

    public Person(){}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Person(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    private String say(String word) throws Exception{
        System.out.println("欧勒格, "+word);
        return "没毛病";
    }

    public void show(){
        System.out.println("奥里给");
    }

    private static int good(){
        System.out.println("斯巴拉西");
        return 88;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
