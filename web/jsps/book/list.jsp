<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>图书列表</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <style type="text/css">
        body {
            font-size: 10pt;
        }

        .icon {
            margin: 10px;
            border: solid 2px gray;
            width: 160px;
            height: 180px;
            text-align: center;
            float: left;
        }
        #hot{
            position: relative;
            left: -70px;
            top: -190px;

        }
    </style>
</head>

<body>

<c:forEach items="${books}" var="book">
    <c:forEach items="${book}" var="b">
        <div class="icon">
            <a href="<c:url value='/BookServlet?method=load&bid=${b.key.bid}'/>">
                <img src="<c:url value='${b.key.image}'/>" border="0"/>
            </a>
            <br/>
            <a href="<c:url value='/BookServlet?method=load&bid=${b.key.bid}'/>">${b.key.bname}</a>
            <c:choose>
                <c:when test="${b.value > 2}">
                    <div id="hot"><img src="/images/hot.png" style="width:50px; height: 50px;"></div>
                </c:when>
            </c:choose>
        </div>

    </c:forEach>
</c:forEach>


</body>

</html>

