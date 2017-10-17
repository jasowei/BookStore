<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>main</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script src="../jquery-3.2.1.js"></script>
    <style type="text/css">
        * {
            font-size: 10pt;
        }

        body {
            text-align: center;
        }

        .table {
            width: 1024px;
            height: 100%;
            border-collapse:collapse; /*单线的列表边框*/
        }

        #jaso{
            font-size: 10pt;
            color: #0e0e0e;
            text-align: center;
            opacity: 0.5;
            position: fixed;
            bottom: 8px;
            background-color: #bcc1c2;
            width: 100%;
            height: 60px;
            margin-left: auto;
            padding-top: 20px;
        }
        iframe {
            width: 100%;
            height: 100%;
        }
        #wlcm{
            float: left;
            width: 100%;
            height: 100%;
            text-align: center;
            padding-top: 200px;
            opacity: 0.8;
            position: fixed;
            color: #212321;
            background-color: #6f7475;

        }
        p{
            font-size: 68px;
            background-color: #fff;
            color: #000000;
        }

    </style>
</head>

<body>
<div id="wlcm" style="text-align: center">
     <p>Welcome  <span style="color: #ff0032 ;font-size: 80px">${sessionScope.user.username}</span>  To JのBookStore ！</p>
</div>

<table class="table" align="center">
    <tr style="height: 120px; ">
        <td colspan="2" align="center">
            <iframe frameborder="0" src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe>
        </td>
    </tr>
    <tr>
        <td width="120" style="padding:5px;" align="center" valign="top">
            <iframe frameborder="0" width="120" src="<c:url value='/CategoryServlet?method=findAll'/>"
                    name="left"></iframe>
        </td>
        <td>
            <iframe frameborder="0" src="<c:url value='/jsps/body.jsp'/>" name="body"></iframe>
        </td>
    </tr>

        <div id="jaso">
            <span>Designed by 魏加朔 in DaLian</span>
            <br>
            <span>新浪微博 @Jasooo</span>
        </div>

</table>
</body>
<script>

    var ts = document.getElementById("wlcm");
    ts.onmousemove = function () {
        ts.style.display = "none";
    };


    var jaso = document.getElementById("jaso");
    jaso.onclick = function () {
        jaso.style.display = "none";
    }

</script>
</html>
