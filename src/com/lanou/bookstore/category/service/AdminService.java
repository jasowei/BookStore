package com.lanou.bookstore.category.service;


import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.category.domain.Admin;
import com.lanou.bookstore.order.domain.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/9/22.
 */
public interface AdminService {
    Admin login(String adminname, String password);

    void addBook(Book book);

    void del(String bid);

    void edit(Book book, String bid);

    List<Order> findOrders();

    List<Order> findOrdersByState(String state, String uid);

    void sendOrder(String oid);

    void oal(List<Order> orders);

    List<String> findUid(List<Order> orders);

    List findOrders(String uid);

    List<Map<String, Object>> volume();

    int queryCountByBid(String bid);
}
