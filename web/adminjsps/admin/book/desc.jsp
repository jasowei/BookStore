<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>My JSP 'bookdesc.jsp' starting page</title>

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
    <style type="text/css">
        body {
            font-size: 10pt;
        }

        .img {
            margin: 20px;
            width: 150px;
            height: 150px;
            text-align: center;
        }

        li {
            margin: 10px;
        }
    </style>
</head>

<body>
<table class="table">
    <tr style="text-align: center">
        <th colspan="4" >
            <div class="img">
                <img src="<c:url value='${book.image}'/>" border="0"/>
            </div>
        </th>
    </tr>

    <form style="margin:20px;" id="form" action="/AdminBookServlet" method="post">
        <tr>
            <td>图书名称：<input type="text" name="bname" value="${book.bname}"/></td>
            <td>图书单价：<input type="text" name="price" value="${book.price}"/></td>
            <td> 图书作者：<input type="text" name="author" value="${book.author}"/></td>
            <td>图书分类：<select style="width: 150px; height: 20px;" name="cid">
                <c:forEach items="${categoryList}" var="cate">
                    <option value="${cate.cid}"
                            <c:if test="${cate.cid == book.cid}">selected='selected'</c:if>
                    >${cate.cname}</option>
                </c:forEach>
            </select></td>
        </tr>
        <tr class="active">
            <td colspan="4" style="text-align: center"><input type="hidden" name="bid" value="${book.bid}">
                <input type="submit" name="method" value="del" onclick="return confirm('是否真要删除该图书？');"/>
                <input type="submit" name="method" value="edit"/></td>
        </tr>
    </form>
</table>
</body>
</html>
