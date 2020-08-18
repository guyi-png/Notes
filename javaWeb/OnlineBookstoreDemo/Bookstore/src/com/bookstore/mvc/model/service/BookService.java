package com.bookstore.mvc.model.service;

import com.bookstore.mvc.model.dao.BookDAO;
import com.bookstore.mvc.model.dao.BookDAOImpl;
import com.bookstore.mvc.model.domain.Book;
import com.bookstore.mvc.model.domain.ShoppingCart;
import com.bookstore.mvc.model.domain.ShoppingCartItem;
import com.bookstore.mvc.model.web.CriteriaBook;
import com.bookstore.mvc.model.web.Page;

import java.util.Collection;

public class BookService {
    private final BookDAO bookDAO = new BookDAOImpl();

    // 获取页面的信息
    public Page<Book> getPage(CriteriaBook criteriaBook){
        return bookDAO.getPage(criteriaBook);
    }

    //获取书的信息
    public Book getBook(int id){
        return bookDAO.getBook(id);
    }

    // 添加到购物车
    public String addToCart(int id, ShoppingCart shoppingCart){
        Book book = bookDAO.getBook(id);

        if (book != null){
            shoppingCart.addBook(book);
            return book.getTitle();
        }
        return null;
    }

    // 获取库存数
    public int getStoreNumber(int id){
        return bookDAO.getStoreNumber(id);
    }


    public void cash(Collection<ShoppingCartItem> items) {
        bookDAO.batchUpdateStoreNumberAndSalesAmount(items);
    }
}
