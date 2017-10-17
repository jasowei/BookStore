package com.lanou.bookstore.user.web.controller;

import com.lanou.bookstore.user.dao.UserDao;
import com.lanou.bookstore.user.dao.impl.UserDaoImpl;
import com.lanou.bookstore.user.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dllo on 17/9/28.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String uname = request.getParameter("username");
        String pwd = request.getParameter("password");
        System.out.println(uname+":"+pwd);
        response.getWriter().print("*用户名或密码不能为空!");

        if (uname.equals("") || pwd.equals("")){
            response.getWriter().print("*用户名或密码不能为空!");
        }
        User user = userDao.findByUsername(uname);
        if (user==null){
            response.getWriter().print("*用户不存在!");
        }
        if (!user.getPassword().equals(pwd)){
            response.getWriter().print("*密码错误!");
        }
        if (!user.isState()){
            response.getWriter().print("*用户名未激活!");
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
    }
}