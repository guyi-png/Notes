package com.bookstore.mvc.model.dao;

import com.bookstore.mvc.model.domain.Trade;

import java.util.Set;

public interface TradeDAO {
    /**
     * 向数据表中插入Trade对象
     * @param trade
     */
    long insert(Trade trade);

    /**
     * 根据用户的id获取Trade信息的集合
     * @param id
     * @return
     */
    Set<Trade> getTradeByUserId(int id);
}
