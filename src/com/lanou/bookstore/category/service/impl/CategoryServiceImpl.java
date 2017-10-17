package com.lanou.bookstore.category.service.impl;

import com.lanou.bookstore.book.dao.BookDao;
import com.lanou.bookstore.book.dao.impl.BookDaoImpl;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.category.dao.CategoryDao;
import com.lanou.bookstore.category.dao.impl.CategoryDaoImpl;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.category.service.CategoryException;
import com.lanou.bookstore.category.service.CategoryService;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public List<Category> findAll() {
        List<Category> categories = categoryDao.findAll();
        return categories;
    }

    @Override
    public Book load(int bid) {
        Book book = bookDao.load(bid);
        return book;
    }

    @Override
    public void add(Category category) {
        categoryDao.add(category);
    }

    @Override
    public void delete(String cid) throws CategoryException {
//        通过cid查看该分类下的图书本数，如果大于0，抛出异常；
        List<Book> books = bookDao.findByCategory(Integer.parseInt(cid));
        if (books.size()>0){
            throw new CategoryException("*不可删除此图书类!");
        }
//        如果等于0，删除该分类；
        if (books.size()==0){
            categoryDao.delete(cid);
        }
    }

    @Override
    public Category editPre(String cid) {
        Category category = categoryDao.findByCid(cid);
        return category;
    }

    @Override
    public void edit(String cname,String cid) {
        categoryDao.edit(cname,cid);
    }
}
