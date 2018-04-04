<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>套餐列表</title>
    <link rel="stylesheet" type="text/css" href="css/css.css">
	<script src="js/jQuery.js"></script>
	<script src="js/mui.js"></script>
    <script src="js/common.js"></script>
    <script type="text/javascript">
document.getElementsByName("form")[0].submit();
</script>
</head>

<body class="body">
<!--头部 开始-->
<div class="header">
	<div class="header_main" style="background:#FF6666">
        <a href="javascript:history.go(-1)" class="a_fh" class="a_fh">&lt返回</a>
        <h3>婚庆用车</h3>
    </div>
</div>
<!--头部 结束-->
<i>${msg}</i>
<!--主体内容 开始-->
<div class="list_one">
	<a href="#">品牌<i>▼</i></a>
    <a href="#">车型<i>▼</i></a>
    <a href="#">车系<i>▼</i></a>
    <a href="#">颜色<i>▼</i></a>
    <a href="#">价格<i>▼</i></a>
    <a href="#">座位数<i>▼</i></a>
</div>
<div class="list_search">
<form action="<c:url value="/UserAction"></c:url>" method="post">
<input type="hidden" name="operate" value="listCar">
<input type="hidden" name="purpose" value="${purpose}">
	<p><i class="qiche_i">搜索汽车</i>
    	<input type="text" class="txt txt_qiche" name="search">
    </p>
</form>
</div>
<div class="list_two"><i>推荐套餐</i></div>
<ul class="sy_four">
<c:forEach items="${ carInfoList}" var="carInfo">
    <li>
    	<a href="<c:url value="UserAction?operate=showCar&carId=${carInfo.carId}"></c:url>">
        	<div class="img"><img src="img/01.jpg"></div>
        	<div class="left">
            	<i>${carInfo.brand}</i><br>
                ${carInfo.brand}${carInfo.model} ${carInfo.color}  ${carInfo.count}辆<br>
                仅含车费，<br>
                其他费用另算
            </div>
            <div class="right">
            	<span>${carInfo.price}</span><i>元/天</i><br>
                目前还剩：<i>5</i>套<br>
                已预定：<i>10</i>套
            </div>
        </a>    
    </li>
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