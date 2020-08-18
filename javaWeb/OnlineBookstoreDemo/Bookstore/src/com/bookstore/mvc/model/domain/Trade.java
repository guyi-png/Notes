package com.bookstore.mvc.model.domain;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class Trade {
    private int trade_id;
    private Date trade_time;
    private int user_id;
    // 每次交易可能有多个交易项
    private Set<TradeItem> items = new LinkedHashSet<>();

    public Trade() {}

    public Set<TradeItem> getItems() {
        return items;
    }

    public void setItems(Set<TradeItem> items) {
        this.items = items;
    }

    public int getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(int trade_id) {
        this.trade_id = trade_id;
    }

    public Date getTrade_time() {
        return trade_time;
    }

    public void setTrade_time(Date trade_time) {
        this.trade_time = trade_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "trade_id=" + trade_id +
                ", trade_time=" + trade_time +
                ", user_id=" + user_id +
                '}';
    }
}
