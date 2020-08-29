package com.guyi.JUC;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * JUC(java.util.concurrent)
 * 
 * volatile关键字:  当多个线程进行操作共享数据时，
 * 可以保证内存中的数据可见,
 * 是一种较于synchronized更轻量的方式,
 * 但是没有"锁"功能,没有解决"原子性"问题
 *
 * 原子变量： jdk1.5后， Java.util.concurrent.atomic包下提供了常用的原子变量
 * 原子变量被声明为volatile，底层用Compare-And-Swap算法保证数据的原子性
 *
 */
public class Volatile {
    public static void main(String[] args) {
        MyThread mt = new MyThread();
        Thread thread = new Thread(mt);
        // 开启分线程
        thread.start();

        // while循环很快， 没有时间去堆里读取数据 ???
//        while (true){
//            if (mt.isFlag()){
//                System.out.println("**********");
//                break;
//            }
//        }

//        while (true){
//            synchronized(mt){  //加锁延缓速度，这样就有时间去读取数据
//                if (mt.isFlag()){
//                    System.out.println("**********");
//                    break;
//                }
//            }
//        }

        while (true){
            if (mt.isFlag()){
                System.out.println("&&&&&&&");
                break;
            }
        }

        MyThread1 mt1 = new MyThread1();
        for (int i =0; i < 10; i++){
            new Thread(mt1).start();
        }
    }
}

class MyThread implements Runnable{
    private volatile boolean flag = false;  //可见数据

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag is "+ flag);
    }
}

class MyThread1 implements Runnable{
//    private int serialNumber = 1;
    private AtomicInteger serialNumber = new AtomicInteger(1);

    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getSerialNumber());
    }

    public int getSerialNumber(){
        return serialNumber.getAndIncrement(); // 相当serialNumber++
    }
}
