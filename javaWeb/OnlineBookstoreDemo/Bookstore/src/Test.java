import com.bookstore.mvc.model.DAOutils.Utils;
import com.bookstore.mvc.model.connection.ConnectContext;
import com.bookstore.mvc.model.dao.*;
import com.bookstore.mvc.model.domain.Book;
import com.bookstore.mvc.model.domain.ShoppingCartItem;
import com.bookstore.mvc.model.domain.Trade;
import com.bookstore.mvc.model.domain.TradeItem;
import com.bookstore.mvc.model.web.CriteriaBook;

import java.sql.Date;
import java.util.ArrayList;

public class Test {
    static BookDAOImpl bookDAO = new BookDAOImpl();
    static AccountDAOImpl accountDAO = new AccountDAOImpl();
    static UserDAOImpl userDAO = new UserDAOImpl();
    static TradeDAOImpl tradeDAO = new TradeDAOImpl();
    static TradeItemDAOImpl tradeItemDAO  = new TradeItemDAOImpl();

    public static void main(String[] args) {
        ConnectContext.getInstance().bind(Utils.getConnection());

        testQuery();
    }

    static void testInsert(){
        String sql = "INSERT INTO trade(user_id, trade_time) VALUES(?, ?)";
        long id = bookDAO.insert(sql, 1, new Date(new java.util.Date().getTime()));
        System.out.println(id);
    }

    static void testUpdate(){
        String sql = "UPDATE books SET sales_amount = ? WHERE book_id = ?";
        bookDAO.update(sql, 10 ,  1);
    }

    static void testQuery(){
        String sql = "SELECT * FROM books WHERE book_id = ?";
        Book book = bookDAO.query(sql, 1);
        System.out.println(book);
    }

    static void testSingleValue(){
        String sql = "SELECT COUNT(*) FROM books";
        long count = bookDAO.querySingleValue(sql);
        System.out.println(count);
    }

    static void testBatch(){
        String sql = "UPDATE books SET sales_amount = ?, store_number= ? WHERE book_id = ?";
        bookDAO.batch(sql, new Object[]{3, 3, 1}, new Object[]{4,4, 2});
    }

    static void testBatchUpdateStoreNumberAndSalesAmount(){
        ArrayList<ShoppingCartItem> items = new ArrayList<>();
        items.add(new ShoppingCartItem(bookDAO.getBook(1)));
        items.add(new ShoppingCartItem(bookDAO.getBook(3)));
        items.add(new ShoppingCartItem(bookDAO.getBook(4)));

        bookDAO.batchUpdateStoreNumberAndSalesAmount(items);
    }

    static void testPage(){
        CriteriaBook criteriaBook = new CriteriaBook(1);
        System.out.println(bookDAO.getPage(criteriaBook));
    }

    static void testAccount(){
        System.out.println(accountDAO.get(123));
        accountDAO.updateBalance(123, 1);
    }

    static void testUser(){
        System.out.println(userDAO.getUser("怪鸽"));
    }

    static void testTrade(){
        Trade trade = new Trade();
        trade.setTrade_time(new Date(new java.util.Date().getTime()));
        trade.setUser_id(1);

        tradeDAO.insert(trade);
        System.out.println(tradeDAO.getTradeByUserId(1));
    }

    static void testTradeItem(){
        ArrayList<TradeItem> items = new ArrayList<>();
        items.add(new TradeItem(0, 1, 2,1));
        items.add(new TradeItem(0, 2, 34,2));
        tradeItemDAO.batchSave(items);
        System.out.println(tradeItemDAO.getTradeItemsByTradeId(2));
    }
}
