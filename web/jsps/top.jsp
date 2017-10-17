<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>top</title>

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
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        body {
            background-image: url("/images/bz1.jpg");
            color: white;
        }

        a {
            text-transform: none;
            text-decoration: none;
            color: #e5eaeb;
            tab-size: 1;
        }

        a:hover {
            text-decoration: underline;
            color: white;
        }

        #serch {
            float: right;
        }
        #lb ul li{
            list-style: none;
            text-align: center;
        }
        #lb ul li:not(:first-child){display: none;}

    </style>
</head>

<body>
<div id="lb" style="text-align: center">
    <ul>
        <li><div style="height: 56px;text-align: center;"><span style="font-size:50px; color: white ; font-family: '.SF NS Text'"> JのBookStore </span></div></li>
        <li><div><img src="/images/广告.png" style="width: 750px;height: 56px;"></div></li>
    </ul>
</div>
<div style="font-size: 10pt;">
    <c:choose>
        <c:when test="${not empty user}">
            &nbsp;&nbsp;Hi~:<span style="color: #ff9025; font-size: x-large">[  ${user.username}  ]</span>
        </c:when>
        <c:otherwise>
            <span style="font-size: x-large;color: #cdd2d3">&nbsp;&nbsp;[亲,请登录]</span>
        </c:otherwise>
    </c:choose>

    <br/>

    <c:choose>
        <c:when test="${not empty user}">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="<c:url value='/jsps/user/regist.jsp'/>" target="body">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            </a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<c:url value='/jsps/user/pre.jsp'/>" target="body">
                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
            </a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<c:url value='/jsps/cart/list.jsp'/>" target="body">
                <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
            </a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<c:url value='/OrderServlet?method=myOrders'/>" target="body">
                <span class="glyphicon glyphicon-list" aria-hidden="true"></span>
            </a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<c:url value="${pageContext.request.contextPath}/UserServlet?method=quit"/> "
               target="body">
                <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
            </a>
        </c:when>
        <c:otherwise>
            &nbsp;&nbsp;&nbsp;
            <a href="<c:url value='/jsps/user/regist.jsp'/>" target="body">
                注册
            </a>&nbsp;|&nbsp;
            <a href="<c:url value='/jsps/user/login.jsp'/>" target="body">登录</a>
        </c:otherwise>
    </c:choose>


    <form action="/BookServlet" method="post" id="serch" target="body">
        <input type="hidden" name="method" value="search">

        <input type="text" name="srh" value="   国庆节全场包邮~" style="color: #aeb3b4;
        width: 200px;height: 30px;border:3px solid #fa5521"
               onfocus="this.value=''" onblur="if(this.value==''){this.value='   国庆节全场包邮~'}">
        <input type="submit" value="🔍" style="background-color:white;">
    </form>


</div>
<script>
    //因为使用了document，所以js需要放在需要执行的代码下面生效才能生效
    var li=document.getElementById('lb').getElementsByTagName("li");
    var num=0;
    var len=li.length;

    setInterval(function(){
        li[num].style.display="none";
        num=++num==len?0:num;
        li[num].style.display="inline-block";

    },8000);//切换时间
</script>
</body>
</html>
