<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>首页</title>

 <link rel="stylesheet" type="text/css" href="css/css.css">
  	<link href="css/mui.main.css" rel="stylesheet" type="text/css" />
 	<link href="css/mui.css" rel="stylesheet" type="text/css" />
	<script src="js/jQuery.js"></script>
	<script src="js/mui.main.js"></script>
	<script src="js/mui.js"></script>
</head>
<i>${msg}</i>
<body class="body">
	<!--头部 开始-->
	<div class="header">
		<div class="header_main">
			<a href="#" class="a_fh"><img src="img/img_02.jpg"></a>
			<c:if test="${empty sessionScope.userInfo }">
				<a href="<c:url value="login.jsp"></c:url>" class="a_right">登录</a>
			</c:if>
			<h3>
				<img src="img/logo.png">
			</h3>
		</div>
		<div class="mui-dropdown">
    </div>
	</div>
	<!--头部 结束-->

	<!--主体内容 开始-->
	<div id="slider1" class="mui-slider">
		<div class="mui-slider-group mui-slider-loop">
			<!-- 额外增加的一个节点(这里是最后一张轮播图片) -->
			<div class="mui-slider-item mui-slider-item-duplicate">
				<a href="#"> <img src="img/gg.png" class="img">
				</a>
			</div>
			<!-- 第一张 -->
			<div class="mui-slider-item">
				<a href="#"> <img src="img/gg.png" class="img">
				</a>
			</div>
			<!-- 第二张 -->
			<div class="mui-slider-item">
				<a href="#"> <img src="img/gg.png" class="img">
				</a>
			</div>
			<!-- 第三张 -->
			<div class="mui-slider-item">
				<a href="#"> <img src="img/gg.png" class="img">
				</a>
			</div>
			<!-- 第四张 -->
			<div class="mui-slider-item">
				<a href="#"> <img src="img/gg.png" class="img">
				</a>
			</div>
			<!-- 额外增加的一个节点(循环轮播：最后一个节点是第一张轮播) -->
			<div class="mui-slider-item mui-slider-item-duplicate">
				<a href="#"> <img src="img/gg.png">
				</a>
			</div>
		</div>
		<div class="mui-slider-indicator">
			<div class="mui-indicator mui-active"></div>
			<div class="mui-indicator"></div>
			<div class="mui-indicator"></div>
			<div class="mui-indicator"></div>
		</div>
	</div>
	<script type="text/javascript" charset="utf-8">
		var slider = mui("#slider1");
		slider.slider1({
			interval : 5000
		});
	</script>
	<ul class="sy_one">
		<li><a href="<c:url value="UserAction?operate=listCar&purpose=婚庆用车"></c:url>"><img src="img/tb1.png"></a></li>
		<li><a href="<c:url value="UserAction?operate=listCar&purpose=商务用车"></c:url>"><img src="img/tb2.png"></a></li>
		<li><a href="<c:url value="UserAction?operate=listCar&purpose=旅行用车"></c:url>"><img src="img/tb3.png"></a></li>
		<li><a href="<c:url value="UserAction?operate=listCar&purpose=自驾短租"></c:url>"><img src="img/tb4.png"></a></li>
		<li><a href="<c:url value="UserAction?operate=listCar&purpose=自驾长租"></c:url>"><img src="img/tb5.png"></a></li>
		<li><a href="<c:url value="UserAction?operate=listCar&purpose=其他"></c:url>"><img src="img/tb6.png"></a></li>
	</ul>
	<div class="sy_two">
		<a href="#">促销促销促销促销促销促销促销促销</a>
	</div>
	<div class="sy_three">
		<i>热门车型</i> <a href="<c:url value="UserAction?operate=listCar&hot=hot"></c:url>">更多>></a>
	</div>
	<ul class="sy_four">
	<c:forEach items="${ carInfoList}" var="carInfo" begin="1" end="2">
		<li><a href='<c:url value="UserAction?operate=showCar&carId=${carInfo.carId}"></c:url>'>
				<div class="img">
					<img src="
					<c:if test="${!empty carInfo.carImg }">
					<c:url value="/upload/${carInfo.carImg}"></c:url>
					</c:if>
					<c:if test="${empty carInfo.carImg }">
						img/01.jpg
					</c:if>
					">
				</div>
				<div class="left">
				<i>${carInfo.comboName}</i><br>
					<i>${carInfo.brand}${carInfo.model}</i><br>乘坐${carInfo.seats} <br> ${carInfo.type}/${carInfo.cc}${carInfo.gears}<br> 颜色：${carInfo.color}
				</div>
				<div class="right">
					<span>${carInfo.price}</span><i>元/天</i><br> 目前还剩：<i>5</i>辆<br> 已预定：<i>10</i>辆
				</div>
		</a></li>
		</c:forEach>
	</ul>
	<!--主体内容 结束-->

	<!--尾部 开始-->
	<div class="footer">
		<ul>
			<li><a href="<c:url value="UserAction?operate=listCar&index=index"></c:url>">首页</a></li>
			<c:if test="${!empty sessionScope.userInfo }">
			<li><a href="<c:url value="UserAction?operate=fabu"></c:url>" class="a3"><img src="img/img_03.png"></a></li>
			</c:if>
			<c:if test="${empty sessionScope.userInfo }">
			<li><a href="<c:url value="login.jsp"></c:url>" class="a3"><img src="img/img_03.png"></a></li>
			</c:if>
			
			<c:if test="${!empty sessionScope.userInfo }">
			<li><a href="<c:url value="UserAction?operate=show"></c:url>" class="a2">个人中心</a></li>
			</c:if>
			<c:if test="${empty sessionScope.userInfo }">
			<li><a href="<c:url value="login.jsp"></c:url>" class="a2">个人中心</a></li>
			</c:if>
		</ul>
	</div>
	<!--尾部 结束-->

</body>
</html>