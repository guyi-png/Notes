package com.guyi.learn;

import java.util.concurrent.locks.ReentrantLock;

/**
 * jdk5.0新增--> Lock接口
 */
public class LockTest {
    public static void main(String[] args) {
        Window2 wv = new Window2();
        Thread w1 = new Thread(wv);
        Thread w2 = new Thread(wv);
        Thread w3 = new Thread(wv);
        w1.setName("窗口一");
        w2.setName("窗口二");
        w3.setName("窗口三");

        w1.start();
        w2.start();
        w3.start();
    }
}

class Window2 implements Runnable{
    private int ticket =100;
    // 1.实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while(true){
            try{
                // 2.调用lock()
                lock.lock();
                if (ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":"+ticket);
                    ticket--;
                }else{
                    break;
                }
            }finally{
                //手动的解锁。如果出异常了，也会解锁
                lock.unlock();
            }
        }
    }
}
