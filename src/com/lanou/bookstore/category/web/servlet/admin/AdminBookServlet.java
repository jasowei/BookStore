package com.lanou.bookstore.category.web.servlet.admin;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.category.service.AdminService;
import com.lanou.bookstore.category.service.CategoryService;
import com.lanou.bookstore.category.service.impl.AdminServiceImpl;
import com.lanou.bookstore.category.service.impl.CategoryServiceImpl;
import com.lanou.commons.CommonUtils;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Created by dllo on 17/9/22.
 */
@WebServlet("/AdminBookServlet")
public class AdminBookServlet extends BaseServlet {

    private BookService service = new BookServiceImpl();
    private CategoryService service1 = new CategoryServiceImpl();
    private AdminService service2 = new AdminServiceImpl();
    /**
     * 查看所有图书
     */
    public String findAll(HttpServletRequest request,HttpServletResponse response){
//        查询所有图书，保存到request
        List<Book> books = service.findAll();
        request.setAttribute("books",books);
//        转发到/adminjsps/admin/book/list.jsp
        return "f:/adminjsps/admin/book/list.jsp";
    }
    /**
     * 加载图书
     */
    public String load(HttpServletRequest request,HttpServletResponse response){
//        获取bid，通过bid调用BookService方法得到Book对象
        String bid = request.getParameter("bid");
        Book book = service.load(Integer.parseInt(bid));
//        保存到request中，转发到/adminjsps/admin/book/desc.jsp
        List<Category> categoryList = service1.findAll();
        request.setAttribute("categoryList",categoryList);
        request.setAttribute("book",book);
        return "f:/adminjsps/admin/book/desc.jsp";

    }
    /**
     * 加载所有分类
     */
    public String allCategory(HttpServletRequest request,HttpServletResponse response){
        List<Category> categoryList = service1.findAll();
        request.setAttribute("categoryList",categoryList);
        return "f:/adminjsps/admin/book/add.jsp";
    }
    /**
     * 删除图书
     */
    public String del(HttpServletRequest request,HttpServletResponse response){

        /*
        获取bid
        调用service方法完成删除
        返回列表，即调用findAll()
         */
        String bid = request.getParameter("bid");
        service2.del(bid);
        return findAll(request,response);
    }

    /**
     * 编辑图书
     */
    public String edit(HttpServletRequest request,HttpServletResponse response){

        /*
        封装表单数据（必须让页面保证把image传递过来）
        要求页面必须添加一个隐藏字段，把原来的图片路径传递过来！
        调用service方法完成
        return findAll()
         */
        String bid = request.getParameter("bid");
        Book book = CommonUtils.toBean(request.getParameterMap(),Book.class);
        service2.edit(book,bid);
        return findAll(request,response);
    }



}