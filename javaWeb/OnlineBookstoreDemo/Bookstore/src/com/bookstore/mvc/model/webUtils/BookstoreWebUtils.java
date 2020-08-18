package com.bookstore.mvc.model.webUtils;

import com.bookstore.mvc.model.domain.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BookstoreWebUtils {
    /**
     * 从session中获取购物车的对象，若session中有则返回，
     * 没有就创建一个购物车对象
     * @param request 用于获取session
     */
    public static void createShoppingCart(HttpServletRequest request){
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart)session.getAttribute("shoppingCart");

        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            session.setAttribute("shoppingCart", shoppingCart);
        }
    }
}
