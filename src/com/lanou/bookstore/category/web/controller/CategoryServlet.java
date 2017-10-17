package com.lanou.bookstore.category.web.controller;

import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.category.service.AdminService;
import com.lanou.bookstore.category.service.CategoryService;
import com.lanou.bookstore.category.service.impl.AdminServiceImpl;
import com.lanou.bookstore.category.service.impl.CategoryServiceImpl;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/9/21.
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {

    private CategoryService service = new CategoryServiceImpl();
    private AdminService adminService = new AdminServiceImpl();
    public String findAll(HttpServletRequest request,HttpServletResponse response){
        List<Category> category = service.findAll();
        List<Map<String, Object>> list = adminService.volume();
        // 分类
        request.setAttribute("categorys",category);
        // 热销
        request.setAttribute("hot",list);
        return "f:/jsps/left.jsp";
    }
}