package com.bookstore.mvc.model.service;

import com.bookstore.mvc.model.dao.TradeDAOImpl;
import com.bookstore.mvc.model.domain.Trade;

import java.sql.Date;
import java.util.Set;

public class TradeService {
    private final TradeDAOImpl tradeDAO = new TradeDAOImpl();

    // 向交易表中插入记录
    public long insert(int userId){
        Trade trade = new Trade();
        trade.setTrade_time(new Date(new java.util.Date().getTime()));
        trade.setUser_id(userId);

        return tradeDAO.insert(trade);
    }

    // 通过用户的id获取他的全部的交易记录
    public Set<Trade> getTradeByUserId(int id){
        return tradeDAO.getTradeByUserId(id);
    }
}
