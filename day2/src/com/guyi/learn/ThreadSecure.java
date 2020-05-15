package com.guyi.learn;

/**
 *  在Java中，我们通过同步机制。来解决线程同步的问题：
 *      方式一： 同步代码块：
 *              synchronized(同步监视器){
 *                  // 需要被同步的代码,要准确的包裹
 *              }
 *              注：需要被同步的代码,比如共享的数据，共享的数据即多个线程共同操作的变量。
 *              同步监视器即锁，任何一个类的对象都能当锁,多线程的锁唯一
 *      方式二： 同步方法：
 *              如果操作共享数据的代码完整的声明在一个方法中，我们可以将此方法声明为同步的
 */
public class ThreadSecure {
    public static void main(String[] args) {
        Window wv = new Window();
        Thread w1 = new Thread(wv);
        Thread w2 = new Thread(wv);
        Thread w3 = new Thread(wv);

        w1.start();
        w2.start();
        w3.start();
    }
}

class Window implements Runnable{
    private int ticket = 100;
//    Object obj = new Object();
    @Override
    public void run() {
        while(true){
//            synchronized(obj){
            synchronized(this){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ticket > 0){
                    System.out.println(Thread.currentThread().getName() + "买票，票号为： " + ticket);
                    ticket--;
                }else {
                    break;
                }
            }
        }
    }
}

