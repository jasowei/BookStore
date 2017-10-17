package com.lanou.bookstore.book.web.controller;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.category.service.AdminService;
import com.lanou.bookstore.category.service.impl.AdminServiceImpl;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/9/21.
 */
@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet {

    private BookService service = new BookServiceImpl();
    private AdminService service2 = new AdminServiceImpl();
    /**
     * 查看所有类图书
     */
    public String findAll(HttpServletRequest request,HttpServletResponse response){
        /*1.调用service得到结果,保存到request(List<Book>)*/
        List<Book> books = service.findAll();
        List<Map<Book,Integer>> list = new ArrayList<>();
        for (Book book : books) {
            Map<Book,Integer> map = new HashMap<>();
            int cnt = service2.queryCountByBid(book.getBid());
            map.put(book,cnt);
            list.add(map);
        }
        request.setAttribute("books",list);
        /*2.返回/jsps/book/list.jsp*/
        return "f:/jsps/book/list.jsp";
    }
    /**
     * 分类查询
     */
    public String findByCategory(HttpServletRequest request,HttpServletResponse response){
        int cid = Integer.parseInt(request.getParameter("cid"));
        /*1.调用service得到结果,保存到request(List<Book>)*/
        List<Book> books = service.findByCategory(cid);
        List<Map<Book,Integer>> list = new ArrayList<>();
        for (Book book : books) {
            Map<Book,Integer> map = new HashMap<>();
            int cnt = service2.queryCountByBid(book.getBid());
            map.put(book,cnt);
            list.add(map);
        }
        request.setAttribute("books",list);
        /*2.返回/jsps/book/list.jsp*/
        return "f:/jsps/book/list.jsp";
    }
    /**
     *查询详细信息
     */
    public String load(HttpServletRequest request,HttpServletResponse response){
        /*1.获取bid*/
        int bid = Integer.parseInt(request.getParameter("bid"));
        /*2.service得到book对象,保存到request*/
        Book book = service.load(bid);
        request.setAttribute("book",book);
        /*3.转发*/
        return "f:/jsps/book/desc.jsp";

    }
    /**
     * 搜索
     */
    public String search(HttpServletRequest request,HttpServletResponse response){
        String ss = request.getParameter("srh");
        String s = "%"+ss+"%";
        List<Book> books = service.search(s);
        List<Map<Book,Integer>> list = new ArrayList<>();
        for (Book book : books) {
            Map<Book,Integer> map = new HashMap<>();
            int cnt = service2.queryCountByBid(book.getBid());
            map.put(book,cnt);
            list.add(map);
        }
        if (books.size() == 0){
            request.setAttribute("msg","{很抱歉,没有找到你想要的,试试搜索其他内容吧.}");
            return "f:jsps/msg.jsp";
        }
        request.setAttribute("books",list);
        /*2.返回/jsps/book/list.jsp*/
        return "f:/jsps/book/list.jsp";
    }

}