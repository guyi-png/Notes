package com.bookstore.mvc.model.service;

import com.bookstore.mvc.model.dao.AccountDAOImpl;
import com.bookstore.mvc.model.domain.Account;

public class AccountService {
    private final AccountDAOImpl accountDAO = new AccountDAOImpl();

    // 获取账户信息
    public Account getAccount(int id){
        return accountDAO.get(id);
    }

    // 更新账户数据
    public void updateBalance(Integer id, double amount){
        accountDAO.updateBalance(id, amount);
    }

}
