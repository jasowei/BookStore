package com.lanou.bookstore.category.service.impl;

import com.lanou.bookstore.book.dao.BookDao;
import com.lanou.bookstore.book.dao.impl.BookDaoImpl;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.category.dao.AdminDao;
import com.lanou.bookstore.category.dao.impl.AdminDaoImpl;
import com.lanou.bookstore.category.domain.Admin;
import com.lanou.bookstore.category.service.AdminService;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;

import java.util.*;

/**
 * Created by dllo on 17/9/22.
 */
public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();
    private BookDao bookDao =new  BookDaoImpl();
    @Override
    public Admin login(String adminname, String password) {
        Admin admin = adminDao.login(adminname,password);
        return admin;
    }

    @Override
    public void addBook(Book book) {
        adminDao.addBook(book);
    }

    @Override
    public void del(String bid) {
        adminDao.del(bid);
    }

    @Override
    public void edit(Book book, String bid) {
        adminDao.edit(book,bid);
    }

    @Override
    public List<Order> findOrders() {
        List<Order> orders = adminDao.findOrders();
        oal(orders);
        return orders;
    }

    public List<String> findUid(List<Order> orders){
        List<String> uids = new ArrayList<>();
        for (Order order : orders) {
            String uid = order.getUid();
            uids.add(uid);
        }
        Set set = new HashSet();
        List<String> uID = new ArrayList<>();
        for (String s : uids) {
            if (set.add(s)){
                uID.add(s);
            }
        }
        return uID;
    }

    @Override
    public List findOrders(String uid) {
        List<Order> orders = adminDao.findOrders(uid);
        oal(orders);
        return orders;
    }

    @Override
    public List<Map<String, Object>> volume() {
        List<Map<String,Object>> map = adminDao.volume();
        return map;
    }

    @Override
    public int queryCountByBid(String bid) {
        int i = adminDao.queryCountByBid(bid);
        return i;
    }

    @Override
    public List<Order> findOrdersByState(String state, String uid) {
        List<Order> orders = adminDao.findOrdersByState(state,uid);
        oal(orders);
        return orders;
    }
    @Override
    public void oal(List<Order> orders){
        for (Order order : orders) {
            List<OrderItem> orderItems = adminDao.findByOid(order.getOid());
            for (OrderItem orderItem : orderItems) {
                int bid = Integer.parseInt(orderItem.getBid());
                Book book = bookDao.load(bid);
                orderItem.setBook(book);
            }
            order.setOrderList(orderItems);
        }
    }

    @Override
    public void sendOrder(String oid) {
        adminDao.sendOrder(oid);
    }


}
