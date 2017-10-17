package com.lanou.bookstore.category.web.controller;

import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.category.service.CategoryException;
import com.lanou.bookstore.category.service.CategoryService;
import com.lanou.bookstore.category.service.impl.CategoryServiceImpl;
import com.lanou.commons.CommonUtils;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
@WebServlet("/AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {

    private CategoryService service = new CategoryServiceImpl();

    /**
     * 查看所有分类
     */
    public String findAll(HttpServletRequest request,HttpServletResponse response){

        //调用service得到所有的分类List<Category>
        List<Category> all = service.findAll();
        //保存到request域，转发到/adminjsps/admin/category/list.jsp
        request.setAttribute("categorys",all);
        return "f:/adminjsps/admin/category/list.jsp";

    }
    /**
     * 添加分类
     */
    public String add(HttpServletRequest request,HttpServletResponse response){

        //封装表单数据；
        Category category = CommonUtils.toBean(request.getParameterMap(),Category.class);
        //补全：cid
        List<Category> all1 = service.findAll();
        category.setCid(String.valueOf(all1.size()+1));
        //调用service方法完成添加工作
        service.add(category);
        //调用findAll()方法
        List<Category> all = service.findAll();
        request.setAttribute("categorys",all);
        return "f:/adminjsps/admin/category/list.jsp";

    }
    /**
     * 删除分类
     */
    public String delete(HttpServletRequest request,HttpServletResponse response){
//        获取参数：cid
        String cid = request.getParameter("cid");
//        调用service方法完成删除！
        try {
            service.delete(cid);
        } catch (CategoryException e) {
//        如果出现异常，保存异常信息，转发到msg.jsp显示
            request.setAttribute("msg",e.getMessage());
            return "f:/adminjsps/msg.jsp";
        }
//        调用findAll()
        List<Category> all = service.findAll();
        //保存到request域，转发到/adminjsps/admin/category/list.jsp
        request.setAttribute("categorys",all);
        return "f:/adminjsps/admin/category/list.jsp";


    }
    /**
     * 修改分类
     * 1.加载
     * 2.修改
     */

    public String editPre(HttpServletRequest request,HttpServletResponse response){

        /*
        获取cid
        通过cid来调用service方法，得到Category对象
        保存到request域中，转发到mod.jsp
        mod.jsp：把当前的Category对象显示到表单中
         */
        String cid = request.getParameter("cid");
        Category category = service.editPre(cid);
        request.setAttribute("category",category);
        return "f:/adminjsps/admin/category/mod.jsp";

    }
    public String edit(HttpServletRequest request,HttpServletResponse response){
        /*
            封装表单数据
            调用service方法完成修改工作
            调用findAll()
         */
        String cname = request.getParameter("cname");
        String cid = request.getParameter("cid");
        service.edit(cname,cid);
        List<Category> all = service.findAll();
        //保存到request域，转发到/adminjsps/admin/category/list.jsp
        request.setAttribute("categorys",all);
        return "f:/adminjsps/admin/category/list.jsp";

    }

}