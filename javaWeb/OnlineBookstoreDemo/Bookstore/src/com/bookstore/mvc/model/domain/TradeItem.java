package com.bookstore.mvc.model.domain;

public class TradeItem {
    private int item_id;
    private int book_id;
    private int quantity;
    private int trade_id;
    private Book book;

    public TradeItem() {}

    public TradeItem(int item_id, int book_id, int quantity, int trade_id) {
        this.item_id = item_id;
        this.book_id = book_id;
        this.quantity = quantity;
        this.trade_id = trade_id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(int trade_id) {
        this.trade_id = trade_id;
    }

    @Override
    public String toString() {
        return "TradeItem{" +
                "item_id=" + item_id +
                ", book_id=" + book_id +
                ", quantity=" + quantity +
                ", trade_id=" + trade_id +
                ", book=" + book +
                '}';
    }
}
