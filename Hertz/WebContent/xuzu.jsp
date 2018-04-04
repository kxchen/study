<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>续租</title>
    <link rel="stylesheet" type="text/css" href="css/css.css">
	<script src="js/mui.js"></script>
</head>

<body class="body">
<!--头部 开始-->
<div class="header">
	<div class="header_main">
         <a href="javascript:history.go(-1)" class="a_fh" class="a_fh">&lt返回</a>
        <h3>确认支付</h3>
    </div>
</div>
<!--头部 结束-->

<!--主体内容 开始-->
<div class="q">
	<i>订单编号：${orderDetailList[0].orderInfo.orderId} 下单时间:${orderDetailList[0].orderInfo.buyTime}</i>
</div>
<c:forEach items="${orderDetailList}" var="orderDetail">
<ul class="buy">
    <li>
    <i>编号：${orderDetail.orderDetailId }</i><br>
       	<div class="left">
       	<i>${orderDetail.carInfo.brand}${orderDetail.carInfo.model}</i><br>
          乘坐${orderDetail.carInfo.seats }<br>
         ${orderDetail.carInfo.type}/${orderDetail.carInfo.cc}${orderDetail.carInfo.gears}<br>
          颜色：${orderDetail.carInfo.color}
		</div>
       	<p>
       	<i> <span>${orderDetail.reaPrice}</span></i><span>元/天</span><br>
        		<span>共：${orderDetail.quantity}天<br></span>
        		<span>小计：</span><i> <span>${orderDetail.carInfo.price*orderDetail.quantity}</span></i><br>
        	<span>预付：</span><i> <span>${orderDetail.paid}</span></i><br>

       </p> 
	</li>
</ul>
</c:forEach>
 <ul class="buy">
    <li>
    <p>
        	<span>总价：</span><i> ${orderDetailList[0].orderInfo.pay}</i><span>元 </span> <br>
        	<span>预付款：</span><i>${orderDetailList[0].orderInfo.reaPay}</i><span>元 </span> 
        	</p>
	</li>
</ul>
<h3 class="rz_one">
	<i><img src="img/img_16.png"></i>
    联系人信息
</h3>

<form method="post" action="<c:url value="/UserAction"/>">
<input type="hidden" name="operate" value="pay">
<ul class="rz_two">
	<li>联系人:<input type="text" class="txt" name="contacts" value="${userInfo.userName }"></li>
    <li>联系电话:<input type="text" class="txt" name="conPhone" value="${userInfo.phone }"></li>
    <li>联系地址:<input type="text" class="txt" name="conAddress" value="${userInfo.address }"></li> 
</ul>
<div class="rz_three">
 <input type="hidden" name="orderId" value="${orderDetailList[0].orderInfo.orderId}">
	<input type="submit" class="btn" value="确认支付">
</div>
</form>
<!--主体内容 结束-->

</body>
</html>