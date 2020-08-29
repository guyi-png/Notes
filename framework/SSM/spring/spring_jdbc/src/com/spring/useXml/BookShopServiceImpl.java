package com.spring.useXml;


public class BookShopServiceImpl implements BookShopService {
    private BookShopDAO bookShopDAO;

    public void setBookShopDAO(BookShopDAO bookShopDAO) {
        this.bookShopDAO = bookShopDAO;
    }

    public void purchase(String username, String isbn) {
        // 获取书的单价
        Integer price = bookShopDAO.getBookPriceByIsbn(isbn);
        // 更新书的库存
        bookShopDAO.updateBookStock(isbn);
        // 更新用户的余额
        bookShopDAO.updateUserAccount(username, price);
    }
}
