package com.bookstore.mvc.model.domain;


/**
 * 购物车中每一项商品的信息
 * 包括购买的书和该书的数量
 */
public class ShoppingCartItem {
    private Book book;
    private int quantity; //数量

    public ShoppingCartItem(Book book) {
        this.book = book;
        quantity = 1;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // 这项商品的总钱数(书的单价*数量)
    public double getItemMoney(){
        return book.getPrice() * quantity;
    }

    // 增加书的购买数量
    public void increment(){
        quantity++;
    }
}
