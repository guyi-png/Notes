package com.bookstore.mvc.model.dao;

import com.bookstore.mvc.model.domain.Account;

public class AccountDAOImpl extends BaseDAO<Account> implements AccountDAO{

    @Override
    public Account get(Integer id) {
        String sql = "SELECT * FROM account WHERE account_id = ?";
        return query(sql, id);
    }

    @Override
    public void updateBalance(Integer id, double amount) {
        String sql = "UPDATE account SET balance = balance -? WHERE account_id = ?";
        update(sql, amount, id);
    }
}
