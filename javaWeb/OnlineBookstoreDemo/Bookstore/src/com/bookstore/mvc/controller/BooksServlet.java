package com.bookstore.mvc.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.bookstore.mvc.model.domain.Book;
import com.bookstore.mvc.model.domain.ShoppingCart;
import com.bookstore.mvc.model.domain.ShoppingCartItem;
import com.bookstore.mvc.model.domain.User;
import com.bookstore.mvc.model.service.*;
import com.bookstore.mvc.model.web.CriteriaBook;
import com.bookstore.mvc.model.web.Page;
import com.bookstore.mvc.model.webUtils.BookstoreWebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "BooksServlet")
public class BooksServlet extends HttpServlet {
    private final BookService bookService = new BookService();
    private final UserService userService = new UserService();
    private final AccountService accountService = new AccountService();
    private final TradeService tradeService = new TradeService();
    private final TradeItemService tradeItemService = new TradeItemService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 利用反射通过方法名字调用相应方法
        String methodName = request.getParameter("method");
        try {
            Method method = getClass()
                    .getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this, request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取展示页面的信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void getBooks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 读取请求的数据
        String pageNoStr = request.getParameter("pageNo");
        String minPriceStr = request.getParameter("minPrice");
        String maxPriceStr = request.getParameter("maxPrice");

        int pageNo = 1;
        double minPrice = 0, maxPrice = Integer.MAX_VALUE;
        // 类型转换
        try {
            if (pageNoStr != null)
                pageNo = Integer.parseInt(pageNoStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            if (minPriceStr != null)
                minPrice = Double.parseDouble(minPriceStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            if (maxPriceStr != null)
                maxPrice = Double.parseDouble(maxPriceStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        //  提供筛选条件和当前页数获取页面信息
        CriteriaBook criteriaBook = new CriteriaBook(minPrice, maxPrice, pageNo);
        Page<Book> page = bookService.getPage(criteriaBook);

        // 与request域绑定，请求转发
        request.setAttribute("page", page);
        request.getRequestDispatcher("/WEB-INF/page/books.jsp")
                .forward(request, response);
    }

    /**
     * 获取每本书的详细信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void getBook(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = -1;

        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Book book = null;
        if (id > 0) {
            book = bookService.getBook(id);
        }
        if (book == null){
            response.sendRedirect(request.getContextPath()+"/error-1.html");
            return;
        }
        request.setAttribute("book", book);
        request.getRequestDispatcher("WEB-INF/page/book.jsp")
                .forward(request, response);
    }

    /**
     * 将商品添加到购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void addToCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        // 获取商品的id
        String idStr = request.getParameter("id");
        int id = -1;

        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // 获取购物车对象
        BookstoreWebUtils.createShoppingCart(request);
        ShoppingCart shoppingCart = (ShoppingCart)
                request.getSession().getAttribute("shoppingCart");

        String title = null;
        //调用BookService的addToCart()把商品放入购物车中
        if (id > 0){
             title = bookService.addToCart(id, shoppingCart);
        }

        // 判断是否添加成功
        if (title != null && !"".equals(title.trim())){
            request.setAttribute("title", title);
            // 调用getBooks方法返回当前页
            getBooks(request, response);
        }else{
            response.sendRedirect(request.getContextPath()+"/error-2.html");
        }
    }

    /**
     *  到某个页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void toPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String pageName = request.getParameter("pageName");
        // 利用servlet访问WEB-INF下的文件
        request.getRequestDispatcher("WEB-INF/page/"+pageName+".jsp").forward(request,response);
    }

    /**
     * 移除购物车中对应的商品
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        // 获取id
        String idStr = request.getParameter("id");
        int id = -1;

        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        ShoppingCart shoppingCart = null;
        if (id > 0){
            // 通过id将对应的商品删除
            HttpSession session = request.getSession();
            shoppingCart =
                    (ShoppingCart) session.getAttribute("shoppingCart");
            shoppingCart.removeItem(id);
        }

        if (shoppingCart != null && !shoppingCart.isEmpty()){
            // 请求的转发
            request.getRequestDispatcher("WEB-INF/page/cart.jsp")
                    .forward(request,response);
        }else{
            request.getRequestDispatcher("WEB-INF/page/emptyCart.jsp")
                    .forward(request,response);
        }
    }

    /**
     * 清空购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void clear(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        // 清除购物车
        ( (ShoppingCart)request.getSession().getAttribute("shoppingCart") )
                .clear();
        request.getRequestDispatcher("WEB-INF/page/emptyCart.jsp").forward(request,response);
    }

    /**
     * 通过请求获得的数量更改书的数量
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void updateItemQuantity(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        // 从请求中获取书的id， 对应的quantity，然后修改数量
        String idStr = request.getParameter("id");
        String quantityStr = request.getParameter("quantity");
        // 获取购物车
        ShoppingCart shoppingCart = (ShoppingCart)request.getSession()
                .getAttribute("shoppingCart");
        int id = -1;
        int quantity = -1;

        try {
            id = Integer.parseInt(idStr);
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
             e.printStackTrace();
        }
        if (id > 0 && quantity > 0){
            // 修改书对应的购买数量
            shoppingCart.updateItemQuantity(id, quantity);
        }
        // 通过JSON返回数据
        Map<String, Object> result = new HashMap<>();
        result.put("bookNumber", shoppingCart.getBookNumber());
        result.put("totalMoney", shoppingCart.getTotalMoney());

        // 转为JSON字符串
        String jsonString = JSONUtils.toJSONString(result);
        response.setContentType("text/javascript");
        response.getWriter().print(jsonString);
    }

    /**
     * 交易的处理，包括表单的验证，数据的校验和购买时的业务处理
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void cash(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        // 该方法可以考虑优化
// ---------------------------------------------------------
        // 验证表单是否重复提交
        HttpSession session = request.getSession(false);
        Object token = session.getAttribute("token"); //获取表单标记
        String token1 = request.getParameter("token"); //为了更安全，用隐藏标签中的标记与上面的标记比较
        if (token1.equals(token)){
            session.removeAttribute("token");
        }else{
            request.getRequestDispatcher("WEB-INF/page/repeat.jsp")
            .forward(request,response);
            return;
        }

// ---------------------------------------------------------
        // 简单验证
        // 用于保存错误信息
        StringBuilder sb = new StringBuilder();
        String username = request.getParameter("username");
        String accountIdStr = request.getParameter("accountId");
        int accountId = -1;

        if (username == null || "".equals(username.trim()) ){
            sb.append("用户名不可为空<br>");
        }
        if (accountIdStr == null || "".equals(accountIdStr.trim()) ){
            sb.append("账户id不可为空<br>");
        }
        try {
            if (accountIdStr != null && !"".equals(accountIdStr.trim()))
                accountId = Integer.parseInt(accountIdStr.trim());
        } catch (NumberFormatException e) {
            sb.append("账户id请输入数字<br>");
        }
        if (!"".equals(sb.toString())){
            request.setAttribute("message", sb);
            request.getRequestDispatcher("WEB-INF/page/buy.jsp")
                    .forward(request,response);
            return;
        }

// ---------------------------------------------------------
        // 验证用户和账户id是否匹配(正确)
        // 用于保存错误信息
        StringBuilder sb1 = new StringBuilder();

        User user = userService.getUserByUsername(username);
        if (user != null){
            // 获取用户的账户id
            int account_id = user.getAccount_id();
            // 与键入账户id是否相同
            if (account_id != accountId){
                sb1.append("账户id错误");
            }
        }else{
            sb1.append("用户名错误或用户名不存在");
        }
        if (!"".equals(sb1.toString())){
            request.setAttribute("message", sb1);
            request.getRequestDispatcher("WEB-INF/page/buy.jsp")
                    .forward(request,response);
            return;
        }

// ---------------------------------------------------------
        // 验证库存
        // 用于保存错误信息
        StringBuilder sb2 = new StringBuilder();

        ShoppingCart shoppingCart = (ShoppingCart)
                request.getSession().getAttribute("shoppingCart");
        // 获取所有shoppingItem项
        Collection<ShoppingCartItem> items = shoppingCart.getItems();

        for (ShoppingCartItem item : items){
            int quantity = item.getQuantity();
            // 从数据库读取
            int store_number = bookService.
                    getStoreNumber(item.getBook().getBook_id());

            if (store_number < quantity){
                sb2.append("《").append(item.getBook().getTitle()).append("》库存不足<br>");
            }
        }
        if (!"".equals(sb2.toString())){
            request.setAttribute("message", "非常抱歉,<br>"+sb2);
            request.getRequestDispatcher("WEB-INF/page/buy.jsp")
                    .forward(request,response);
            return;
        }

// ---------------------------------------------------------
        // 验证余额是否不足
        // 用于保存错误信息
        StringBuilder sb3 = new StringBuilder();

        double totalMoney = shoppingCart.getTotalMoney();
        // 从数据库中查看账户余额
        double balance = accountService.getAccount(accountId).getBalance();
        if (balance < totalMoney){
            sb3.append("您的账户中的余额不足");
        }
        if (!"".equals(sb3.toString())){
            request.setAttribute("message", sb3);
            request.getRequestDispatcher("WEB-INF/page/buy.jsp")
                    .forward(request,response);
            return;
        }
// ---------------------------------------------------------
        // 执行购买时的业务操作
        // 更新数据库中books表的store_number，sales_amount
        bookService.cash(items);
        // 更新数据库中account表的balance
        accountService.updateBalance(accountId, shoppingCart.getTotalMoney());
        // 更新数据库中trade表数据
        long tradeId = tradeService.insert(user.getUser_id());
        // 更新数据库中tradeitem表数据
        tradeItemService.batchSave(items, tradeId);
        // 清空购物车
        shoppingCart.clear();

        // 以上执行购买时的业务操作是同一个事务，要么一起成功，要么一起失败
        // 所以要实现处理事务的功能
        // 使用 ThreadLocal+ Filter处理事务
// ---------------------------------------------------------
        request.getRequestDispatcher("WEB-INF/page/success.jsp")
        .forward(request,response);
    }
}
