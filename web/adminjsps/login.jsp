<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>管理员登录页面</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <style>
        #d1 {
            background-image: url("/images/timg.gif");
            height: 470px;
            width: 1227px;
            padding-top: 120px;
            margin: auto;
        }

        #d2 {
            margin: auto;
            width: 350px;
            height: 400px;
            text-align: center;
            color: white;
        }
        #login_form .in {
            border: 0;
            BACKGROUND-COLOR: transparent;
            BORDER-BOTTOM: #ffffff 1px solid;
            BORDER-LEFT: #ffffff 1px solid;
            COLOR: #ffffff;
            HEIGHT: 18px;
            font-size: 12pt
        }
        .loginbtn{
            BACKGROUND-COLOR: transparent;
            border: 1px solid #FFFFFF;
            color: white;
            height: 28px;
            font-size: 14pt;
        }
    </style>

</head>

<body>
<div id="d1">
    <div id="d2">
        <h1 style="color: white;font-size: 50px"> JのBookStore 商城管家 </h1>
        <br>
        <hr/>
        <p style="font-weight: 900; color: red">${msg }</p>
        <form id="login_form" action="<c:url value='/AdminServlet?method=login'/>" method="post">
            管理员账户：<input class="in" type="text" name="adminname" value=""/><br/>
            <br>
            密　　　码：<input class="in" type="password" name="password"/><br/>
            <br>
            <input class="loginbtn" type="submit" value="进入后台"/>
            <br>
            <hr/>
            <span style="color: #525657;font-size: 12px">[为确保您账户的安全及正常使用，未经允许, 不准进入!]</span>
        </form>
    </div>
</div>

</body>
</html>
