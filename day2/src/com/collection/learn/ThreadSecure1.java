package com.collection.learn;

public class ThreadSecure1 {
    public static void main(String[] args) {
        Window1 wv = new Window1();
        Thread w1 = new Thread(wv);
        Thread w2 = new Thread(wv);
        Thread w3 = new Thread(wv);

        w1.start();
        w2.start();
        w3.start();
    }
}

class Window1 implements Runnable{
    private int ticket = 100;
    @Override
    public void run() {
        while(ticket > 0){
            purchase();
        }
    }

    private synchronized void purchase(){   //默认的同步的监视器，非静态是this, 静态是本类，即Window.class
        if (ticket > 0){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "买票，票号为： " + ticket);
            ticket--;
        }
    }
}