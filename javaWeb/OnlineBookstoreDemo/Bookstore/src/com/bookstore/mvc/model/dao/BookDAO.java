package com.bookstore.mvc.model.dao;

import com.bookstore.mvc.model.domain.Book;
import com.bookstore.mvc.model.domain.ShoppingCartItem;
import com.bookstore.mvc.model.web.CriteriaBook;
import com.bookstore.mvc.model.web.Page;

import java.util.Collection;
import java.util.List;

public interface BookDAO {
    /**
     * 根据id获取指定Book对象
     * @param id
     * @return Book对象
     */
    Book getBook(int id);

    /**
     * 根据传入的CriteriaBook对象返回对应的Page对象
     * @param cb
     * @return Page对象
     */
    Page<Book> getPage(CriteriaBook cb);

    /**
     * 根据传入的CriteriaBook对象返回对应的记录数
     * @param cb
     * @return 记录条数
     */
    long getTotalNumber(CriteriaBook cb);

    /**
     *  根据传入的CriteriaBook和每页记录数返回当页的list
     * @param cb
     * @param pageNumber
     * @return list
     */
    List<Book> getPageList(CriteriaBook cb, int pageNumber);

    /**
     * 根据id获取相应的book的库存数
     * @param id
     * @return 库存数
     */
    int getStoreNumber(Integer id);

    /**
     * 根据传入的ShoppingCartItem的集合批量更新book数据表的
     * storeNumber和salesAmount的值
     * @param items
     */
    void batchUpdateStoreNumberAndSalesAmount
    (Collection<ShoppingCartItem> items);
}
