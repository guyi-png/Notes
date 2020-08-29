package com.guyi.learn.ThreadTest;

public class Singleton {
    public static void main(String[] args) {
        Bank bank = Bank.getInstance();
        bank.say();
    }
}

//Singleton
class Bank{
    private Bank(){}

    private static Bank instance= null;

//    public static synchronized Bank getInstance(){
//        if (instance == null){
//            instance = new Bank();
//        }
//        return instance;
//    }

    //效率高点的方式
        public static Bank getInstance(){
            if (instance == null){
                synchronized(Bank.class){
                    if (instance == null){
                        instance = new Bank();
                    }
                }
            }
            return instance;
        }

    public void say(){
        System.out.println("hello");
    }
}
