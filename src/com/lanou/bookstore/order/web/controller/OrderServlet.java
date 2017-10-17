package com.lanou.bookstore.order.web.controller;

import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.cart.domain.CartItem;
import com.lanou.bookstore.order.dao.OrderDao;
import com.lanou.bookstore.order.dao.impl.OrderDaoImpl;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;
import com.lanou.bookstore.order.service.OrderException;
import com.lanou.bookstore.order.service.OrderService;
import com.lanou.bookstore.order.service.impl.OrderServiceImpl;
import com.lanou.bookstore.user.domain.User;
import com.lanou.commons.CommonUtils;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/9/22.
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {

    private OrderService service = new OrderServiceImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    /**
     * 生成订单
     */
    public String add(HttpServletRequest request,HttpServletResponse response){
        /*1.创建Order对象*/
        Order order = new Order();
        //订单通过购物车生成
        User user = (User) request.getSession().getAttribute("user");
        //session中获取cart
        Cart cart = (Cart) request.getSession().getAttribute(user.getUsername());
        Map<String, CartItem> map = cart.getCartMap();
        if (map.isEmpty()){
            request.setAttribute("msg","*您还没有选购任何商品哦!");
            return "f:/jsps/order/msg.jsp";
        }
        //使用cart生成order
        order.setUid(user.getUid());
        // 获取系统时间
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String timeNow = String.valueOf(s.format(date));
        order.setOrdertime(timeNow);
        order.setOid(CommonUtils.uuid());
        order.setTotal(request.getParameter("total"));
        order.setState("0");
        /*2.service 的add(order)方法 清空车*/
        service.add(order);
        List<OrderItem> orderList = service.addOrderItemList(cart,order);
        order.setOrderList(orderList);
        new Cart().clear(cart);
        /*3.保存生成订单, 转发jsps/order/desc.jsp*/
        request.setAttribute("orders",order);
        return "f:/jsps/order/desc.jsp";

    }
    /**
     * 我的订单(用户查找)
     */
    public String myOrders(HttpServletRequest request,HttpServletResponse response){
        /*1.从session获取user  uid*/
        User user = (User) request.getSession().getAttribute("user");

            String uid = user.getUid();
        /*2.使用uid调用service方法,得到List<Order> 保存*/
            List<Order> orders = service.findeByUid(uid);
            request.setAttribute("orders", orders);
        /*3.转发*/
            return "f:/jsps/order/list.jsp";

    }
    /**
     * 加载订单
     */
    public String load(HttpServletRequest request,HttpServletResponse response){
        /*1.获取oid*/
        String oid = request.getParameter("oid");
        /*2.通过oid调用service得到Order*/
        Order order = service.findByOid(oid);
        /*3.保存,转发*/
        request.setAttribute("orders",order);
        return "f:/jsps/order/desc.jsp";

    }
    /**
     *确认收货
     */
    public String confirm(HttpServletRequest request,HttpServletResponse response){
        /*1.获取oid*/
        String oid = request.getParameter("oid");
        /*2.使用oid调用service*/
        try {
            service.confirm(oid);
        } catch (OrderException e) {
            //有异常,保存异常,转发msg.jsp
            request.setAttribute("msg",e.getMessage());
            return "f:/jsps/order/msg.jsp";
        }
        //无异常,保存成功,转发msg.jsp
        request.setAttribute("msg","订单已确认!欢迎下次光临!");
        return "f:/jsps/order/msg.jsp";
    }

    /**
     * 支付
     */
    public String pay(HttpServletRequest request,HttpServletResponse response){
        /*获取收货地址*/
        String address = request.getParameter("address");
        String oid = request.getParameter("oid");
        String phone = request.getParameter("phone");
        String state = orderDao.getStateByOid(oid);
            /*.通过oid调用service得到Order*/
        Order order = service.findByOid(oid);
        if (address.equals("")){
            /*.保存,转发*/
            request.setAttribute("orders",order);
            request.setAttribute("msg","* 支付失败,请填写收货地址!");
            return "f:/jsps/order/desc.jsp";
        }else if (phone.equals("")){
            request.setAttribute("orders",order);
            request.setAttribute("msg2","* 支付失败,请填写手机号!");
            return "f:/jsps/order/desc.jsp";

        }else {
            //收货地址添加到订单
            //修改状态为 1
            if (state.equals("0")) {
                orderDao.updateStateAdr(oid, "1",address);
                request.setAttribute("msg", "订单已付款, 请等待发货!");
                /**
                 * 发短信
                 */
                return "f:/jsps/order/send.jsp";
            }else {
                request.setAttribute("msg", "*订单已付款, 请勿重复支付");
                return "f:/jsps/order/send.jsp";
            }
        }
    }


}