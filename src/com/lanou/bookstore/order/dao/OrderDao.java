package com.lanou.bookstore.order.dao;

import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public interface OrderDao {
    void addOrder(Order order);

    void addOrderItem (OrderItem orderItem);

    List<Order> findByUid(String uid);

    List<OrderItem> findByOid(String oid);

    Order findByOid1(String oid);

    String getStateByOid(String oid);

    void updateState(String oid, String state);
    void updateStateAdr(String oid, String state,String adr);
}
