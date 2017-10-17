<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>My JSP 'msg.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <meta http-equiv="refresh" content="3;url=/jsps/back.jsp">


    <style>
        h1{
            margin-top: 5%;
        }
        div{
            width: 480px;
            height: 150px;
            margin: auto;
            text-align: center;
            padding-top: 50px;
        }
    </style>

</head>

<body>
<div>
    <c:choose>
        <c:when test="${not empty msg}">
        <h1 style="color: red">${msg}</h1>
        </c:when>
        <c:otherwise>
        <h1 style="color: red">${sessionScope.msg1}</h1>
        </c:otherwise>
    </c:choose>
    <img src="/images/加载.gif" style="width: 100px;height: 100px;">
</div>

</body>
</html>
