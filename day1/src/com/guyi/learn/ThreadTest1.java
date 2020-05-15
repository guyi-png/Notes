package com.guyi.learn;

/**
 *  多线程的创建：
 *       方式之二：实现Runnable接口
 *              1.创建一个实现了Runnable接口的类
 *              2.实现类实现Runnable接口中的run()抽象方法
 *              3.创建实现类的对象
 *              4.将此对象作为参数传到Thread类的构造器中，创建Thread类的对象
 *              5.通过Thread类的对象调用start()
 *
 *              开发中，优先选择方式二： 1.实现的方式没有类的单继承的局限性
 *              2.实现的方式更适合来处理多个线程共享数据的情况
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        HeThread htv = new HeThread();
        Thread ht = new Thread(htv);
        ht.setName("线程一");
        ht.start();

        Thread ht1 = new Thread(htv);
        ht1.setName("线程二");
        ht1.start();
    }
}

class HeThread implements Runnable{
    @Override
    public void run() {
        for (int i =0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
