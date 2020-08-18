package com.bookstore.mvc.controller;

import com.bookstore.mvc.model.domain.Book;
import com.bookstore.mvc.model.domain.Trade;
import com.bookstore.mvc.model.domain.TradeItem;
import com.bookstore.mvc.model.domain.User;
import com.bookstore.mvc.model.service.BookService;
import com.bookstore.mvc.model.service.TradeItemService;
import com.bookstore.mvc.model.service.TradeService;
import com.bookstore.mvc.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private final TradeService tradeService = new TradeService();
    private final TradeItemService tradeItemService = new TradeItemService();
    private final BookService bookService = new BookService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 该方法获取用户的交易记录
        String message = "";
        // 获取username请求参数值
        String username = request.getParameter("username").trim();

        // 先获取User对象
        User user = userService.getUserByUsername(username);
        if (user == null){
            message = "用户名错误";
            request.setAttribute("message", message);
            request.getRequestDispatcher("WEB-INF/page/trade.jsp")
                    .forward(request,response);
            return;
        }
        // 再获取Trade对象
        Set<Trade> trades = tradeService.getTradeByUserId(user.getUser_id());
        // 获取每个Trade对象对应的多个交易项, 然后装入trade中
        for (Trade trade : trades){
            Set<TradeItem> tradeItems = tradeItemService
                    .getTradeItemsByTradeId(trade.getTrade_id());

            if (tradeItems != null){
                // 为交易项添加book的信息
                for (TradeItem tradeItem : tradeItems){
                    Book book = bookService.getBook(tradeItem.getBook_id());
                    tradeItem.setBook(book);
                }
                trade.setItems(tradeItems);
            }
        }
        if (trades.isEmpty()){
            message = "没用对应的记录";
            request.setAttribute("message", message);
            request.getRequestDispatcher("WEB-INF/page/trade.jsp")
                    .forward(request,response);
            return;
        }
        // 将trades加入user中
        user.setTrades(trades);
        // 将user放入request
        request.setAttribute("user", user);
        // 请求的转发
        request.getRequestDispatcher("WEB-INF/page/trade.jsp")
                .forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
