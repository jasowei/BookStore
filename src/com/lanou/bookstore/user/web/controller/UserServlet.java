package com.lanou.bookstore.user.web.controller;

import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.user.dao.UserDao;
import com.lanou.bookstore.user.dao.impl.UserDaoImpl;
import com.lanou.bookstore.user.domain.User;
import com.lanou.bookstore.user.service.UserException;
import com.lanou.bookstore.user.service.UserService;
import com.lanou.bookstore.user.service.impl.UserServiceImpl;
import com.lanou.commons.CommonUtils;
import com.lanou.mail.Mail;
import com.lanou.mail.MailUtils;
import com.lanou.servlet.BaseServlet;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 魏加朔 on 17/9/21.
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    private UserDao userDao = new UserDaoImpl();
    /**
     * 注册
     */
    public String regist(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException {
        /*1.封装表单数据到User form对象*/
        User form = CommonUtils.toBean(request.getParameterMap(),User.class);
        /*2.补全 uid  code*/
        form.setUid(CommonUtils.uuid());
        form.setCode(CommonUtils.uuid()+CommonUtils.uuid());
        // 保存 form 用于回显
        request.setAttribute("form",form);
        /*3.输入效验*/
        String uname = request.getParameter("username");
        String pwd = request.getParameter("password");
        String email = request.getParameter("email");
        if (uname.equals("") || pwd.equals("") || email.equals("")){
            request.setAttribute("msg","*请完整填写注册信息!");
            return "f:jsps/user/regist.jsp";
        }
        if (uname.length()<3 || uname.length()>10){
            request.setAttribute("msg","*用户名长度必须在[3~10]之间");
            return "f:jsps/user/regist.jsp";
        }
        if (pwd.length()<3 || pwd.length()>10){
            request.setAttribute("msg","*密码长度必须在[3~10]之间");
            return "f:jsps/user/regist.jsp";
        }
        /*4.调用 service#regist(form)*/
        try {
            userService.regist(form);
        } catch (UserException e) {
            // 保存异常信息
            request.setAttribute("msg",e.getMessage());
            // 转发到regist.jsp
            return "f:jsps/user/regist.jsp";
        }
        /*5.发邮件*/
        System.out.println("发邮件");
        /*   1>. 得到 session   */
        Session session = MailUtils.createSession("smtp.163.com","xinrugao@163.com", "why1993521");
        /*   2>.创建邮件对象   */
        Mail mail = new Mail("xinrugao@163.com",form.getEmail(),"FROM.Jasoo00o0ooo0o0o",
                "<h2>激活账户!</h2><br>" +
                        "<a href='http://localhost:8080/UserServlet?method=active&code="+form.getCode()+"'>[点此激活]</a>");
        /*  3>.发送    */
        MailUtils.send(session,mail);
        /*6.保存成功信息到request*/
        request.setAttribute("msg","恭喜您:"+uname+",注册BookStore成功!(可前往邮箱进行激活!)");
        /*7.转发到msg.jsp*/
        return "f:jsps/msg.jsp";
    }

    /**
     * 激活账户
     */
    public String active(HttpServletRequest request, HttpServletResponse response){
        /*1.获取参数: 激活码*/
        String code = request.getParameter("code");
        /*2.使用激活码调用service#active(String code)*/
        try {
            userService.active(code);
        } catch (UserException e) {
            // 保存异常信息
            request.setAttribute("msg",e.getMessage());
            // 转发到regist.jsp
            return "f:jsps/msg.jsp";
        }
        /*3.保存激活成功信息*/
        request.setAttribute("msg","用户成功激活!欢迎加入我们!");
        /*4.转发到msg.jsp*/
        return "f:jsps/msg.jsp";
    }

    /**
     * 登录
     */
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*1.一键封装*/
        User form = CommonUtils.toBean(request.getParameterMap(),User.class);
        // 保存 form 用于回显
        request.setAttribute("form",form);
        /*2.输入校验*/
        String uname = request.getParameter("username");
        String pwd = request.getParameter("password");
        if (uname.equals("") || pwd.equals("")){
            request.setAttribute("msg","*请完整填写注册信息!");
            return "f:jsps/user/login.jsp";
        }

        /*3.调用service方法完成登录  (保存异常,保存form,转发login.jsp)*/
        try {
            userService.login(form);
        } catch (UserException e) {
            // 保存异常信息
            request.setAttribute("msg",e.getMessage());
            // 转发到regist.jsp
            return "f:jsps/user/login.jsp";
        }
        /*4.保存用户信息到session*/
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findByUsername(uname);
        request.getSession().setAttribute("user",user);
        /*5.重定向到 index.jsp*/
        System.out.println("登陆成功!");
        // 给辆车
        Cart cart = new Cart();
        if (request.getSession().getAttribute(form.getUsername()) == null) {
            cart.setCartMap(new HashMap<>());
            request.getSession().setAttribute(form.getUsername(), cart);
        }
        return "f:/jsps/back.jsp";

    }
    /**
     * 退出账户
     */
    public String quit(HttpServletRequest request, HttpServletResponse response){
        Object user = request.getSession().getAttribute("user");
        request.getSession().removeAttribute("user");
        return "f:/jsps/back.jsp";
    }
    /**
     * 个人中心
     */
    public String editUser(HttpServletRequest request, HttpServletResponse response){
        User form = CommonUtils.toBean(request.getParameterMap(),User.class);
        // 获取用户id
        User user = (User) request.getSession().getAttribute("user");
        User byUsername = userDao.findByUsername(user.getUsername());

        // 保存转发
        if (form.getUsername().equals("")||form.getPassword().equals("")||form.getEmail().equals("")){
            request.setAttribute("msg","*请填写完整信息!");
            return "f:jsps/user/pre.jsp";

        }
        // 修改
        userService.edit(byUsername.getUid(),form);
        request.setAttribute("msg","恭喜您修改成功!下次登录请使用更新的个人信息!");
        return "f:jsps/msg.jsp";
    }
}