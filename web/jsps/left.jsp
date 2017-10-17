<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>left</title>
    <base target="body"/>
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
    <!--先引 JQ-->
    <script src="../js/bootstrap.js"></script>
    <style type="text/css">
        * {
            text-align: center;
        }

        a {
            text-decoration: none;
            color: #3c4142;
        }

        a:hover {
            text-decoration: underline;
            color: red;
        }

    </style>
</head>

<body>
<ul class="nav nav-pills nav-stacked">
    <li role="presentation" class="active">
        <a href="<c:url value='/BookServlet?method=findAll'/>">[ 全部分类 ]</a>
    </li>

    <c:forEach items="${categorys}" var="cate">
        <li role="presentation">
            <a class="btn-group"
               href="<c:url value='/BookServlet?method=findByCategory&cid=${cate.cid}'/>">${cate.cname}>></a>
        </li>
    </c:forEach>
</ul>
<br>
<h4 style="color: red">🔥 热销书单</h4>
<div style="overflow: hidden;text-overflow:ellipsis;width: 100px;height: 145px;background-color:white;">
    <c:forEach var="map" items="${hot}">
        <a href="/BookServlet?method=load&bid=${map.bid}" style="color: #ff4e04">《${map.bname}》</a>
    </c:forEach>
</div>
</body>
</html>
