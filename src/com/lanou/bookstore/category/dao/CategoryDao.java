package com.lanou.bookstore.category.dao;

import com.lanou.bookstore.category.domain.Category;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public interface CategoryDao {
    List<Category> findAll();

    void add(Category category);

    void delete(String cid);

    Category findByCid(String cid);

    void edit(String cname,String cid);
}
