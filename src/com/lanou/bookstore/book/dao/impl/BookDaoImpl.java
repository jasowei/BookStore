package com.lanou.bookstore.book.dao.impl;

import com.lanou.bookstore.book.dao.BookDao;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class BookDaoImpl implements BookDao{
    private QueryRunner qr = new GxQueryRunner();
    @Override
    public List<Book> findAll() {
        String sql = "select * from book where del=false";
        try {
            List<Book> books = qr.query(sql, new BeanListHandler<>(Book.class));
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Book> findAll2() {
        String sql = "select * from book";
        try {
            List<Book> books = qr.query(sql, new BeanListHandler<>(Book.class));
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> findByCategory(int cid) {
        String sql = "select * from book where cid = ? and del=false";
        try {
            List<Book> books =qr.query(sql,new BeanListHandler<>(Book.class),cid);
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book load(int bid) {
        String sql = "select * from book where bid = ? and del=false";
        try {
            Book book =qr.query(sql,new BeanHandler<>(Book.class),bid);
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<Book> search(String s) {
        String sql = "select * from book where  bname like ? or author like ?";
        try {
            List<Book> books =qr.query(sql,new BeanListHandler<>(Book.class),s,s);
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
