package com.lanou.bookstore.category.dao.impl;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.category.domain.Admin;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.lanou.bookstore.category.dao.AdminDao;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/9/22.
 */
public class AdminDaoImpl implements AdminDao {
    QueryRunner qr = new GxQueryRunner();
    @Override
    public Admin login(String adminname, String password) {
        String sql = "select * from tb_admin where aname= ? and password= ?";
        Object[] p = {adminname,password};
        try {
            Admin admin = qr.query(sql, new BeanHandler<>(Admin.class), p);
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addBook(Book book) {
        String sql ="insert into book values (?,?,?,?,?,?,?)";
        Object[] p = {book.getBid(),book.getBname(),book.getPrice(),book.getAuthor(),
                book.getImage(),book.getCid(),book.isDel()};
        try {
            qr.update(sql,p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void del(String bid) {
        String sql = "update book set del = true where bid = ?";
        try {
            qr.update(sql,bid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Book book, String bid) {
        String sql = "update book set bname = ? , price=? , author=? ," +
                "cid=? where bid = ?";
        Object[] p = {book.getBname(),book.getPrice(),book.getAuthor(),book.getCid(),bid};
        try {
            qr.update(sql,p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> findOrders() {
        String sql = "select * from orders ";
        try {
            List<Order> query = qr.query(sql, new BeanListHandler<>(Order.class));
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> findOrdersByState(String state, String uid) {
        String sql = "select * from orders where state = ? and uid = ?";
        Object[] p = {state,uid};
        try {
            List<Order> query = qr.query(sql, new BeanListHandler<>(Order.class),p);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderItem> findByOid(String oid) {
        String sql = "select * from orderitem where oid = ?";
        try {
            List<OrderItem> query = qr.query(sql, new BeanListHandler<>(OrderItem.class),oid);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sendOrder(String oid) {
        String sql = "update orders set state = 2 where oid = ?";
        try {
            qr.update(sql,oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> findOrders(String uid) {
        String sql = "select * from orders where uid = ?";
        try {
            List<Order> query = qr.query(sql, new BeanListHandler<>(Order.class),uid);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> volume() {
        String sql = "select bname,count(*) c,b.bid from book b,orderitem o where b.bid=o.bid group by o.bid order by c desc";
        try {
            List<Map<String,Object>> query = qr.query(sql, new MapListHandler());
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int queryCountByBid(String bid) {
        String sql = "select count(*) from orderitem where bid = ? group by bid";
        try {
            Number query = qr.query(sql, new ScalarHandler<>(), bid);
            if (query==null){
                return 0;
            }
            return query.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
