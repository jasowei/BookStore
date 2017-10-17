package com.lanou.bookstore.order.dao.impl;

import com.lanou.bookstore.order.dao.OrderDao;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;
import com.lanou.bookstore.order.service.OrderService;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class OrderDaoImpl implements OrderDao{
    QueryRunner qr = new GxQueryRunner();
    @Override
    public void addOrder(Order order) {
        String sql ="insert into orders (oid,ordertime,total,state,uid,address)" +
                " values (?,?,?,?,?,?)";
        Object[] params = {order.getOid(),order.getOrdertime(),order.getTotal(),order.getState()
        ,order.getUid(),order.getAddress()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        String sql ="insert into orderitem (iid,count,subtotal,oid,bid)" +
                " values (?,?,?,?,?)";
        Object[] params = {orderItem.getIid(),orderItem.getCount(),orderItem.getSubtotal()
        ,orderItem.getOid(),orderItem.getBid()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Order> findByUid(String uid) {
        String sql ="select * from orders where uid = ?";
        try {
            List<Order> query = qr.query(sql, new BeanListHandler<>(Order.class), uid);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderItem> findByOid(String oid) {
        String sql ="select * from orderitem where oid = ?";
        try {
            List<OrderItem> query = qr.query(sql, new BeanListHandler<>(OrderItem.class), oid);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order findByOid1(String oid) {
        String sql ="select * from orders where oid = ?";
        try {
            Order query = qr.query(sql, new BeanHandler<>(Order.class), oid);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getStateByOid(String oid) {
        String sql ="select state from orders where oid = ?";
        try {
            Order order = qr.query(sql,new BeanHandler<>(Order.class), oid);
            return order.getState();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateState(String oid, String state) {
        String sql ="update orders set state = ?  where oid = ?";
        Object[] params = {state,oid};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void updateStateAdr(String oid, String state, String adr) {
        String sql ="update orders set state = ? , address= ? where oid = ?";
        Object[] params = {state,adr,oid};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
