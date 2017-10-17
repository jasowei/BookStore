<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>My JSP 'main.jsp' starting page</title>

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
        * {
            font-size: 10pt;
        }

        body {
            text-align: center;
            margin: 0px;
        }

        .table {
            width: 100%;
            height: 100%;
            border-collapse: collapse; /*单线的列表边框*/
        }

        .bz {
            float: left;
            width: 100%;
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
            -webkit-filter: blur(5px);
            -moz-filter: blur(5px);
            -o-filter: blur(5px);
            -ms-filter: blur(5px);
            filter: blur(5px);
        }
        .cot{
            width:100%;
            height:100%;
            position: absolute;
            top:0;

        }

        iframe {
            width: 100%;
            height: 100%;
            margin: 0;
        }
    </style>
</head>

<body>
<div class="bz">
    <img src="/images/book.jpg" style="width:100%;height:100%;">
</div>
<div class="cot">
    <table class="table" align="center">

        <tr>
            <td width="260px" align="center" valign="top">
                <iframe frameborder="0" width="250px" src="<c:url value='/adminjsps/admin/left.jsp'/>"
                        name="left"></iframe>
            </td>
            <td>
                <iframe frameborder="0" src="<c:url value='/adminjsps/admin/body.jsp'/>" name="body"></iframe>
            </td>
        </tr>

        <tr style=" height: 50px; ">
            <td colspan="2" align="center">
                <iframe frameborder="0" src="<c:url value='/adminjsps/admin/top.jsp'/>" name="top"></iframe>
            </td>
        </tr>

    </table>
</div>
</body>
</html>
