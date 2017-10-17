package com.lanou.bookstore.category.web.controller;

import com.lanou.bookstore.category.domain.Admin;
import com.lanou.bookstore.category.service.AdminService;
import com.lanou.servlet.BaseServlet;
import com.lanou.bookstore.category.service.impl.AdminServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dllo on 17/9/22.
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends BaseServlet {
    private AdminService adminService = new AdminServiceImpl();
    /**
     * 登录
     */
    public String login(HttpServletRequest request, HttpServletResponse response){
        String adminname = request.getParameter("adminname");
        String password = request.getParameter("password");
        if (adminname.equals("") || password.equals("")){
            request.setAttribute("msg","*请完整填写信息!");
            return "f:/adminjsps/login.jsp";
        }
        // 验证用户
        Admin admin = adminService.login(adminname, password);
        if (admin == null){
            request.setAttribute("msg","*用户名或密码错误!");
            return "f:/adminjsps/login.jsp";
        }
        request.getSession().setAttribute("msgname",adminname);
        return "f:/adminjsps/admin/index.jsp";

    }
}