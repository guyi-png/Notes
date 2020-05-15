package com.guyi.learn;

/**
 * 多线程的创建：
 *      方式之一： 继承Thread类
 *              1.创建一个继承Thread类的子类
 *              2.重写Thread类中的run()
 *              3.创建Thread类的子类的对象
 *              4.通过此对象的start()方法
 *              start()方法的作用： 启动当前的线程，调用当前线程中的run()方法
 */
public class ThreadTest {
    public static void main(String[] args) {
        // 3.创建Thread类的子类的对象
        MyThread mt = new MyThread();
        //4.通过此对象的start()方法
        mt.start();
//        mt.run();    这时调用run()是用的主线程
//        mt.start();  在上面的start()执行后，不能再调用start()方法
        MyThread mt1 = new MyThread();
        mt1.start();  //  又开启一个线程

        //以下代码会和run()同时执行
        for (int i =0; i< 100;i++){
            System.out.println("Hello World");
        }

        // 匿名子类重写run()方法和创建匿名对象，在匿名对象上调用start()方法
        new Thread(){
            @Override
            public void run() {
                for (int i =0; i<100; i++){
                    if (i % 2 == 0){
                        System.out.println(i);
                    }
                }
            }
        }.start();
    }
}

 //1.创建一个继承Thread类的子类
class MyThread extends Thread{
    // 2.重写Thread类中的run()
    @Override
    public void run() {
        for (int i =0; i<100; i++){
            if (i % 2 == 0){
                System.out.println(i);
            }
        }
    }
}
