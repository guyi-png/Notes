package com.collection.learn;

/**
 *java中的sleep()和wait()的区别:
 * 对于sleep()方法，我们首先要知道该方法是属于Thread类中的。
 * 而wait()方法，则是属于Object类中的。
 *
 * sleep()方法导致了程序暂停执行指定的时间，让出cpu该其他线程，
 * 但是他的监控状态依然保持者，当指定的时间到了又会自动恢复运行状态。
 *
 * 在调用sleep()方法的过程中，线程不会释放对象锁。
 *
 * 而当调用wait()方法的时候，线程会放弃对象锁，进入等待此对象的等待锁定池，
 * 只有针对此对象调用notify()方法后本线程才进入对象锁定池准备
 *
 * 获取对象锁进入运行状态。
 *
 *
 * wait(): 一旦执行此方法，当前线程就进入阻塞状态，并释放同步监视器
 * notify(): 一旦执行此方法，就会唤醒wait的线程，如果有多个线程被wait，则唤醒优先级高的线程
 * notifyAll(): 一旦执行此方法，就会唤醒所有被wait的线程
 * 上面的方法只能声明在同步代码块或同步方法中,其同步监视器要一样
 */
public class ThreadCommunication {
    public static void main(String[] args) {
        Number nv = new Number();
        Thread n = new Thread(nv);
        Thread n1 = new Thread(nv);
        n.setName("线程一");
        n1.setName("线程二");
        n.start();
        n1.start();
    }
}

class Number implements Runnable{
    private int number = 1;
//    Object obj = new Object();

    @Override
    public void run() {
        while(true){
//            synchronized (obj){       这的obj 与调用wait和notify方法 的对象不同
            synchronized(this){
                notify();  //让等待中线程准备就绪
                if (number <= 100){
                    System.out.println(Thread.currentThread().getName()+":"+number);
                    number++;

                    try {
                        wait();  //等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
        }
    }
}
