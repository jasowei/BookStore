<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>订单列表</title>

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
        * {
            font-size: 11pt;
        }

        div {
            border: solid 2px gray;
            width: 75px;
            height: 75px;
            text-align: center;
        }

        li {
            margin: 10px;
        }

        #buy {
            background: url(<c:url value='/images/all.png'/>) no-repeat;
            display: inline-block;

            background-position: 0 -902px;
            margin-left: 30px;
            height: 36px;
            width: 146px;
        }

        #buy:HOVER {
            background: url(<c:url value='/images/all.png'/>) no-repeat;
            display: inline-block;

            background-position: 0 -938px;
            margin-left: 30px;
            height: 36px;
            width: 146px;
        }
    </style>
</head>

<body>

<h1>[ 我的订单 ]</h1>
<c:forEach items="${orders}" var="order">
    <table class="table" width="100%" cellspacing="0" background="black">

        <tr class="active">
            <td colspan="6">
                订单编号：${order.oid}　成交时间：${order.ordertime}　
                金额：<font color="red"><b>${order.total}</b></font>　

                <c:choose>
                    <c:when test="${order.state == 0}">
                        [未付款]<a href="<c:url value='/OrderServlet?method=load&oid=${order.oid}'/>">付款</a>
                    </c:when>
                    <c:when test="${order.state == 1}">
                        [未发货]
                    </c:when>
                    <c:when test="${order.state == 2}">
                        [已发货]<a href="<c:url value='/OrderServlet?method=confirm&oid=${order.oid}'/>">确认收货</a>
                    </c:when>
                    <c:otherwise>
                        [订单已完成]
                    </c:otherwise>
                </c:choose>

            </td>
        </tr>
        <c:forEach items="${order.orderList}" var="oList">
            <tr bordercolor="gray" align="center">
                <td width="15%">
                    <div><img src="<c:url value='${oList.book.image}'/>" height="75"/></div>
                </td>
                <td>书名：${oList.book.bname}</td>
                <td>单价：${oList.book.price}</td>
                <td>作者：${oList.book.author}</td>
                <td>数量：${oList.count}</td>
                <td>小计：${oList.subtotal}元</td>
            </tr>
        </c:forEach>

    </table>
</c:forEach>

<h1 style="color: red">${msg}</h1>


</body>
</html>
