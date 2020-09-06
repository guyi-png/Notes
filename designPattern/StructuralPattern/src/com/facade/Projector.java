package com.facade;

public class Projector {
    private Projector (){}

    private static volatile Projector instance;

    public static Projector getInstance(){
        if (instance == null){
            synchronized (Projector.class){
                if (instance == null){
                    instance = new Projector();
                }
            }
        }
        return instance;
    }

    public void on(){
        System.out.println("projector on");
    }

    public void off(){
        System.out.println("projector off");
    }

    public void project(){
        System.out.println("projector is mapping");
    }
}
