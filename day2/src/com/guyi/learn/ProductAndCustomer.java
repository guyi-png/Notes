package com.guyi.learn;

/**
 *  生产者与消费者
 */
public class ProductAndCustomer {
    public static void main(String[] args) {
        Clerk c1 = new Clerk();
        Product pv = new Product(c1);
        Thread p1 = new Thread(pv);
        p1.setName("生产者");
        p1.start();

        Customer1 ctv = new Customer1(c1);
        Thread ct1 = new Thread(ctv);
        ct1.setName("消费者");
        ct1.start();
    }
}

class Clerk{
    private int productCount = 0;

    public synchronized void addProduct() {
        if (productCount < 20){
            productCount++;
            System.out.println("生产第" + productCount + "个产品");
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void purchaseProduct() {
        if (productCount > 0){
            System.out.println("购买第" + productCount + "个产品");
            productCount--;
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Product implements Runnable{
    public Product(Clerk clerk){
        this.clerk = clerk;
    }

    private Clerk clerk;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ "开始生产");
        while(true){
            clerk.addProduct();
        }
    }
}

class Customer1 implements Runnable{
    private Clerk clerk;

    public Customer1(Clerk clerk){
        this.clerk = clerk;
    };
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ "开始购买");
        while(true){
            clerk.purchaseProduct();
        }
    }
}