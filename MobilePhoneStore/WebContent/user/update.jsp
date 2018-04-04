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

<!-- Bootstrap Core CSS -->
<link rel="stylesheet"
	href="<c:url value="/css/bootstrap.min.css"></c:url>" type="text/css">

<!-- Custom CSS -->
<link rel="stylesheet" href="<c:url value="/css/style.css"></c:url>">


<!-- Custom Fonts -->

<link rel="stylesheet"
	href="<c:url value="/font-awesome/css/font-awesome.min.css"></c:url>"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value="/fonts/font-slider.css"></c:url>" type="text/css">

<!-- jQuery and Modernizr-->
<script src="<c:url value="/js/jquery-2.1.1.js"></c:url>"></script>

<!-- Core JavaScript Files -->
<script src="<c:url value="/js/bootstrap.min.js"></c:url>"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
    <![endif]-->
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
			<div id="cart">
				<c:if test="${!empty sessionScope.userInfo }">
					<a class="btn btn-1"
						href="<c:url value="/UserInfoController?operate=listShopCart"></c:url>"><span
						class="glyphicon glyphicon-shopping-cart"></span>购物车 </a>
				</c:if>
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
	<!--///////////////////Contact Page///////////////////-->
	<!--//////////////////////////////////////////////////-->
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<ul class="breadcrumb">
						<li><a href="<c:url value="index.jsp"></c:url>">首页</a></li>
						<li><a
							href="<c:url value="/UserInfoController?operate=edit&userId=${sessionScope.userInfo.userId}"></c:url>">个人信息</a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="heading">
						<h1>个人信息</h1>
					</div>
				</div>
				<div class="col-md-6" style="margin-bottom: 30px;">
					<form name="form1" id="ff" method="post"
						action="<c:url value="/UserInfoController"></c:url>">

						<div class="form-group">
							<input type="hidden" name="operate" value="update"> <input
								type="hidden" name="userId" value="${userInfo.userId}">
						</div>
						<div class="form-group">
							用户名<input type="text" class="form-control"
								value="${userInfo.loginName}" readonly="readonly"
								name="loginName" id="loginName">
						</div>
						<div class="form-group">
							真实姓名<input type="text" class="form-control"
								value="${userInfo.realName}" name="realName" id="realName">
						</div>

						<div class="form-group">
							联系电话<input type="tel" class="form-control"
								value="${userInfo.phone}" name="phone" id="phone">
						</div>
						<div class="form-group">
							电子邮箱<input type="email" class="form-control"
								value="${userInfo.email}" name="email" id="email">
						</div>
						<div class="form-group">
							收货地址<input type="text" class="form-control"
								value="${userInfo.address}" name="address" id="address">
						</div>
						<div class="form-group">
							注册时间<input type="text" class="form-control"
								value="${userInfo.regtime}" readonly="readonly" name="regtime"
								id="regtime">
						</div>
						<button type="submit" class="btn btn-1">更新</button>
					</form>
				</div>
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
</body>
</html>