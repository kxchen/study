<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>个人中心</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="css/css.css"></c:url>">
    <script src=" <c:url value="js/jQuery.js"></c:url> "></script>
    <script src="<c:url value="js/mui.js"></c:url>"></script>
   
</head>

<body class="body">
<i>${msg}</i>
<!--头部 开始-->
<div class="header">
	<div class="header_main">
        <a href="javascript:history.go(-1)" class="a_fh" class="a_fh">&lt返回</a>
        <h3>个人中心</h3>
    </div>
</div>
<!--头部 结束-->

<!--主体内容 开始-->
<div class="gr_one">
	<img src="
	<c:if test="${!empty userInfo.headImg}">
	<c:url value="/upload/${userInfo.headImg}"></c:url>
	<br>
	</c:if>
	<c:if test="${empty userInfo.headImg }">
	img/us.png">
	<br>
	</c:if>
    ${userInfo.phone }
</div>
<div class="gr_two">
	<i><img src="img/img_14.jpg"></i>
    我的钱包
</div>
<div class="gr_three">
	<span>余额：<i>${userInfo.remBal}</i>元</span>
    <span>租车劵：<i>${userInfo.disCou }</i>元</span>
</div>
<ul class="list_five">
	<li><a href="<c:url value="UserAction?operate=listShopCar"></c:url>"><i><img src="img/img_04.png"></i>我的购物车</a></li>
    <li><a href="<c:url value="UserAction?operate=listOrder&state=yudingzhong"></c:url>"><i><img src="img/img_10.png"></i>我的订单</a></li>
    <li><a href="<c:url value="UserAction?operate=edit"></c:url>"><i><img src="img/img_11.png"></i>实名认证</a></li>
    <li><a href="http://m.weizhang8.cn/"><i><img src="img/img_12.png"></i>违章驾驶</a></li>
    <li><a href="#"><i><img src="img/img_13.png"></i>帮助中心</a></li>
</ul>
<!--主体内容 结束-->
</body>
</html>