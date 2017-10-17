package com.lanou.bookstore.order.service.impl;

import com.lanou.bookstore.book.dao.BookDao;
import com.lanou.bookstore.book.dao.impl.BookDaoImpl;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.cart.domain.CartItem;
import com.lanou.bookstore.category.service.AdminService;
import com.lanou.bookstore.category.service.impl.AdminServiceImpl;
import com.lanou.bookstore.order.dao.OrderDao;
import com.lanou.bookstore.order.dao.impl.OrderDaoImpl;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;
import com.lanou.bookstore.order.service.OrderException;
import com.lanou.bookstore.order.service.OrderService;
import com.lanou.commons.CommonUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/9/21.
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    private AdminService service = new AdminServiceImpl();
    @Override
    public void add(Order order) {
        // 保存订单
        orderDao.addOrder(order);
    }

    //保存订单所有条目
    @Override
    public List<OrderItem> addOrderItemList(Cart cart,Order order) {
        List<OrderItem> orderItems = new LinkedList<>();
        Map<String, CartItem> cartMap = cart.getCartMap();
        for (Map.Entry<String,CartItem> entry : cartMap.entrySet()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setIid(CommonUtils.uuid());
            orderItem.setOid(order.getOid());
            orderItem.setBid(entry.getKey());
            CartItem cartItem = entry.getValue();
            Book book = cartItem.getBook();
            String count = String.valueOf(cartItem.getCount());
            orderItem.setCount(count);
            double p = Double.parseDouble(book.getPrice());
            double c = Double.parseDouble(count);
            orderItem.setSubtotal(String.valueOf(p*c));
            orderItem.setBook(book);
            // 封装集合
            orderItems.add(orderItem);
            orderDao.addOrderItem(orderItem);
        }
        return orderItems;
    }

    @Override
    public List<Order> findeByUid(String uid) {
        List<Order> orders = orderDao.findByUid(uid);
        service.oal(orders);
        return orders;
    }

    @Override
    public Order findByOid(String oid) {
        Order order = orderDao.findByOid1(oid);
        List<OrderItem> orderItems = orderDao.findByOid(oid);
        for (OrderItem orderItem : orderItems) {
            Book book = bookDao.load(Integer.parseInt(orderItem.getBid()));
            orderItem.setBook(book);
        }
        order.setOrderList(orderItems);
        return order;
    }

    @Override
    public void confirm(String oid) throws OrderException {
        /*1.oid查询订单状态*/
        String state = orderDao.getStateByOid(oid);
        /*2.如果不是3,抛*/
        if (state.equalsIgnoreCase("3")){
            throw new OrderException("*您不可执行此操作!");
        }
        /*3.修改状态*/
        if (state.equalsIgnoreCase("2")){
            orderDao.updateState(oid,"3");
        }
    }
}
