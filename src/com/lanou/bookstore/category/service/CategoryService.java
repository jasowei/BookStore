package com.lanou.bookstore.category.service;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.category.domain.Category;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public interface CategoryService {
    List<Category> findAll();

    Book load(int bid);

    void add(Category category);

    void delete(String cid) throws CategoryException;

    Category editPre(String cid);

    void edit(String cname ,String cid);
}
