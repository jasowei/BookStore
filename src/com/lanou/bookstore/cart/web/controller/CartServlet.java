package com.lanou.bookstore.cart.web.controller;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.cart.domain.CartItem;
import com.lanou.bookstore.category.service.CategoryService;
import com.lanou.bookstore.category.service.impl.CategoryServiceImpl;
import com.lanou.bookstore.user.domain.User;
import com.lanou.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by dllo on 17/9/21.
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {

    private CategoryService service = new CategoryServiceImpl();

    /**
     * 添加购物车条目
     */
    public String add(HttpServletRequest request, HttpServletResponse response){
        /*1.从session中得到车*/
        User user = (User) request.getSession().getAttribute("user");
        Cart cart = (Cart) request.getSession().getAttribute(user.getUsername());
        // 没登录,不让买
        if (user == null){
            request.setAttribute("msg","*请先登录再购买!");
            return "f:/jsps/user/login.jsp";
        }else {
        /*2.获取表单参数 bid  数量*/
            String bid = request.getParameter("bid");
            int count = Integer.parseInt(request.getParameter("count"));
        /*3.通过bid得到book 通过service*/
            Book book = service.load(Integer.parseInt(bid));
        /*4.使用book和数量创建Cartitem*/
            Map<String, CartItem> cartMap = cart.getCartMap();
            if (cartMap.containsKey(bid)) {
                int count1 = cartMap.get(bid).getCount();
                count += count1;
            }
            CartItem cartItem = new CartItem(book, count);
        /*5.把CartItem添加到车*/
            cartMap.put(bid, cartItem);
            cart.setCartMap(cartMap);
            request.getSession().setAttribute("cart", cart);
        /*6.返回/jsps/cart/list.jsp*/
            return "f:/jsps/cart/list.jsp";
        }
    }

    /**
     * 清空购物车
     */
    public String clear(HttpServletRequest request, HttpServletResponse response){
        /*1.从session中得到车*/
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        /*2.调用车的clear方法*/
        cart.clear(cart);
        /*3.返回*/
        return "f:/jsps/cart/list.jsp";
    }
    /**
     * 清空条目
     */
    public String delete(HttpServletRequest request, HttpServletResponse response){
        /*1.得到bid*/
        String bid = request.getParameter("bid");
        /*2.从session中得到车*/
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        /*3.调用车的delete方法*/
        cart.delete(cart,bid);
        /*4.返回*/
        return "f:/jsps/cart/list.jsp";
    }

}