<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>登录</title>

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
    <!--先引 JQ-->
    <script src="../../js/bootstrap.js"></script>

    <script src="../../jquery-3.2.1.js"></script>
    <style>
        #d1 {
            background-image: url("/images/timg.gif");
            height: 500px;
            width: 900px;
        }

        .bg-blur {
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

        #d2 {

            width: 300px;
            text-align: center;
            position: absolute;
            left: 320px;
            color: white;
        }

        #d4 {
            width: 300px;
            text-align: center;
            position: absolute;
            left: 320px;
            top: 120px;
            display: none;
        }

        .in {
            border: 0;
            border-bottom: 3px solid #fa5521;
            background: transparent;
        }
        .sb{
            BACKGROUND-COLOR: transparent;
            border: 0;
            color:#fa5521;
            height: 28px;
            font-size: 14pt;
            BORDER-BOTTOM: #ffffff 1px solid;
        }
    </style>

</head>

<body>
<div id="d1" class="bg-blur"></div>
<div id="d2">

    <h1>登 录</h1>
    <hr><br>
    <p id="msg">${msg}</p>
    <br>
    <form action="${pageContext.request.contextPath}/UserServlet" method="post">
        <input type="hidden" name="method" value="login">

        <span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;
        <input class="in" id="user" type="text" name="username" value="${form.username}"/><br/>
        <br><br>
        <span class="glyphicon glyphicon-lock"></span>&nbsp;&nbsp;
        <input class="in" id="pwd" type="password" name="password" value="${form.password}"/><br/>
        <br><br>
        <input class="sb" type="submit" value="登录" />
        <br><br>
            <span id="ewm" class="glyphicon glyphicon-qrcode"></span>&nbsp;&nbsp;手机扫码登录
        <br>
        <hr>
        <span style="color:#595e5f ">在这里,阅读全世界.</span>
        <br>
        <span style="color:#595e5f ">JのBookStore</span>
    </form>
</div>

<div id="d4">
    <img src="/images/myewm.png" alt="" style="height: 200px;width: 200px;">
</div>


</body>
<script>
    var ewm = document.getElementById("ewm");
    var div = document.getElementById("d4");
    ewm.onclick = function () {
        div.style.display = "block";
    };
    div.onclick = function () {
        div.style.display = "none";
    };
</script>
<%--<script>--%>
<%--//创建异步对象--%>
<%--function createXMLHttpRequest() {--%>
<%--try {--%>
<%--return new XMLHttpRequest();//大多数浏览器--%>
<%--} catch (e) {--%>
<%--try {--%>
<%--return ActvieXObject("Msxml2.XMLHTTP");//IE6.0--%>
<%--} catch (e) {--%>
<%--try {--%>
<%--return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本--%>
<%--} catch (e) {--%>
<%--alert("哥们儿，您用的是什么浏览器啊？");--%>
<%--throw e;--%>
<%--}--%>
<%--}--%>
<%--}--%>
<%--}--%>

<%--window.onload = function() {--%>
<%--var userElm = document.getElementById("user");--%>
<%--var pwdElm = document.getElementById("pwd");--%>
<%--userElm.onblur= function () {--%>
<%--var xmlHttp = createXMLHttpRequest();--%>
<%--xmlHttp.open("post","/LoginServlet",true);--%>
<%--xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");--%>
<%--//.发送请求，给出请求体--%>
<%--xmlHttp.send("username=" + userElm.value);--%>
<%--xmlHttp.send("password=" + pwdElm.value);--%>
<%--//.给xmlHttp的onreadystatechange事件注册监听--%>
<%--xmlHttp.onreadystatechange = function() {--%>
<%--if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {//双重判断--%>
<%--var text = xmlHttp.responseText;--%>
<%--alert(text);--%>
<%--var msg = document.getElementById("msg");--%>
<%--if(text == null) {--%>
<%--$("form").action="${pageContext.request.contextPath}/UserServlet";--%>
<%--} else {--%>
<%--msg.innerHTML = text;--%>
<%--msg.style.color = "green";--%>
<%--}--%>
<%--}--%>
<%--};--%>

<%--}--%>

<%--}--%>
<%--</script>--%>
</html>
