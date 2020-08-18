package com.bookstore.mvc.model.domain;


import java.util.LinkedHashSet;
import java.util.Set;

public class User {
    private int user_id;
    private String user_name;
    private int account_id;
    // 用于保存交易信息
    public Set<Trade> trades = new LinkedHashSet<>();


    public User() {}


    public Set<Trade> getTrades() {
        return trades;
    }

    public void setTrades(Set<Trade> trades) {
        this.trades = trades;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", account_id=" + account_id +
                ", trades=" + trades +
                '}';
    }
}
