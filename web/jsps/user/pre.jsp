<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>pre</title>

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
    <style>
        #d1 {
            background-image: url("/images/timg.gif");
            height: 500px;
            width: 900px;
        }

        .bg{
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
            width: 400px;
            text-align: center;
            position: absolute;
            left:280px;
            color: white;
        }
        .in{
            border:0;
            border-bottom:3px solid #fa5521;
            background:transparent;
        }
        .sb{
            BACKGROUND-COLOR: transparent;
            border: 0px;
            color: #fa5521;
            height: 28px;
            font-size: 14pt;
            BORDER-BOTTOM: #ffffff 1px solid;
        }
    </style>
    <Script src="../../jquery-3.2.1.js"></Script>

</head>

<body>
<div id="d1" class="bg"></div>
<div id="d2">
    <h1 style="color: #fa5521">个人中心</h1>
    <hr>
    <br>
    <p>${msg}</p>
    <form action="/UserServlet" method="post">
        <input type="hidden" name="method" value="editUser"/>
        <span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;
        <input class="in" type="text" name="username" value="${sessionScope.user.username}"/>
        <br><br>
        <span class="glyphicon glyphicon-lock"></span>&nbsp;&nbsp;
        <input class="in" type="password" name="password" value="${sessionScope.user.password}"/>
        <br><br>
        <span class="glyphicon glyphicon-envelope"></span>&nbsp;&nbsp;
        <input class="in" type="text" name="email" value="${sessionScope.user.email}"/>
        <br><br>
        <div style="font-size: small ; color: #0f0f0f">
            <span class="glyphicon glyphicon-home" style="color: white"></span>&nbsp;&nbsp;
            <select name="province" id="p">
                <option value="0">请选择</option>
            </select>
            <select name="city" id="c">
                <option value="0">请选择</option>
            </select>
            <select>
                <option value="0">请选择</option>
                <option value="">长江</option>
                <option value="">黄河</option>
            </select>
        </div>
        <br>
        <br>
        <input class="sb" type="submit" value="保存修改" />
        <br>

        <hr>
        <span style="color:#595e5f ">在这里,阅读全世界.</span>
        <br>
        <span style="color:#595e5f ">JのBookStore</span>
    </form>
</div>

</body>
<script>
    $(function () {
        $.post(
                "/CityServlet",
                {
                    name: "D",
                    body: "d"
                },
                function (data) {
                    var _html = "";
                    $.each(data, function (i, n) {
                        _html += '<option value=' + n.province + ' >' + n.province + '</option>';

                    });
                    $("#p").append(_html);
                },
                'json'
        );
        $("#p").change(function () {
            $("#c").children("option").detach();
            var indexProv = $("#p>option:selected").index();
            if (indexProv > 0) {
                $.post(
                        "/CityServlet",
                        {
                            name: "D",
                            body: "d"
                        },
                        function (data) {
                            $.each(data[indexProv - 1].city, function (i, n) {
                                $("#c").append('<option value=' + n + '>' + n + '</option>');
                            });
                        },
                        'json'
                );
            }
        });
    })

</script>
</html>
