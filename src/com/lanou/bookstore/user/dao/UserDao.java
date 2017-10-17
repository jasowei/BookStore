package com.lanou.bookstore.user.dao;

import com.lanou.bookstore.user.domain.User;

/**
 * 数据层
 */
public interface UserDao {
    User findByUsername(String username);

    User findByEmail(String email);

    void add(User form);

    User findByCode(String code);

    void updateState(String uid, boolean state);

    void edit(String uid, User form);
}
