<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>购物车列表</title>

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
    <%--<script>--%>

        <%--window.onload = function () {--%>
            <%--var topInp = document.getElementById("theadInp");--%>
            <%--var tbody = document.getElementById("tbody");--%>
            <%--var botInpArr = tbody.getElementsByTagName("input");--%>
            <%--//绑定事件--%>
            <%--topInp.onclick = function () {--%>
                <%--//优化版（被点击的input按钮的checked属性值，应该直接作为下面的所有的input按钮的checked属性值）--%>
                <%--for (var i = 0; i < botInpArr.length; i++) {--%>
                    <%--botInpArr[i].checked = this.checked;--%>
                <%--}--%>
            <%--}--%>

            <%--for (var i = 0; i < botInpArr.length; i++) {--%>
                <%--botInpArr[i].onclick = function () {--%>
                    <%--//定义一个标识是true还是false的变量--%>
                    <%--//默认它为true--%>
                    <%--var bool = true;--%>
                    <%--//检测每一个input的checked属性值。--%>
                    <%--for (var j = 0; j < botInpArr.length; j++) {--%>
                        <%--if (botInpArr[j].checked === false) {--%>
                            <%--bool = false;--%>
                        <%--}--%>
                    <%--}--%>
                    <%--topInp.checked = bool;--%>
                <%--}--%>
            <%--}--%>
        <%--}--%>

    <%--</script>--%>
</head>

<body>
<c:choose>
    <c:when test="${empty sessionScope.cart.cartMap}">
        <div style="width:100%;height:100%; background-color:#f6f6f6"; align="center">
            <img src="/images/gwc.png" >
        </div>

    </c:when>
    <c:otherwise>
        <h1>[ 我的购物车 ]</h1>
        <table class="table"  width="100%" cellspacing="0" background="black">
            <tr>
                <td class="active" colspan="8" align="right" style="font-size: 15pt; font-weight: 900">
                    <a href="/CartServlet?method=clear">清空购物车</a>
                </td>
            </tr>
            <tr class="active">
                    <%--<th><input type="checkbox" id="theadInp"/>全选</th>--%>
                <th>图片</th>
                <th>书名</th>
                <th>作者</th>
                <th>单价</th>
                <th>数量</th>
                <th>小计</th>
                <th>操作</th>
            </tr>
            <tbody id="tbody">
            <c:forEach items="${sessionScope.cart.cartMap}" var="cart">

                <tr>
                        <%--<td><input type="checkbox"/></td>--%>
                    <td>
                        <div><img src="<c:url value='${cart.value.book.image}'/>"/></div>
                    </td>
                    <td>${cart.value.book.bname}</td>
                    <td>${cart.value.book.author}</td>
                    <td>${cart.value.book.price}</td>
                    <td>${cart.value.count}</td>
                    <td>${cart.value.book.price * cart.value.count}</td>
                    <td><a href="/CartServlet?method=delete&bid=${cart.value.book.bid}">删除</a></td>
                </tr>

            </c:forEach>
            </tbody>
            <tr class="active">
                <td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
                    <c:set var="sum" value="0"/>
                    <c:forEach var="cart" items="${sessionScope.cart.cartMap}">
                        <c:set var="sum" value="${sum + cart.value.book.price * cart.value.count}"/>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${sum >=300}">
                            合计：${sum}元  (免运费)
                        </c:when>
                        <c:otherwise>
                            合计：${sum+20}元  (含运费20元)
                        </c:otherwise>
                    </c:choose>

                </td>
            </tr>
            <tr class="active">
                <td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
                    <a id="buy" href="<c:url value='/OrderServlet?method=add&total=${sum}'/>"></a>
                </td>
            </tr>
        </table>
    </c:otherwise>
</c:choose>


</body>
</html>
