<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="">
<meta name="author" content="">

<title>Mobile Shop</title>

<link rel="stylesheet"
	href="<c:url value="/css/bootstrap.min.css"></c:url>" type="text/css">

<link rel="stylesheet" href="<c:url value="/css/style.css"></c:url>">

<link href='<c:url value="/css/bootstrap.css"></c:url>' rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value="/font-awesome/css/font-awesome.min.css"></c:url>"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value="/fonts/font-slider.css"></c:url>" type="text/css">

<script src="<c:url value="/js/jquery-2.1.1.js"></c:url>"></script>

<script type="text/javascript" src="<c:url value="/js/jquery.min.js"/>"></script>

<script src="<c:url value="/js/bootstrap.min.js"></c:url>"></script>

<script type="text/javascript">
	function gouWuChe() {
		alert("你还没有登陆，请登录后重试！")
	}
</script>

<script type="text/javascript">
	function changePage(method) {
		var pageNo = document.forms[1].pageNo.value;
		var totalPage = document.forms[1].totalPage.value;
		if (method == "next") {
			if (parseInt(pageNo) >= parseInt(totalPage)) {
				alert("当前已经是最后一页！");
				return false;
			} else {
				document.forms[1].pageNo.value = parseInt(pageNo) + 1;
			}
		}
		if (method == "previous") {
			if (parseInt(pageNo) <= 1) {
				alert("当前已经是第一页");
				return false;
			} else {
				document.forms[1].pageNo.value = parseInt(pageNo) - 1;
			}
		}
		if (method == "frist") {
			document.forms[1].pageNo.value = 1;
		}
		if (method == "end") {
			document.forms[1].pageNo.value = totalPage;
		}

		document.forms[1].submit();
	}
</script>
</head>
<body>
	<!--Top-->
	<nav id="top">
		<div class="container">
			<div class="row">
				<div class="col-xs-6"></div>
				<div class="col-xs-6">
					<ul class="top-link">
						<c:if test="${!empty sessionScope.userInfo }">
							<li><a
								href="<c:url value="/UserInfoController?operate=edit&userId=${sessionScope.userInfo.userId}"></c:url>"><span
									class="glyphicon glyphicon-user"></span>${sessionScope.userInfo.loginName}</a></li>
							<li><a
								href="<c:url value="/UserInfoController?operate=listOrder"></c:url>"><span
									class="glyphicon "></span>个人订单</a></li>
							<li><a href="<c:url value="/user/changePsw.jsp"></c:url>"><span
									class="glyphicon "></span>修改密码</a></li>
							<li><a
								href="<c:url value="/UserInfoController?operate=quit"></c:url>"><span
									class="glyphicon "></span>退出</a></li>
						</c:if>
						<c:if test="${empty sessionScope.userInfo }">
							<li><a href="<c:url value="/account.jsp"></c:url>"><span
									class="glyphicon glyphicon-user"></span>登陆</a></li>
							<li><a href="<c:url value="/account.jsp"></c:url>"><span
									class="glyphicon "></span>注册</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<!--Header-->
	<header class="container">
		<div class="row">
			<div class="col-md-4">
				<div id="logo">
					<img src="images/logo.png" />
				</div>
			</div>
			<div class="col-md-4">
				<form
					action="<c:url value="UserInfoController?operate=listPhone"></c:url>"
					method="post" class="form-search">
					<input type="text" name="search" class="input-medium search-query">
					<button type="submit" class="btn">
						<span class="glyphicon glyphicon-search"></span>
					</button>
				</form>
			</div>
			<div class="col-md-4">
				<div id="cart">
					<c:if test="${!empty sessionScope.userInfo }">
						<a class="btn btn-1"
							href="<c:url value="/UserInfoController?operate=listShopCart"></c:url>"><span
							class="glyphicon glyphicon-shopping-cart"></span>购物车 </a>
					</c:if>
				</div>
			</div>
		</div>

	</header>
	<!--Navigation-->
	<nav id="menu" class="navbar">
		<div class="container">
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<li><a href="<c:url value="index.jsp"></c:url>">首页</a></li>
					<li><a
						href="<c:url value="UserInfoController?operate=listPhone"></c:url>">手机列表</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!--//////////////////////////////////////////////////-->
	<!--///////////////////Cart Page//////////////////////-->
	<!--//////////////////////////////////////////////////-->
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<ul class="breadcrumb">
						<li><a href="<c:url value="index.jsp"></c:url>">首页</a></li>
						<li><c:if test="${!empty sessionScope.userInfo }">
								<a
									href="<c:url value="/UserInfoController?&operate=listShopCart"></c:url>">购物车</a>
							</c:if> <c:if test="${empty sessionScope.userInfo }">
								<a href="#" onclick="gouWuChe()">购物车</a>
							</c:if></li>

					</ul>
				</div>
			</div>

			<div class="container">
				<form
					action="<c:url value="UserInfoController?operate=listOrder"></c:url>"
					method="post" class="form-search">
					<input type="hidden" name="pageNo" value="${pageNo}"> <input
						type="hidden" name="totalPage" value="${totalPage}">
				</form>
				<table class="table table-hover table-bordered ">
					<thead>
						<tr>
							<th width="120" align="left">订单编号</th>
							<th width="100" align="left">订单总价格</th>
							<th width="100" align="left">是否付款</th>
							<th width="120" align="left">是否已发货</th>
							<th width="100" align="left">订单日期</th>
							<th colspan="3"><div class="text-center">操作</div></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orderInfoList}" var="orderInfo">
							<tr>
								<td>${orderInfo.orderId}</td>
								<td>${orderInfo.totalPrice}</td>
								<c:if test="${orderInfo.isPay==0}">
									<td>未付款</td>
								</c:if>
								<c:if test="${orderInfo.isPay==1}">
									<td>已付款</td>
								</c:if>
								<c:if test="${orderInfo.isDeliver==0}">
									<td>未发货</td>
								</c:if>
								<c:if test="${orderInfo.isDeliver==1}">
									<td>已发货</td>
								</c:if>
								<td>${orderInfo.submitTime}</td>

								<td align="center"><c:if test="${orderInfo.isPay==0}">
										<a href="<c:url value="/UserInfoController?operate=zhiFu&orderId=${orderInfo.orderId}"></c:url>" class="btn btn-2">付款</a>
									</c:if> <c:if test="${orderInfo.isDeliver==0&&orderInfo.isPay==1}">
										<a href="#" class="btn btn-2">提醒发货</a>
									</c:if> 
									
									<a href="<c:url value="/UserInfoController?operate=deleteOrder&orderId=${orderInfo.orderId}"></c:url>" class="btn btn-2">取消订单</a>
									
									<a data-toggle="modal" class="btn btn-2"
									href="<c:url value="/UserInfoController?operate=listOredeDetail&orderId=${orderInfo.orderId}"></c:url>"
									data-target="#myModal">查看明细</a></td>
							</tr>
							<!--  -->
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="row text-center">
				<ul class="pagination">
					<!-- <li class="active"><a href="#">1</a></li> -->
					<li><a>共有${count }条记录，当前第${pageNo }/${totalPage}页</a></li>
					<li><a href="#" onclick="changePage('frist')">首页</a></li>
					<li><a href="#" onclick="changePage('previous')">上一页</a></li>
					<li><a href="#" onclick="changePage('next')">下一页</a></li>
					<li><a href="#" onclick="changePage('end')">尾页</a></li>
				</ul>
			</div>

		</div>
	</div>
	<footer>
		
		<div class="copyright">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						web开发工作室&nbsp&nbsp第六组 &nbsp&nbsp<a
							target="_blank" href="<c:url value="/login.jsp"></c:url>">管理员后台登陆</a>
					</div>
					<div class="col-md-6">
						<div class="pull-right">
							<ul>
								<li><img src="images/visa-curved-32px.png" /></li>
								<li><img src="images/paypal-curved-32px.png" /></li>
								<li><img src="images/discover-curved-32px.png" /></li>
								<li><img src="images/maestro-curved-32px.png" /></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>

	<!-- 模态框（Modal）-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"></div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<script type="text/javascript">
		$("#myModal").on("hidden.bs.modal", function() {
			$(this).removeData("bs.modal");
		});
	</script>
</body>
</html>
