package com.facade;

public class Screen {
    private Screen (){}

    private static volatile Screen instance;

    public static Screen getInstance(){
        if (instance == null){
            synchronized (Screen.class){
                if (instance == null){
                    instance = new Screen();
                }
            }
        }
        return instance;
    }

    public void up(){
        System.out.println("screen up");
    }

    public void down(){
        System.out.println("screen down");
    }
}
