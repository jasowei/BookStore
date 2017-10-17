<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>订单详细</title>

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
    <script src="../../jquery-3.2.1.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?key=zLwuzInmR1DTrNFGQqBIxQWIQ2EkMikD&callback=renderReverse&v=1.1&services=true"></script>

    <style type="text/css">
        * {
            font-size: 11pt;
        }

        div {
            border: solid 2px gray;
            width: 75px;
            height: 75px;
            text-align: center;
        }

        li {
            margin: 10px;
        }

        #pay {
            background: url(<c:url value='/images/all.png'/>) no-repeat;
            display: inline-block;

            background-position: 0 -412px;
            margin-left: 30px;
            height: 36px;
            width: 146px;
        }

        #pay:HOVER {
            background: url(<c:url value='/images/all.png'/>) no-repeat;
            display: inline-block;

            background-position: 0 -448px;
            margin-left: 30px;
            height: 36px;
            width: 146px;
        }
    </style>
</head>

<body>
<h1>当前订单</h1>


<table class="table" width="100%" cellspacing="0" background="black">
    <tr class="active" bgcolor="gray" bordercolor="gray">
        <td colspan="6">
            订单编号：${orders.oid}　成交时间：${orders.ordertime}　金额：<font color="red"><b>${orders.total}</b></font>
        </td>
    </tr>
    <c:forEach items="${orders.orderList}" var="order">
        <tr bordercolor="gray" align="center">
            <td width="15%">
                <div><img src="<c:url value='${order.book.image}'/>" height="75"/></div>
            </td>
            <td>书名：${order.book.bname}</td>
            <td>单价：${order.book.price}</td>
            <td>作者：${order.book.author}</td>
            <td>数量：${order.count}</td>
            <td>小计：${order.subtotal}</td>
        </tr>

    </c:forEach>
</table>
<br/>
<form class="form-horizontal" method="post" action="/OrderServlet?method=pay&oid=${orders.oid}" id="form" target="body">
    收货地址：<span id='location' class='glyphicon glyphicon-screenshot' aria-hidden='true'></span>
    <input id="baidu_geo" class="form-control" type="text" name="address" size="50" value="辽宁省大连市" />
    <span
        style="color: red">${msg}
    </span><br/>
    <iframe src="<c:url value='/jsps/order/ditu.jsp'/>" width="600" height="300" frameborder="0" scrolling="no"></iframe>


    </div>
    <br>
    手机号:&nbsp;&nbsp;
    <input class="form-control" type="text" name="phone"><span style="color: red">${msg2}</span><br/>


    选择银行：<br/>
    <input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>工商银行
    <img src="../../bank_img/icbc.bmp" align="middle"/>

    <input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行
    <img src="../../bank_img/bc.bmp" align="middle"/><br/><br/>

    <input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行
    <img src="../../bank_img/abc.bmp" align="middle"/>

    <input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行
    <img src="../../bank_img/ccb.bmp" align="middle"/><br/><br/>

    <input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>交通银行
    <img src="../../bank_img/bcc.bmp" align="middle"/><br/>

    <br/>
</form>
<a id="pay" href="javascript:document.getElementById('form').submit();"></a>

</body>
<script>
    function createXMLHttpRequest() {
        try {
            return new XMLHttpRequest();//大多数浏览器
        } catch (e) {
            try {
                return ActvieXObject("Msxml2.XMLHTTP");//IE6.0
            } catch (e) {
                try {
                    return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本
                } catch (e) {
                    alert("哥们儿，您用的是什么浏览器啊？");
                    throw e;
                }
            }
        }
    }
    window.onload = getLocation();
    function getLocation(){
        if (navigator.geolocation){
            navigator.geolocation.getCurrentPosition(showPosition,showError);
        }else{
            alert("浏览器不支持地理定位。");
        }
    }

    function showError(error){
        switch(error.code) {
            case error.PERMISSION_DENIED:
                alert("定位失败,用户拒绝请求地理定位");
                break;
            case error.POSITION_UNAVAILABLE:
                alert("!!!!!!!!!!!!!!!!定位失败!!!!!!!!!!!!!!!!");
                break;
            case error.TIMEOUT:
                alert("定位失败,请求获取用户位置超时");
                break;
            case error.UNKNOWN_ERROR:
                alert("定位失败,定位系统失效");
                break;
        }
    }

//    function showPosition(position){
//        var lat = position.coords.latitude; //纬度
//        var lag = position.coords.longitude; //经度
//        alert('纬度:'+lat+',经度:'+lag);
//    }

    function showPosition(position){
        var latlon = position.coords.latitude+','+position.coords.longitude;
        var XMLHttpRequest = createXMLHttpRequest();

        //baidu
        var url = "<a href='http://api.map.baidu.com/geocoder/v2/?ak=zLwuzInmR1DTrNFGQqBIxQWIQ2EkMikD&callback=renderReverse&location="+latlon+"&output=json&pois=0'>http://api.map.baidu.com/geocoder/v2/?ak=C93b5178d7a8ebdb830b9b557abce78b&callback=renderReverse&location="+latlon+"&output=json&pois=0</a>";
                $.ajax({
                    type: "GET",
                    dataType: "jsonp",
                    url: url,
                    beforeSend: function(){
                        $("#baidu_geo").html('正在定位...');
                    },
                    success: function (json) {
                        if(json.status==0){
                            $("#baidu_geo").html(json.result.formatted_address);
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        $("#baidu_geo").html(latlon+"地址位置获取失败");
                    }
                });
    };
</script>
</html>

