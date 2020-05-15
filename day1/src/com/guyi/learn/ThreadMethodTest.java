package com.guyi.learn;

/**
 * Thread类中的常用的方法:
 * 1.start()  -->  启动当前的线程，调用当前线程的run()
 * 2.run()  --> 子类要重写父类的run()方法， run() 方法体的内容为该线程的需要执行的代码
 * 3.currentThread() --> 静态方法，返回执行当前代码的线程
 * 4.getName()  --> 返回执行当前代码的线程的名字
 * 5.setName() --> 设置执行当前代码的线程的名字
 * 6.yield() -->   释放当前cpu的执行权
 * 7.join()  -->    在线程A中调用线程B的join()，此时线程A就进入阻塞状态，直到线程B完全执行完以后，线程A才结束阻塞状态
 * 8.stop()  -->    让线程它停止
 * 9.sleep(long millis) -->    让它休息
 * 10.isAlive()   --> 死没死
 *
 * 线程的优先级：
 * Modifier and Type	Field	            Description
 * static int	        MAX_PRIORITY        The maximum priority that a thread can have.
 *
 * static int	        MIN_PRIORITY        The minimum priority that a thread can have.
 *
 * static int	        NORM_PRIORITY       The default priority that is assigned to a thread.
 * 如何获得和设置当前线程的优先级：
 * getPriority()
 * setPriority(int n)     优先级越大，被cpu执行的概率就越大
 */
public class ThreadMethodTest {
    public static void main(String[] args){
        YourThread yt = new YourThread();
        yt.setName("线程一");
        yt.start();
        YourThread yt1 = new YourThread("线程二");
        yt1.start();

        yt1.setPriority(Thread.MAX_PRIORITY);

        //给主线程取名
        Thread.currentThread().setName("主线程");
        for (int i =0; i< 100; i++){
            System.out.println(Thread.currentThread().getName() +  ":" + i);
            if (i == 20){
                try {
                    yt.join();    //让yt的线程执行完,再执行自己的
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class YourThread extends Thread{
    public YourThread(){

    }

    public YourThread(String name){
        super(name);
    }

    @Override
    public void run() {
        for (int i =0; i< 100; i++){
            System.out.println(Thread.currentThread().getName()
                    +  ":" + i + "--" +Thread.currentThread().getPriority());
            System.out.println();
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i % 2 ==0){
                yield();
            }
        }
    }
}
