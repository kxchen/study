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
<script type="text/javascript">
	function gouWuChe() {
		alert("你还没有登陆，请登录后重试！")
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
									href="<c:url value="/UserInfoController?operate=listShopCart"></c:url>">购物车</a>
							</c:if> <c:if test="${empty sessionScope.userInfo }">
								<a href="#" onclick="gouWuChe()">购物车</a>
							</c:if></li>

					</ul>
				</div>
			</div>

			<c:forEach items="${shopCartList}" var="shopCart">
				<div class="row">
					<div class="product well">
						<div class="col-md-3">
							<div class="image">
								<img
									src="<c:if test="${!empty shopCart.mobilePhone.imgPath }">
													<c:url value="/upload/${shopCart.mobilePhone.imgPath}"></c:url>
												</c:if>
												<c:if test="${empty shopCart.mobilePhone.imgPath }">
												images/default.jpg
												</c:if>" />
							</div>
						</div>
						<div class="col-md-9">
							<div class="caption">
								<div class="name">
									<h3>
										<a
											href="<c:url value="UserInfoController?operate=product&mobPhoneId=${shopCart.mobilePhone.mobPhoneId}"></c:url>"><span>${shopCart.mobilePhone.brand.brandName}&nbsp${shopCart.mobilePhone.model}</span></a>
									</h3>
								</div>
								<div class="info">
									<ul>
										<li>品&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp牌&nbsp:&nbsp${shopCart.mobilePhone.brand.brandName}</li>
										<li>型&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp号&nbsp:&nbsp${shopCart.mobilePhone.model}</li>
										<li>操作系统&nbsp:&nbsp${shopCart.mobilePhone.operatingSystem.opeSystemName}</li>
										<li>网络类型&nbsp:&nbsp&nbsp${shopCart.mobilePhone.networkType.netTypeName}</li>
										<li>颜&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp色&nbsp:&nbsp${shopCart.mobilePhone.color}</li>

									</ul>
								</div>
								<div class="price">
									￥${shopCart.mobilePhone.realPrice*shopCart.quantity}<span>￥${shopCart.mobilePhone.price*shopCart.quantity}</span>
								</div>

								<form method="post"
									action="<c:url value="UserInfoController"></c:url>">
									<label>数量: </label> <input type="hidden" name="operate"
										value="updateShopCart"> <input type="hidden"
										name="shopCartId" value="${shopCart.shopCartId}"> <input
										class="form-inline quantity" type="text"
										value="${shopCart.quantity}" name="quantity">
									<button type="submit" class="btn btn-2 ">更改</button>
								</form>

								<hr>
								<a
									href="<c:url value="UserInfoController?operate=deleteShopCart&shopCartId=${shopCart.shopCartId}"></c:url>"
									class="btn btn-default pull-right">删除</a>
							</div>
						</div>
						<div class="clear"></div>
					</div>
				</div>
			</c:forEach>

			<div class="row">
				<div class="col-md-4 col-md-offset-8 ">
					<center>
						<a
							href="<c:url value="UserInfoController?operate=listPhone"></c:url>"
							class="btn btn-1">继续购物</a>
					</center>
				</div>
			</div>
			<div class="row">
				<div class="pricedetails">
					<div class="col-md-4 col-md-offset-8">
						<table>
							<h6>价格详细信息</h6>
							<tr>
								<td>总计</td>
								<td>￥${zongjia}</td>
							</tr>
							<tr>
								<td>折扣</td>
								<td>￥${zhekou}</td>
							</tr>

							<tr style="border-top: 1px solid #333">
								<td><h5>应付</h5></td>
								<td>￥${yingfu}</td>
							</tr>
						</table>
						<center>
							<a
								href="<c:url value="UserInfoController?operate=deleteShopCartAll"></c:url>"
								class="btn btn-1">清空购物车</a> <a
								href="<c:url value="UserInfoController?operate=saveOrder"></c:url>"
								class="btn btn-1">结账</a>

						</center>
					</div>
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
