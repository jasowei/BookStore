<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>注册</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <!--适配各种设备-->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <!--先引 JQ-->
    <script src="../../js/bootstrap.js"></script>
    <style>
        #d1 {
            background-image: url("/images/timg.gif");
            height: 500px;
            width: 900px;
            padding-top: 20px;
        }

        .bg {
            float: left;
            width: 100%;
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
            -webkit-filter: blur(5px);
            -moz-filter: blur(5px);
            -o-filter: blur(5px);
            -ms-filter: blur(5px);
            filter: blur(5px);
        }

        #d2 {
            position: absolute;
            left: 320px;
            width: 300px;
            text-align: center;
            padding-top: 10px;
            color: white;
        }
        .in{
            border:0;
            border-bottom:3px solid #fa5521;
            background:transparent;
        }
        .sb{
            BACKGROUND-COLOR: transparent;
            color: #fa5521;
            border: 0px;
            height: 28px;
            font-size: 14pt;
            BORDER-BOTTOM: #ffffff 1px solid;
        }
    </style>

</head>

<body>

<div id="d1" class="bg"></div>
<div id="d2">
    <h1>立即免费注册</h1>
    <hr><br>
    <p>${msg}</p>
    <br>
    <form action="${pageContext.request.contextPath}/UserServlet" method="post">
        <input type="hidden" name="method" value="regist"/>
        <span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;
        <input class="in" type="text" name="username" value="${form.username}"/>
        <br><br>
        <span class="glyphicon glyphicon-lock"></span>&nbsp;&nbsp;
        <input class="in" type="password" name="password" value="${form.password}"/>
        <br><br>
        <span class="glyphicon glyphicon-envelope"></span>&nbsp;&nbsp;
        <input class="in" type="text" name="email" value="${form.email}"/>
        <br><br>
        <input class="sb" type="submit" value="立即加入"/>
        <br><br>

        <hr>
        <span style="color:#595e5f ">在这里,阅读全世界.</span>
        <br>
        <span style="color:#595e5f ">JのBookStore</span>
    </form>
</div>

</body>
</html>
