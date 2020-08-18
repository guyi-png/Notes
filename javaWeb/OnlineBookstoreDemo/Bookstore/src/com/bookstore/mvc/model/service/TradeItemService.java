package com.bookstore.mvc.model.service;

import com.bookstore.mvc.model.dao.TradeItemDAOImpl;
import com.bookstore.mvc.model.domain.ShoppingCartItem;
import com.bookstore.mvc.model.domain.TradeItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class TradeItemService {
    private final TradeItemDAOImpl tradeItemDAO = new TradeItemDAOImpl();

    public void batchSave(Collection<ShoppingCartItem> items, long tradeId){
        Collection<TradeItem> tradeItems = new ArrayList<>();

        for (ShoppingCartItem item : items){
            // 通过shoppingCartItem初始化TradeItem
            TradeItem tradeItem = new TradeItem();
            tradeItem.setTrade_id( (int) tradeId);
            tradeItem.setBook_id(item.getBook().getBook_id());
            tradeItem.setQuantity(item.getQuantity());

            tradeItems.add(tradeItem);
        }
        tradeItemDAO.batchSave(tradeItems);
    }

    public Set<TradeItem> getTradeItemsByTradeId(int id) {
        return tradeItemDAO.getTradeItemsByTradeId(id);
    }
}
