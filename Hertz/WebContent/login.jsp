<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="css/css.css">
    <script src="js/jQuery.js"></script>
	<script src="js/mui.js"></script>
</head>

<body class="body">
<!--头部 开始-->
<div class="header">
	<div class="header_main">
         <a href="javascript:history.go(-1)" class="a_fh" class="a_fh">&lt返回</a>
        <a href="<c:url value="regist.jsp"></c:url>" class="a_right">注册</a>
        <h3>登录</h3>
    </div>
</div>
<!--头部 结束-->

<!--主体内容 开始-->
<form action="<c:url value="/UserAction"></c:url>" method="post">

			<input type="hidden" name="operate" value="login">
<ul class="zc_one">
	<li><i> <img src="img/us.png" class="img"></i>
	<input type="text" class="txt txt_phone" placeholder="手机号" name="phone" value="${phone }"></li>
    <li><i> <img src="img/us1.png" class="img">
    </i><input type="password" class="txt" placeholder="请输入密码" name="password"
				value="${password}"></li>
</ul>
<i>${msg}</i>
<div class="zhao">
	<a href="#" class="left"> <img src="img/y.png">找回用户名</a><a href="#" class="right"> <img src="img/y1.png" >忘记密码</a>
</div>
<div class="zc_two"><input type="submit" class="btn_zc" value="登录" ></div>
</form>
<!--主体内容 结束-->

</body>
</html>