package com.spring.useXml;

public interface BookShopDAO {
    // 通过ISBN获取书的价格
    Integer getBookPriceByIsbn(String isbn);

    // 根据isbn更新库存
    void updateBookStock(String isbn);

    // 通过username和price更新用户的账户余额
    void updateUserAccount(String username, int price);
}
