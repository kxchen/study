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
<script type="text/javascript">
	function window_onload() {
		window.location.href="<c:url value="MobilePhoneInfoController?operate=hotSale"></c:url>"
	}
</script>
</head>

<body
	<c:if test="${mobilePhoneInfoList==null }">
onload="window_onload()"
</c:if>>

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
	<!--///////////////////HomePage///////////////////////-->
	<!--//////////////////////////////////////////////////-->
	<div id="page-content" class="home-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<!-- Carousel -->
					<div id="carousel-example-generic" class="carousel slide"
						data-ride="carousel">
						<!-- Indicators -->
						<ol class="carousel-indicators hidden-xs">
							<li data-target="#carousel-example-generic" data-slide-to="0"
								class="active"></li>
							<li data-target="#carousel-example-generic" data-slide-to="1"></li>
							<li data-target="#carousel-example-generic" data-slide-to="2"></li>
						</ol>
						<!-- Wrapper for slides -->
						<div class="carousel-inner">
							<div class="item active">
								<img src="images/main-banner1-1903x600.jpg" alt="First slide">
								<!-- Static Header -->
								<div class="header-text hidden-xs">
									<div class="col-md-12 text-center"></div>
								</div>
								<!-- /header-text -->
							</div>
							<div class="item">
								<img src="images/main-banner2-1903x600.jpg" alt="Second slide">
								<!-- Static Header -->
								<div class="header-text hidden-xs">
									<div class="col-md-12 text-center"></div>
								</div>
								<!-- /header-text -->
							</div>
							<div class="item">
								<img src="images/main-banner3-1903x600.jpg" alt="Third slide">
								<!-- Static Header -->
								<div class="header-text hidden-xs">
									<div class="col-md-12 text-center"></div>
								</div>
								<!-- /header-text -->
							</div>
						</div>

						<!-- Controls -->
						<a class="left carousel-control" href="#carousel-example-generic"
							data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left"></span>
						</a> <a class="right carousel-control"
							href="#carousel-example-generic" data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right"></span>
						</a>
					</div>
					<!-- /carousel -->
				</div>
			</div>

			<div class="row">
				<div class="banner">
					<div class="col-sm-6">
						<img src="images/sub-banner4.jpg" />
					</div>
					<div class="col-sm-6">
						<img src="images/sub-banner5.png" />
					</div>
				</div>
			</div>


			<div class="row">
				<div class="col-lg-12">
					<div class="heading">
						<h2>热销产品</h2>
					</div>
					<div class="products">


						<c:forEach items="${mobilePhoneInfoList}" var="mobilePhoneInfo">
							<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
								<div class="product">
									<div class="image">
										<a
											href="<c:url value="UserInfoController?operate=product&mobPhoneId=${mobilePhoneInfo.mobPhoneId}"></c:url>"><img
											src="<c:if test="${!empty mobilePhoneInfo.imgPath }">
													<c:url value="/upload/${mobilePhoneInfo.imgPath}"></c:url>
												</c:if>
												<c:if test="${empty mobilePhoneInfo.imgPath }">
												images/default.jpg
												</c:if>" /></a>


										<!--数据库中的图片路径：<c:url value="/upload/${mobilePhoneInfo.imgPath}"></c:url> -->
									</div>
									<div class="caption">
										<div class="name">
											<h3>
												<a
													href="<c:url value="UserInfoController?operate=product&mobPhoneId=${mobilePhoneInfo.mobPhoneId}"></c:url>">${mobilePhoneInfo.brand.brandName}&nbsp${mobilePhoneInfo.model}&nbsp${mobilePhoneInfo.operatingSystem.opeSystemName}手机&nbsp${mobilePhoneInfo.networkType.netTypeName}手机&nbsp${mobilePhoneInfo.screenSize.scrSizeName}屏幕&nbsp${mobilePhoneInfo.pixels}像素</a>

											</h3>
										</div>
										<div class="price">
											￥${mobilePhoneInfo.realPrice}<span>￥${mobilePhoneInfo.price}</span>
										</div>

										<div class="rating">
											<span class="glyphicon glyphicon-star"></span><span
												class="glyphicon glyphicon-star"></span><span
												class="glyphicon glyphicon-star"></span><span
												class="glyphicon glyphicon-star"></span><span
												class="glyphicon glyphicon-star-empty"></span>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 此处可以添加特价手机或推荐产品 -->
	<div class="row">
		<div class="banner">
			<div class="col-sm-4">
				<img src="images/sub-banner1.png" />
			</div>
			<div class="col-sm-4">
				<img src="images/sub-banner2.png" />
			</div>
			<div class="col-sm-4">
				<img src="images/sub-banner3.png" />
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
						web开发工作室&nbsp&nbsp第六组<a target="_blank"
							href="<c:url value="/login.jsp"></c:url>">管理员后台登陆</a>
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