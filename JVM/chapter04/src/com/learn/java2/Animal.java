package com.learn.java2;

public class Animal {
    public void eat(){
        System.out.println("吃");
    }
}

interface Huntable{
    void hunt();
}

class Dog extends Animal implements Huntable{
    public Dog(){
        this.hunt();    //早期绑定
    }

    public Dog(int x){
        super.eat();    //早期绑定
    }


    @Override
    public void eat() {
        System.out.println("狗狗吃骨头");    //晚期绑定
    }

    @Override
    public void hunt() {
        System.out.println("捕捉老鼠");     //晚期绑定
    }
}
