package com.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "bookShopService")
public class BookShopServiceImpl implements BookShopService{
    private BookShopDAO bookShopDAO;

    @Autowired(required = false)
    public void setBookShopDAO(BookShopDAO bookShopDAO) {
        this.bookShopDAO = bookShopDAO;
    }

    @Transactional  // 事务注解, 此方法就是一个事务
    @Override
    public void purchase(String username, String isbn) {
        // 获取书的单价
        Integer price = bookShopDAO.getBookPriceByIsbn(isbn);
        // 更新书的库存
        bookShopDAO.updateBookStock(isbn);
        // 更新用户的余额
        bookShopDAO.updateUserAccount(username, price);
    }
}
