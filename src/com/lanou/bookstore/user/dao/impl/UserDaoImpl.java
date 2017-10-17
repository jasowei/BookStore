package com.lanou.bookstore.user.dao.impl;

import com.lanou.bookstore.user.dao.UserDao;
import com.lanou.bookstore.user.domain.User;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * 数据层 > 实现类
 */
public class UserDaoImpl implements UserDao {

    private QueryRunner qr = new GxQueryRunner();

    @Override
    public User findByUsername(String username) {
        String sql = "select * from tb_user where username = ?";
        try {
            return qr.query(sql,new BeanHandler<>(User.class),username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByEmail(String email) {
        String sql = "select * from tb_user where email = ?";
        try {
            return qr.query(sql,new BeanHandler<>(User.class),email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByCode(String code) {
        String sql = "select * from tb_user where code = ?";
        try {
            return qr.query(sql,new BeanHandler<>(User.class),code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateState(String uid, boolean state) {
        String sql = "update tb_user set state = 1 where uid = ?";
        try {
            qr.update(sql,uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void edit(String uid, User form) {
        String sql = "update tb_user set username = ? , password = ? , email = ? where uid = ?";
        Object[] p = {form.getUsername(),form.getPassword(),form.getEmail(),uid};
        try {
            qr.update(sql,p);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(User form) {
        String sql ="insert into tb_user (uid,username,password,email,code,state) " +
                "values (?,?,?,?,?,?) ";
        Object[] params = {form.getUid(),form.getUsername(),form.getPassword(),form.getEmail()
        ,form.getCode(),form.isState()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
