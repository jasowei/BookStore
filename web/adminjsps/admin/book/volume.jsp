<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>分类列表</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <!--适配各种设备-->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>
<h2 style="text-align: center;">销量排行</h2>
<table class="table">
    <tr class="active">
        <th style="text-align: center">书名</th>
        <th style="text-align: center">销量</th>
    </tr>

    <c:forEach items="${list}" var="m">
        <tr>
            <td style="text-align: center">${m['bname']}</td>
            <td style="text-align: center">${m.c}</td>
        </tr>
    </c:forEach>


</table>
</body>
</html>
