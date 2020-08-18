package com.bookstore.mvc.model.dao;

import com.bookstore.mvc.model.domain.TradeItem;

import java.util.Collection;
import java.util.Set;

public interface TradeItemDAO {
    /**
     * 批量保存TradeItem对象
     * @param items
     */
    void batchSave(Collection<TradeItem> items);

    /**
     * 通过trade的id获取交易项
     * @param id
     * @return
     */
    Set<TradeItem> getTradeItemsByTradeId(int id);
}
