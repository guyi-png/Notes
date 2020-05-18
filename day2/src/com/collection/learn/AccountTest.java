package com.collection.learn;

/**
 * 如果银行有一个账户，有两个储户分别向同一个账户存3000，每次存1000，每次存完都要打印账户余额
 */
public class AccountTest {
    public static void main(String[] args) {
        Account acct = new Account(0);
        Customer c1 = new Customer(acct);
        Customer c2 = new Customer(acct);
        c1.setName("储户一");
        c2.setName("储户二");
        c1.start();
        c2.start();
    }
}

class Account{
    private double balance;

    public Account(double balance){
        this.balance = balance;
    }

    public synchronized void setBalance(double deposit){
        if (deposit > 0){
            balance += deposit;
            //阻塞观察是否有线程安全问题
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "存钱成功，您的账户余额: " + balance);
        }
    }
}

class Customer extends Thread{
    private Account acct;

    public Customer(Account acct){
        this.acct = acct;
    }

    @Override
    public void run() {
        for (int i =0; i<3; i++){
            acct.setBalance(1000);
        }
    }
}
