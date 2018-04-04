<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" type="text/css"
	href='<c:url value='/css/jquery-ui.css' />' />
<style type="text/css">
a {
	color: #007bc4 /*#424242*/;
	text-decoration: none;
}

a:hover {
	text-decoration: underline
}

ol, ul {
	list-style: none
}

table {
	border-collapse: collapse;
	border-spacing: 0
}

body {
	height: 100%;
	font: 12px/18px Tahoma, Helvetica, Arial, Verdana, "\5b8b\4f53",
		sans-serif;
	color: #51555C;
}

img {
	border: none
}

.demo {
	width: 500px;
	margin: 20px auto
}

.demo h4 {
	height: 32px;
	line-height: 32px;
	font-size: 14px
}

.demo h4 span {
	font-weight: 500;
	font-size: 12px
}

.demo p {
	line-height: 28px;
}

input {
	width: 108px;
	height: 20px;
	line-height: 20px;
	padding: 2px;
	border: 1px solid #d3d3d3
}
</style>

<script src="js/jQuery.js"></script>
<script type="text/javascript"
	src='<c:url value='/js/jquery-ui-datepicker.js' />'>
</script>
<script type="text/javascript">
$(function() {
	$("#date_1").datepicker();
})
</script>
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
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>发布</title>
    <link rel="stylesheet" type="text/css" href="css/css.css">
	<script src="js/mui.js"></script>
</head>

<body class="body">
<!--头部 开始-->
<div class="header">
	<div class="header_main">
         <a href="javascript:history.go(-1)" class="a_fh" class="a_fh">&lt返回</a>
        <h3>发布</h3>
    </div>
</div>
<!--头部 结束-->

<!--主体内容 开始-->

<i>${msg}</i>
<form action="<c:url value="AdminAction"></c:url>" method="post">
<input type="hidden" name="operate" value="saveCar">
<input type="hidden" name="method" value="saveCombo">
<input type="hidden" name="imgPath1" id="imgPath1">
<input type="hidden" name="imgPath2" id="imgPath2">
<h3 class="rz_one">
	<i><img src="img/img_20.png"></i>
    套餐信息
</h3>
<ul class="rz_two rz_four">
    <li><p><img src="img/img_21.png">品牌：</p><input type="text" class="txt" name="brand"></li>
    <li><p><img src="img/img_22.png">车系：</p><input type="text" class="txt" name="model"></li>
    <li><p><img src="img/img_23.png">车型：</p><input type="text" class="txt" name="type"></li>
    <li><p><img src="img/img_24.png">购买时间：</p><input type="text" class="txt" name="buyDate" id="date_1" readonly></li>
    <li><p><img src="img/img_25.png">公里数：</p><input type="text" name="km" class="txt"></li>
    <li><p><img src="img/img_26.png">年审凭证照片：</p><input type="button" class="btn_01" value="添加照片" onclick="upload1();"></li>
    <li><p><img src="img/img_27.png">车辆照片：</p><input type="button" class="btn_02" value="添加照片" onclick="upload2();"></li>
    <li class="li">注：正前脸、车前45度角整体。中控、内饰细节、座椅、后备箱！</li>
    <li><p><img src="img/img_28.png">排档：</p><input type="text" class="txt"name="gears"></li>
    <li><p><img src="img/img_29.png">排量：</p><input type="text" class="txt" name="cc"></li>
    <li><p><img src="img/img_30.png">颜色：</p><input type="text" class="txt" name="color"></li>
     <li><p><img src="img/img_30.png">座位数：</p><input type="text" class="txt" name="seats"></li>
      <li><p><img src="img/img_30.png">车辆用途：<select name="purpose" title="操作系统"><option value="">==请选择==</option>
      											<option value="婚庆用车" >婚庆用车</option>
												<option value="商务用车" >商务用车</option>
												<option value="旅行用车" >旅行用车</option>
												<option value="自驾短租" >自驾短租</option>
												<option value="自驾长租" >自驾长租</option>
												<option value="其他" >其他</option>
												</select></p></li>
	<li><p><img src="img/img_30.png">套餐名：</p><input type="text" class="txt" name="comboName"></li>
    <li><p><img src="img/img_30.png">数量：</p><input type="text" class="txt" name="count"></li>
    <li><p><img src="img/img_31.png">租车条件：</p><input type="text" class="txt" name="criteria"></li>
    <li><p><img src="img/img_32.png">出租价格：</p><input type="text" class="txt" name="price"></li>
</ul>
<div class="rz_five"><label class="rad1"><input type="checkbox" name="checkbox" checked="" />我已阅读并同意《滁租车租车协议》
</label></div>
<div class="rz_three">
	<input type="submit" class="btn" value="确认发布">
</div>
</form>
<!--主体内容 结束-->

</body>
</html>