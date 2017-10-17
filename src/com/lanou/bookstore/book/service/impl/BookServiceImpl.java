package com.lanou.bookstore.book.service.impl;

import com.lanou.bookstore.book.dao.BookDao;
import com.lanou.bookstore.book.dao.impl.BookDaoImpl;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> findAll() {
        List<Book> books = bookDao.findAll();
        return books;
    }

    @Override
    public List<Book> findAll2() {
        List<Book> books = bookDao.findAll2();
        return books;
    }

    @Override
    public List<Book> findByCategory(int cid) {
        List<Book> books = bookDao.findByCategory(cid);
        return books;
    }

    @Override
    public Book load(int bid) {
        Book book = bookDao.load(bid);
        return book;
    }

    @Override
    public List<Book> search(String s) {
        List<Book> books = bookDao.search(s);
        return books;
    }
}
