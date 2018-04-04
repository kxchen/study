<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>注册</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<script src="js/jQuery.js"></script>
<script src="js/mui.js"></script>
<script type="text/javascript">
	function yanzheng() {
		var code = document.forms[0].code.value;
		var phone= document.forms[0].phone.value;
		var password= document.forms[0].password.value;
		var password1= document.forms[0].password1.value;
		document.forms[1].phone.value=phone;
		password= document.forms[1].password.value=password;
		password1= document.forms[1].password1.value=password1;
		document.forms[1].code.value =code;
		document.forms[1].submit();
	}
</script>
</head>

<body class="body">
	<!--头部 开始-->
	<div class="header">
		<div class="header_main">
			 <a href="javascript:history.go(-1)" class="a_fh" class="a_fh">&lt返回</a>
			<a class="a_right"
				href="<c:url value="login.jsp"></c:url>">登录</a>
			<h3>注册</h3>
		</div>
	</div>
	<!--头部 结束-->
<i>${msg}</i>
	<!--主体内容 开始-->
	<form action="<c:url value="/UserAction"></c:url>" method="post">
		<input type="hidden" name="operate" value="regist">
		<ul class="zc_one">
			<li><i> <img src="img/us.png" class="img"></i> <input
				type="text" class="txt txt_phone" placeholder="手机号码" name="phone"
				value="${phone }"></li>
			<li><i> <img src="img/us1.png" class="img"></i> <input
				type="password" class="txt" placeholder="请输入密码" name="password"
				value="${password}"></li>
			<li><i> <img src="img/us1.png" class="img"></i> <input
				type="password" class="txt" placeholder="重复密码" name="password1"
				value="${password1}"></li>
			<li><i> <img src="img/us2.png" class="img"></i> 
			<input type="text" class="txt txt1" placeholder="验证码" name="code">
			<input type="submit" class="btn_yzm" value="获取验证码"></li>
		</ul>
	</form>

	<i>${msg}</i>

	<div class="xie">
		<label class="rad1"><input type="checkbox" name="checkbox"
			checked="" />我已阅读并同意《滁租车租车协议》</label>
	</div>
	<form action="<c:url value="/UserAction"></c:url>" method="post">
		<input type="hidden" name="phone" value="${phone }"> <input
			type="hidden" name="operate" value="activate">
			<input type="hidden" name="password" value="${password}">
			<input type="hidden" name="password1" value="${password1}">
			<input type="hidden" name="code" value="code">
		<div class="zc_two">
			<input type="button" class="btn_zc" value="注册" onclick="yanzheng()">
		</div>
	</form>
	<!--主体内容 结束-->

</body>
</html>