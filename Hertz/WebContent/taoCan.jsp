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
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>套餐详情</title>
    <link rel="stylesheet" type="text/css" href="css/css.css">
    <script src="js/jQuery.js"></script>
  	<script src="js/mui.js"></script>
  	<script type="text/javascript"
	src='<c:url value='/js/jquery-ui-datepicker.js' />'>
	</script>
  	<script type="text/javascript">
	function gouWuChe() {
		alert("你还没有登陆，请登录后重试！")
	}

	function jiaRu() {
		alert("添加成功")
	}

	$(function() {
		$("#date_1").datepicker();
	})

	$(function() {
		$("#date_2").datepicker();
	})
</script>
</head>

<body class="body">
<i>${msg}</i>
<!--头部 开始-->
<div class="header">
	<div class="header_main" style="background:#FF6666;">
        <a href="javascript:history.go(-1)" class="a_fh" class="a_fh">&lt返回</a>
        <h3>婚庆套餐</h3>
    </div>
</div>
<!--头部 结束-->
<i>${msg}</i>
<!--主体内容 开始-->
<div id="slider1" class="mui-slider" >
    <div class="mui-slider-group mui-slider-loop">
        <!-- 额外增加的一个节点(这里是最后一张轮播图片) -->
        <div class="mui-slider-item mui-slider-item-duplicate">
            <a href="#">
                <img src="img/gg.png" class="img">
            </a>
        </div>
        <!-- 第一张 -->
        <div class="mui-slider-item">
            <a href="#">
                <img src="img/gg.png" class="img">
            </a>
        </div>
        <!-- 第二张 -->
        <div class="mui-slider-item">
            <a href="#">
                <img src="img/gg.png" class="img">
            </a>
        </div>
        <!-- 第三张 -->
        <div class="mui-slider-item">
            <a href="#">
                <img src="img/gg.png" class="img">
            </a>
        </div>
         <!-- 第四张 -->
        <div class="mui-slider-item">
            <a href="#">
                <img src="img/gg.png" class="img">
            </a>
        </div>
        <!-- 额外增加的一个节点(循环轮播：最后一个节点是第一张轮播) -->
        <div class="mui-slider-item mui-slider-item-duplicate">
            <a href="#">
                <img src="img/gg.png">
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
        interval: 5000
    });
</script>
<ul class="xx_one">
	<li>
    	<i><img src="img/img_21.png">品牌：</i>
        <p>${carInfo.brand}(${carInfo.model})</p>
    </li>
    <li>
    	<i><img src="img/img_22.png">颜色：</i>
        <p>${carInfo.color}</p>
    </li>
    <li>
    	<i><img src="img/img_33.png">座位：</i>
        <p>${carInfo.seats}位座</p>
    </li>
    <li>
    	<i><img src="img/img_25.png">排量：</i>
        <p>${carInfo.cc}</p>
    </li>
    <li>
    	<i><img src="img/img_28.png">排挡：</i>
        <p>${carInfo.gears}</p>
    </li>
    <li>
    	<i><img src="img/img_34.png">公里数：</i>
        <p>${carInfo.km}公里</p>
    </li>
</ul>
<div class="xx_two">
	<div class="nr">
    	<img src="img/02.png">
        <img src="img/02.png">
        <img src="img/02.png">
        <img src="img/02.png">
    </div>
</div>
<form action=" <c:url value="UserAction"></c:url>">
<div class="xx_three">
		<input type="hidden" name="operate" value="saveShopCar"> <input
			type="hidden" name="carId" value="${carInfo.carId}">
	<h3>租车须知：<br>${carInfo.remarks}</h3>
    <p><i>数量：${carInfo.count}量/套</i></p>
    <p>
				<i>租期：</i> <input type="text" class="txt" name="takeTime"
					id="date_1" readonly> <i>&nbsp;-&nbsp;</i> <input
					type="text" class="txt" name="retTime" id="date_2" readonly>
			</p>
    <p class="p"><i>租价：</i>
    	<strong>${carInfo.price}</strong>
        <em>元/套</em>
    </p>
</div>
<div class="xx_four">
			<c:if test="${!empty sessionScope.userInfo }">
				<input type="submit" class="btn" name="method" value="加入购物车" >
				<input type="submit" class="btn btn_yellow" name="method" value="立即预定">
			</c:if>

			<c:if test="${empty sessionScope.userInfo }">
				<input type="button" class="btn" value="加入购物车" onclick="gouWuChe()">
				<input type="button" class="btn btn_yellow" value="立即预定"
					onclick="gouWuChe()">
			</c:if>
		</div>
		</form>
<!--主体内容 结束-->


</body>
</html>