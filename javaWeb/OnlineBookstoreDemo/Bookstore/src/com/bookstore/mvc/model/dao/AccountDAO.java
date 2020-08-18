package com.bookstore.mvc.model.dao;

import com.bookstore.mvc.model.domain.Account;

public interface AccountDAO {
    /**
     * 根据account_id获取对应的account对象
     * @param id
     * @return
     */
    Account get(Integer id);

    /**
     * 根据id更新账户余额
     * @param id
     * @param amount
     */
    void updateBalance(Integer id, double amount);
}
