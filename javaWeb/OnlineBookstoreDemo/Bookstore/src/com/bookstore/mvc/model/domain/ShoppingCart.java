package com.bookstore.mvc.model.domain;

import java.text.DecimalFormat;
import java.util.*;

/**
 * 购物车的信息
 */
public class ShoppingCart {
    // 保存购物车的内容，key 是书的id
    private final Map<Integer, ShoppingCartItem> books = new HashMap<>();

    // 向购物车中添加一件商品
    public void addBook(Book book){
        // 先验证购物车中有没有此商品
        ShoppingCartItem item = books.get(book.getBook_id());
        if ( item == null){
            // 没有就再购物车中创建新项
            item = new ShoppingCartItem(book);
            books.put(book.getBook_id(), item);
        }else{
            // 有就让购物车中的该商品数量加1
            item.increment();
        }
    }

    // 查看有没有指定的商品(书)
    public boolean hasBook(int id){
        return books.containsKey(id);
    }

    //返回购物车中商品的总数量
    public int getBookNumber(){
        int total = 0;
        Collection<ShoppingCartItem> items = books.values();
        for (ShoppingCartItem item : items){
            total += item.getQuantity();
        }
        return total;
    }

     // 获取所有ShoppingCartItem的信息
    public Collection<ShoppingCartItem> getItems(){
        return books.values();
    }

    // 获取总钱数
    public double getTotalMoney(){
        double total = 0;
        Collection<ShoppingCartItem> values = books.values();
        for (ShoppingCartItem item : values){
            total += item.getItemMoney();
        }
        // 保留四位数
        DecimalFormat decimalFormat = new DecimalFormat("#.0000");
        String format = decimalFormat.format(total);
        return Double.parseDouble(format);
    }

    // 判断购物车是否为空
    public boolean isEmpty(){
        return books.isEmpty();
    }

    // 清空购物车
    public void clear(){
        books.clear();
    }

    // 移除指定的商品
    public void removeItem(int id){
        books.remove(id);
    }

    // 更新ShoppingItem的数量
    public void updateItemQuantity(int id, int quantity){
        ShoppingCartItem item = books.get(id);
        if (item != null){
            item.setQuantity(quantity);
        }
    }
}
