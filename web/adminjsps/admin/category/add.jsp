<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>添加分类</title>

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
    <link href="../../../css/bootstrap.min.css" rel="stylesheet">
</head>


<body>
<table class="table" >
    <tr >
        <th class="active" colspan="2" style="text-align: center"><h1>添加分类</h1></th>

    </tr>
    <p style="font-weight: 900; color: red">${msg }</p>
    <form action="/AdminCategoryServlet" method="post">
        <input type="hidden" name="method" value="add">
        <tr>
            <td>分类名称：<input type="text" name="cname"/></td>
            <td><input type="submit" value="添加分类"/></td>
        </tr>

    </form>
</table>
</body>
</html>
