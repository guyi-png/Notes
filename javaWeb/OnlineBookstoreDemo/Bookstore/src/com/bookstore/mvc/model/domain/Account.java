package com.bookstore.mvc.model.domain;

/**
 * 与数据库中的account表的字段对应
 */
public class Account {
    private int account_id;
    private double balance;

    public Account() {}

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", balance=" + balance +
                '}';
    }
}
