<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>欢迎来到滁租车</title>
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link rel="stylesheet" type="text/css" href="css/css.css">
<!--    <script src="js/jQuery.js"></script>
	<script src="js/yinDao.js"></script>-->
	
	<script type="text/javascript">
		var i=3;
		var intervaid;
		intervaid =setInterval("fun()", 1000);
		function fun(){
			if(i==0){
				window.location.href="<c:url value="UserAction?operate=listCar&index=index"></c:url>"
				clearInterval(intervalid);
			}
			document.getElementById("mes").innerHTML=i;
			i--
		}
	
	</script>
</head>
<body>
	
	<!--主体内容 开始-->
	<div class="yd_main">
		<div class="yd_one">
			<a href="<c:url value="UserAction?operate=listCar&index=index"></c:url>"><span id="mes">3</span>s 跳过</a> 
			<img src="img/img_01.png">
		</div>
	</div>
	<!--主体内容 结束-->

</body>
</html>