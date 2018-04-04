<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>我的订单</title>
    <link rel="stylesheet" type="text/css" href="css/css.css">
	<script src="js/mui.js"></script>
</head>

<body class="body">
<!--头部 开始-->
<div class="header">
	<div class="header_main">
        <a href="javascript:history.go(-1)" class="a_fh" class="a_fh">&lt返回</a>
        <h3>我的订单</h3>
    </div>
</div>
<!--头部 结束-->

<!--主体内容 开始-->
<div class="list_three">
	<a href="<c:url value="UserAction?operate=listOrder&state=yudingzhong"></c:url>">预约中</a>
    <a href="<c:url value="UserAction?operate=listOrder&state=zulinzhong"></c:url>">租赁中</a>
    <a href="<c:url value="UserAction?operate=listOrder&state=yiwancheng"></c:url>" class="sel" >已完成</a>
</div>
<ul class="sy_four list_four">
<c:forEach items="${orderDetailList}" var="orderDetail">
	<li>
    	<a href="<c:url value="UserAction?operate=showCar&carId=${orderDetail.carInfo.carId}"></c:url>">
        	<div class="img"><img src="<c:if test="${!empty orderDetail.carInfo.carImg }">
					<c:url value="/upload/${orderDetail.carInfo.carImg}"></c:url>
					</c:if>
					<c:if test="${empty orderDetail.carInfo.carImg }">
						img/01.jpg
					</c:if>"></div>
        	<div class="left">
            	<i>${orderDetail.carInfo.brand}${orderDetail.carInfo.model}</i><br>
            乘坐${orderDetail.carInfo.seats }<br>
             ${orderDetail.carInfo.type}/${orderDetail.carInfo.cc}${orderDetail.carInfo.gears}<br>
            颜色：${orderDetail.carInfo.color}
            </div>
            <div class="right">
            	 <i> <span>${orderDetail.carInfo.price}</span></i>元/天<br>
                目前还剩：<i>5</i>辆<br>
                已预定：<i>10</i>辆<br>
                <c:if test="${orderDetail.isAbolish==2}">
            <i  class="a">已取消</i>
            </c:if>
            </div>
        </a>    
    </li>
</c:forEach>
</ul>
<!--主体内容 结束-->

</body>
</html>