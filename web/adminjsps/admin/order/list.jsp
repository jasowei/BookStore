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
    <link href="../../../css/bootstrap.min.css" rel="stylesheet">

</head>

<body>
<h1>我的订单</h1>
<c:forEach items="${map}" var="orders">

    <table class="table">
        <tr class="danger">
            <td colspan="6">
                用户ID:${orders.key}
            </td>
        </tr>
    </table>

    <c:forEach items="${orders.value}" var="order">

        <table class="table table-hover">

            <tr class="active" >
                <td colspan="6">
                    订单：${order.oid}　成交时间：${order.ordertime}　金额：
                    <font color="red"><b>${order.total}</b></font>
                    <c:choose>
                        <c:when test="${order.state == 0}">
                            [未付款]
                        </c:when>
                        <c:when test="${order.state == 1}">
                            [未发货]<a href="<c:url value='/AdminOrderServlet?method=sendOrder&oid=${order.oid}'/>">发货</a>
                        </c:when>
                        <c:when test="${order.state == 2}">
                            [已发货]
                        </c:when>
                        <c:otherwise>
                            [订单已完成]
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <c:forEach items="${order.orderList}" var="ol">
                <tr align="center">
                    <td width="15%">
                        <div><img src="<c:url value='${ol.book.image}'/>" height="75"/></div>
                    </td>
                    <td>书名：${ol.book.bname}</td>
                    <td>单价：${ol.book.price}</td>
                    <td>作者：${ol.book.author}</td>
                    <td>数量：${ol.count}</td>
                    <td>小计：${ol.subtotal}元</td>
                </tr>
            </c:forEach>

        </table>

    </c:forEach>
    <br>
    <hr>
    <br>
    <br>
</c:forEach>

</body>
</html>
