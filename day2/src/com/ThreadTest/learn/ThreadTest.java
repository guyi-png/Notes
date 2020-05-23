package com.collection.learn;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * jdk5.0 新增的线程创建方式:
 *      实现Callable接口:
 *           好处： call()方法有返回值，call()可以抛出异常，Callable支持泛型
 *
 */
public class ThreadTest {
    public static void main(String[] args) {
        //3.创建Call()接口的实现类的对象
        MyThread mt = new MyThread();
        //4.将此对象作为参数传到FutureTask中（FutureTask类实现了Runnable接口）创建FutureTask类的对象
        FutureTask<Integer> futureTask = new FutureTask(mt);
        //5.将futureTask对象作为参数传到Thread，创建线程类对象，然后开启线程(调用start()方法)
        new Thread(futureTask).start();
        try {
            int sum = futureTask.get();  //可以获取线程调用call()后的返回值
            System.out.println("总和: "+ sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

//1.创建一个实现Callable接口的类
class MyThread implements Callable<Integer> {
    //2.实现call()方法,线程需要执行的代码放在call()方法中
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i =1; i <= 100; i++){
            System.out.println(i);
            sum += i;
        }

        return sum;   //有返回值
    }
}