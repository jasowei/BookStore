<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>图书详细</title>

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
    <style type="text/css">
        body {
            font-size: 10pt;
        }

        #all {
            margin: auto;
        }

        div {
            text-align: center;
        }

        li {
            margin: 10px;
        }

        a {
            background: url(<c:url value='/images/all.png'/>) no-repeat;
            display: inline-block;
            background-position: 0 -70px;
            margin-left: 30px;
            height: 36px;
            width: 146px;
        }

        a:HOVER {
            background: url(<c:url value='/images/all.png'/>) no-repeat;
            display: inline-block;
            background-position: 0 -106px;
            margin-left: 30px;
            height: 36px;
            width: 146px;
        }
    </style>
</head>

<body>
<table class="table">
    <tr class="active">
        <th>BOOK</th>
        <th>书名</th>
        <th>作者</th>
        <th>数量</th>
        <th>单价</th>
    </tr>
    <tr>
        <td><div><img src="<c:url value='${book.image}'/>" border="0"/></div></td>
        <td>${book.bname}</td>
        <td>${book.author}</td>
        <td><form id="form" action="<c:url value='/CartServlet?method=add&bid=${book.bid}'/>" method="post">
            <input type="text" size="3" name="count" value="1"/>
        </form></td>
        <td>${book.price}元</td>
    </tr>
    <tr class="active">
        <td colspan="5">
            <div id="all">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <a href="javascript:document.getElementById('form').submit();"></a>
                    </c:when>
                    <c:otherwise>
                        <c:set var="msg1" value="请先登录, 再购买!" scope="session"/>
                        <a href="/jsps/msg.jsp" target="body"></a>
                    </c:otherwise>
                </c:choose>
            </div>
        </td>
    </tr>
</table>


</body>
</html>
