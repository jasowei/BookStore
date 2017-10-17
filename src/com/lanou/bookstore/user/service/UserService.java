package com.lanou.bookstore.user.service;

import com.lanou.bookstore.user.domain.User;

/**
 * 业务层
 */
public interface UserService {
    void regist(User form) throws UserException;

    void active(String code) throws UserException;

    User login(User form) throws UserException;

    void edit(String uid, User form);
}
