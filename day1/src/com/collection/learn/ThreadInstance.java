package com.collection.learn;

/**
 * 三个窗口抢票,有线程安全问题
 */
public class ThreadInstance {
    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.setName("窗口一");
        w2.setName("窗口二");
        w3.setName("窗口三");

        w1.start();
        w2.start();
        w3.start();
//        ----------------------------------------------
        Window1 wv = new Window1();
        Thread w4 = new Thread(wv);
        Thread w5 = new Thread(wv);
        Thread w6 = new Thread(wv);

        w4.setName("窗口一");
        w5.setName("窗口二");
        w6.setName("窗口三");

        w4.start();
        w5.start();
        w6.start();
    }
}


class Window extends Thread{
    private static int ticket = 100;

    @Override
    public void run() {
        while(true){
            if (ticket > 0){
                System.out.println(getName() + "买票，票号为： " + ticket);
                ticket--;
            }else{
                break;
            }
        }
    }
}

class Window1 implements Runnable{
    private int ticket = 100;
    @Override
    public void run() {
        while(true){
            if (ticket > 0){
                System.out.println(Thread.currentThread().getName() + "买票，票号为： " + ticket);
                ticket--;
            }else {
                break;
            }
        }
    }
}