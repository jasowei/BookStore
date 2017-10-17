package com.lanou.bookstore.category.web.servlet.admin;

import com.lanou.bookstore.category.service.AdminService;
import com.lanou.bookstore.category.service.impl.AdminServiceImpl;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.user.dao.UserDao;
import com.lanou.bookstore.user.dao.impl.UserDaoImpl;
import com.lanou.bookstore.user.domain.User;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/9/23.
 */
@WebServlet("/AdminOrderServlet")
public class AdminOrderServlet extends BaseServlet {
    private AdminService service = new AdminServiceImpl();
    private UserDao userdao = new UserDaoImpl();
    /**
     * 所有订单
     */
    public String findOrders(HttpServletRequest request,HttpServletResponse response){
        List<Order> orders = service.findOrders();
        List<String> uids = service.findUid(orders);
        Map<String,List<Order>> ordersByuid = new HashMap<>();
        for (String uid : uids) {
            ordersByuid.put(uid,service.findOrders(uid));
        }
        request.setAttribute("map",ordersByuid);
        return "f:adminjsps/admin/order/list.jsp";
    }
    /**
     * 所有顾客
     */
    public String allUser(HttpServletRequest request,HttpServletResponse response){
        List<Order> orders = service.findOrders();
        List<String> uids = service.findUid(orders);
        request.setAttribute("users",uids);
        return "f:adminjsps/admin/cstm/list.jsp";
    }
    /**
     * 分类订单
     */
    public String findOrdersByState(HttpServletRequest request,HttpServletResponse response){
        String state = request.getParameter("state");
        List<Order> orders = service.findOrders();
        List<String> uids = service.findUid(orders);
        Map<String,List<Order>> ordersByuid = new HashMap<>();
        for (String uid : uids) {
            ordersByuid.put(uid,service.findOrdersByState(state,uid));
        }
        request.setAttribute("map",ordersByuid);
        return "f:adminjsps/admin/order/list.jsp";
    }
    /**
     * 发货
     */
    public String sendOrder(HttpServletRequest request,HttpServletResponse response){
        String oid = request.getParameter("oid");
        service.sendOrder(oid);
        return findOrders(request,response);
    }
    /**
     * 销量
     */
    public String volume(HttpServletRequest request,HttpServletResponse response){
        List<Map<String, Object>> map = service.volume();
        request.setAttribute("list",map);
        return "f:adminjsps/admin/book/volume.jsp";
    }

}