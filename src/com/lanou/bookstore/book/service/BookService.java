package com.lanou.bookstore.book.service;

import com.lanou.bookstore.book.domain.Book;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public interface BookService {
    List<Book> findAll();

    List<Book> findAll2();

    List<Book> findByCategory(int cid);

    Book load(int bid);

    List<Book> search(String s);
}
