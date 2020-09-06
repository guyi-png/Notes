package com.facade;

/**
 * dvd播放器
 */
public class DVDPlayer {
    private DVDPlayer (){}

    private static volatile DVDPlayer instance;

    public static DVDPlayer getInstance(){
        if (instance == null){
            synchronized (DVDPlayer.class){
                if (instance == null){
                    instance = new DVDPlayer();
                }
            }
        }
        return instance;
    }

    public void on(){
        System.out.println("dvd on");
    }

    public void off(){
        System.out.println("dvd off");
    }

    public void play(){
        System.out.println("dvd is playing");
    }

    public void pause(){
        System.out.println("dvd pause ...");
    }
}
