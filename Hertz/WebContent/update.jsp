<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>实名认证</title>
    <link rel="stylesheet" type="text/css" href="css/css.css">
	<script src="js/mui.js"></script>
	
	<script type="text/javascript">
	function upload(){
		window.open('<c:url value='upload.jsp' />',"","width=450,height=250")
	}
	function upload1(){
		window.open('<c:url value='upload1.jsp' />',"","width=450,height=250")
	}
	function upload2(){
		window.open('<c:url value='upload2.jsp' />',"","width=450,height=250")
	}
</script>
</head>

<body class="body">
<!--头部 开始-->
<div class="header">
	<div class="header_main">
        <a href="javascript:history.go(-1)" class="a_fh" class="a_fh">&lt返回</a>
        <h3>实名认证</h3>
    </div>
</div>
<!--头部 结束-->

<!--主体内容 开始-->
<h3 class="rz_one">
	<i><img src="img/img_15.png"></i>
    用户注册信息（必填项）
</h3>
<i>${msg}</i>
<form action="<c:url value="UserAction"></c:url>" method="post">
<input type="hidden" name="operate" value="update">
<input type="hidden" name="imgPath1" id="imgPath1">
<input type="hidden" name="imgPath2" id="imgPath2">
<ul class="rz_two">
	<li>姓名：<input type="text" class="txt" name="userName" value="${userInfo.userName }"></li>
    <li>性别：<input type="text" class="txt" name="sex" value="${userInfo.sex }"></li>
    <li>电话号码：<input type="text" class="txt" name="phone" value="${userInfo.phone }"readonly></li>
    <li>身份证号码：<input type="text" class="txt" name="idNum" value="${userInfo.idNum }"></li>
    <li>身份证正反面照片：<input type="button" class="btn_02" name="idImg" id="imgPath1" onclick="upload1();" value="点击上传"></li>
    <li>驾驶证照片：<input type="button" class="btn_02" name="driverImg" id="imgPath2" onclick="upload2();" value="点击上传"></li>
</ul>
<h3 class="rz_one">
	<i><img src="img/img_16.png"></i>
    其他信息（选填项）
</h3>
<ul class="rz_two">
	<li>居住地址：<input type="text" class="txt" name="address" value="${userInfo.address }"></li>
    <li>紧急联系人姓名：<input type="text" class="txt" name="othName" value="${userInfo.othName }"></li>
    <li>紧急联系人电话：<input type="text" class="txt" name="othPhone" value="${userInfo.othPhone }"></li>
    <li>联系人人物关系：<input type="text" class="txt" name="other" value="${userInfo.other }"></li>
</ul>
<div class="rz_three">
	<input type="submit" class="btn" value="确认">
</div>
</form>
<!--主体内容 结束-->

</body>
</html>