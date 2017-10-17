package com.lanou.bookstore.order.service;

import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public interface OrderService {
    void add(Order order);

    List<OrderItem> addOrderItemList(Cart cart,Order order);

    List<Order> findeByUid(String uid);

    Order findByOid(String oid);

    void confirm(String oid) throws OrderException;

}
