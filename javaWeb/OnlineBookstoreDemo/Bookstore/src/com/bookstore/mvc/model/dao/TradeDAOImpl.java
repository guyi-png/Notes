package com.bookstore.mvc.model.dao;

import com.bookstore.mvc.model.domain.Trade;


import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TradeDAOImpl extends BaseDAO<Trade> implements TradeDAO{

    @Override
    public long insert(Trade trade) {
        String sql = "INSERT INTO trades(trade_time,user_id) VALUES(?, ?)";
        return insert(sql, trade.getTrade_time(), trade.getUser_id());
    }

    @Override
    public Set<Trade> getTradeByUserId(int id) {
        String sql = "SELECT * FROM trades WHERE user_id = ? ORDER BY trade_time DESC";
        List<Trade> trades = queryForList(sql, id);
        return new LinkedHashSet<>(trades);
    }
}
