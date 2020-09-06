package com.singleton;

public class Test {
    public static void main(String[] args) {
        one();
        two();
        three();
        four();
        five();
        six();
        seven();
        eight();
    }

    private static void one(){
        SingletonWayOne wayOne = SingletonWayOne.getInstance();
        wayOne.printMessage();
        SingletonWayOne wayOne1 = SingletonWayOne.getInstance();
        System.out.println(wayOne == wayOne1); //true
    }

    private static void two(){
        SingletonWayTwo wayTwo = SingletonWayTwo.getInstance();
        wayTwo.printMessage();
    }

    private static void three(){
        SingletonWayThree wayThree = SingletonWayThree.getInstance();
        wayThree.printMessage();
    }

    private static void four(){
        SingletonWayFour wayFour = SingletonWayFour.getInstance();
        wayFour.printMessage();
    }

    private static void five(){
        SingletonWayFive wayFive = SingletonWayFive.getInstance();
        wayFive.printMessage();
    }

    private static void six(){
        SingletonWaySix waySix = SingletonWaySix.getInstance();
        waySix.printMessage();
    }

    private static void seven(){
        SingletonWaySeven waySeven = SingletonWaySeven.getInstance();
        waySeven.printMessage();
    }

    private static void eight(){
        SingletonWayEight wayEight = SingletonWayEight.INSTANCE;
        wayEight.printMessage();
        SingletonWayEight wayEight1 = SingletonWayEight.INSTANCE;

        System.out.println(wayEight==wayEight1);  //true
    }
}
