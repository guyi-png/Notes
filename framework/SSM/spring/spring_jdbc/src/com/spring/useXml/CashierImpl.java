package com.spring.useXml;


import java.util.List;

public class CashierImpl implements Cashier {
    private BookShopService bookShopService;

    public void setBookShopService(BookShopService bookShopService) {
        this.bookShopService = bookShopService;
    }

    public void checkout(String username, List<String> isbnList) {
        for (String isbn : isbnList){
            bookShopService.purchase(username, isbn);
        }
    }
}
