package com.bookstore.mvc.model.dao;


import com.bookstore.mvc.model.domain.Book;
import com.bookstore.mvc.model.domain.ShoppingCartItem;
import com.bookstore.mvc.model.web.CriteriaBook;
import com.bookstore.mvc.model.web.Page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO{
    @Override
    public Book getBook(int id) {
        String sql = "SELECT * FROM books WHERE book_id = ?";
        return query(sql, id);
    }

    @Override
    public Page<Book> getPage(CriteriaBook cb) {
        Page<Book> page = new Page<>(cb.getPageNo());
        // 先初始化总记录条数
        long totalNumber = getTotalNumber(cb);
        page.setTotalNumber(totalNumber);

        // 再校验pageNo的合法性
        cb.setPageNo(page.getPageNo());

        List<Book> list = getPageList(cb, page.getPageNumber());

        page.setList(list);
        return page;
    }

    @Override
    public long getTotalNumber(CriteriaBook cb) {
        String sql = "SELECT COUNT(*) FROM books WHERE price BETWEEN ? AND ?";
        return querySingleValue(sql, cb.getMinPrice(), cb.getMaxPrice());
    }

    @Override
    public List<Book> getPageList(CriteriaBook cb, int pageNumber) {
        String sql = "SELECT * FROM books WHERE price BETWEEN ? AND ?" +
                " LIMIT ?, ?";
        List<Book> list = queryForList(sql, cb.getMinPrice(), cb.getMaxPrice(),
                (cb.getPageNo() - 1) * pageNumber, pageNumber);
        return list;
    }

    @Override
    public int getStoreNumber(Integer id) {
        String sql = "SELECT store_number FROM books WHERE book_id = ?";
        return querySingleValue(sql, id);
    }

    @Override
    public void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items) {
        String sql = "UPDATE books SET sales_amount = sales_amount + ?, " +
                "store_number = store_number - ? WHERE book_id = ?";
        Object[][] params = new Object[items.size()][3];
        List<ShoppingCartItem> list = new ArrayList<>(items);

        for (int i =0; i < list.size(); i++){
            params[i][0] = list.get(i).getQuantity();
            params[i][1] = list.get(i).getQuantity();
            params[i][2] = list.get(i).getBook().getBook_id();
        }
        batch(sql, params);
    }
}
