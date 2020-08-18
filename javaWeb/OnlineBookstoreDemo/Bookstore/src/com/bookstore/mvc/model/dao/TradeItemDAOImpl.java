package com.bookstore.mvc.model.dao;

import com.bookstore.mvc.model.domain.TradeItem;

import java.util.*;

public class TradeItemDAOImpl extends BaseDAO<TradeItem> implements TradeItemDAO{

    @Override
    public void batchSave(Collection<TradeItem> items) {
        String sql = "INSERT INTO tradeitem(book_id, quantity, trade_id) VALUES(?,?,?)";
        Object[][] params = new Object[items.size()][3];
        List<TradeItem> list = new ArrayList<>(items);

        for (int i =0; i < list.size(); i++){
            params[i][0] = list.get(i).getBook_id();
            params[i][1] = list.get(i).getQuantity();
            params[i][2] = list.get(i).getTrade_id();
        }
        batch(sql, params);
    }

    @Override
    public Set<TradeItem> getTradeItemsByTradeId(int id) {
        String sql = "SELECT * FROM tradeitem WHERE trade_id = ?";
        return new HashSet<>(queryForList(sql, id));
    }
}
