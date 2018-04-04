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

<title>MobilePhoneStore</title>

<!-- Bootstrap Core CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">

<!-- Custom CSS -->
<link rel="stylesheet" href="css/style.css">


<!-- Custom Fonts -->
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="fonts/font-slider.css" type="text/css">

<!-- jQuery and Modernizr-->
<script src="js/jquery-2.1.1.js"></script>

<!-- Core JavaScript Files -->
<script src="js/bootstrap.min.js"></script>

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

	function jiaRu() {
		alert("添加成功")
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
					action="<c:url value="/UserInfoController?operate=listPhone"></c:url>"
					method="post" class="form-search">
					<input type="text" name="search" value="${search}"
						class="input-medium search-query">
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
	<!--///////////////////Product Page///////////////////-->
	<!--//////////////////////////////////////////////////-->
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<ul class="breadcrumb">
						<li><a href="<c:url value="index.jsp"></c:url>">首页</a></li>
						<li><a
							href="<c:url value="UserInfoController?operate=listPhone"></c:url>">手机列表</a></li>
						<li><a
							href="<c:url value="UserInfoController?operate=product&mobPhoneId=${mobilePhoneInfo.mobPhoneId}"></c:url>">${mobilePhoneInfo.brand.brandName}${mobilePhoneInfo.model}</a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div id="main-content" class="col-md-8">
					<div class="product">
						<div class="col-md-6">
							<div class="image">
								<img
									src="<c:if test="${!empty mobilePhoneInfo.imgPath }">
													<c:url value="/upload/${mobilePhoneInfo.imgPath}"></c:url>
												</c:if>
												<c:if test="${empty mobilePhoneInfo.imgPath }">
												images/default.jpg
												</c:if>" />

							</div>
						</div>
						<div class="col-md-6">
							<div class="caption">
								<div class="name">
									<h3>
										<span>${mobilePhoneInfo.brand.brandName}&nbsp${mobilePhoneInfo.model}</span>
									</h3>
								</div>

								<div class="info">
									<ul>
										<li>品&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp牌&nbsp:&nbsp${mobilePhoneInfo.brand.brandName}</li>
										<li>型&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp号&nbsp:&nbsp${mobilePhoneInfo.model}</li>
										<li>操作系统&nbsp:&nbsp${mobilePhoneInfo.operatingSystem.opeSystemName}</li>
										<li>网络类型&nbsp:&nbsp&nbsp${mobilePhoneInfo.networkType.netTypeName}</li>
										<li>像&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp素&nbsp:&nbsp${mobilePhoneInfo.pixels}</li>
										<li>屏幕尺寸&nbsp:&nbsp${mobilePhoneInfo.screenSize.scrSizeName}</li>
										<li>颜&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp色&nbsp:&nbsp${mobilePhoneInfo.color}</li>
										<li>运行内存&nbsp:&nbsp${mobilePhoneInfo.ram}</li>

									</ul>
								</div>
								<div class="price">
									￥${mobilePhoneInfo.realPrice}<span>￥${mobilePhoneInfo.price}</span>
								</div>

								<div class="well">
									<c:if test="${!empty sessionScope.userInfo }">
										<a
											href="<c:url value="UserInfoController?operate=saveShopCart&mobPhoneId=${mobilePhoneInfo.mobPhoneId}"></c:url>"
											onclick="jiaRu()" class="btn btn-2 ">加入购物车</a>
									</c:if>

									<c:if test="${empty sessionScope.userInfo }">
										<a href="#" onclick="gouWuChe()" class="btn btn-2 ">加入购物车</a>
									</c:if>
								</div>
							</div>
						</div>
						<div class="clear"></div>
					</div>
					<div class="product-desc">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#description">简介</a></li>
						</ul>
						<div class="tab-content">
							<div id="description" class="tab-pane fade in active">

								<p>${mobilePhoneInfo.descipt}</p>
							</div>
						</div>
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

	<!-- IMG-thumb -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body"></div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>
</html>
