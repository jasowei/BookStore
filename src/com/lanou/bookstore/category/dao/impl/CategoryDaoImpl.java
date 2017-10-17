package com.lanou.bookstore.category.dao.impl;

import com.lanou.bookstore.category.dao.CategoryDao;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class CategoryDaoImpl implements CategoryDao{
    QueryRunner qr = new GxQueryRunner();
    @Override
    public List<Category> findAll() {
        String sql = "select * from category";
        try {
            List<Category> query = qr.query(sql, new BeanListHandler<>(Category.class));
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Category category) {
        String sql ="insert into category values (?,?)";
        Object[] params = {category.getCid(),category.getCname()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String cid) {
        String sql = "delete from category where cid = ?";
        try {
            qr.update(sql,cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category findByCid(String cid) {
        String sql = "select * from category where cid = ?";
        try {
            Category query = qr.query(sql, new BeanHandler<>(Category.class),cid);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void edit(String cname,String cid)  {
        String sql ="update category set cname = ? where cid = ?";
        Object[] p = {cname,cid};
        try {
            qr.update(sql,p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
