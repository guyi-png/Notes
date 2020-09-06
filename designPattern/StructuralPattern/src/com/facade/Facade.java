package com.facade;

/**
 * 外观类
 */
public class Facade {
    private final DVDPlayer dvdPlayer = DVDPlayer.getInstance();
    private final Popcorn popcorn = Popcorn.getInstance();
    private final Projector projector = Projector.getInstance();
    private final Screen screen = Screen.getInstance();

    public void read(){
        screen.down();
        projector.on();
        dvdPlayer.on();
        popcorn.on();
    }

    public void play(){
        projector.project();
        dvdPlayer.play();
        popcorn.pop();
    }

    public void stop(){
        dvdPlayer.pause();
        popcorn.stop();
    }

    public void end(){
        dvdPlayer.off();
        projector.off();
        screen.up();
        popcorn.stop();
    }
}
