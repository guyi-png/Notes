package com.mybatis.entity;

import java.io.Serializable;

public class Account implements Serializable {
    private Integer id;
    private Integer uid;
    private Double balance;

    //从表实体类应该包含主表实体对象的引用
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account() {}

    public Account(Integer id, Integer uid, Double balance) {
        this.id = id;
        this.uid = uid;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", balance=" + balance +
                ", user=" + user +
                '}';
    }
}