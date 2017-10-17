<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>添加图书</title>

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
<div class="panel panel-default" style="text-align: center">
    <div class="panel-heading"><h1>添加图书</h1></div>
    <form action="/AdminAddBookServlet" method="post" enctype="multipart/form-data">

    <div class="panel-body" style="text-align: center">
        <p style="font-weight: 900; color: red">${msg}</p>
        图片：<input style="width: 223px; height: 20px;" type="file" name="image"/>
    </div>
    <ul class="list-group" >
        <li class="list-group-item">图书名称：<input style="width: 150px; height: 20px;" type="text" name="bname"/></li>
        <li class="list-group-item">图书单价：<input style="width: 150px; height: 20px;" type="text" name="price"/></li>
        <li class="list-group-item">图书作者：<input style="width: 150px; height: 20px;" type="text" name="author"/></li>
        <li class="list-group-item">图书分类：<select style="width: 150px; height: 20px;" name="cid">
            <c:forEach var="cate" items="${categoryList}">
                <option value="${cate.cid}">${cate.cname}</option>
            </c:forEach>
        </select></li>
        <li class="list-group-item"><input type="submit" value="添加图书"/></li>
    </ul>
    </form>
</div>
</body>
</html>
