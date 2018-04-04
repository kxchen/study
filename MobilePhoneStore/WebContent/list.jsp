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
					<input type="text" name="search"  value="${search}" class="input-medium search-query">
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
	<!--///////////////////Category Page//////////////////-->
	<!--//////////////////////////////////////////////////-->
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<ul class="breadcrumb">
						<li><a href="<c:url value="index.jsp"></c:url>">首页</a></li>
						<li><a
							href="<c:url value="UserInfoController?operate=listPhone"></c:url>">手机列表</a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div id="main-content" class="col-md-8">
					<div class="row">
						<div class="col-md-12">
							<div class="products">
								<!-- 商品 -->
								<form
									action="<c:url value="UserInfoController?operate=listPhone"></c:url>"
									method="post" class="form-search">
									<input type="hidden" name="pageNo" value="${pageNo}"> <input
										type="hidden" name="totalPage" value="${totalPage}">
										<input type="hidden" name="search" value="${search}">
								</form>
								<c:forEach items="${mobilePhoneInfoList}" var="mobilePhoneInfo">
									<div class="col-lg-4 col-md-4 col-xs-12">
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
													<div class="well">
														<a
															href="<c:url value="UserInfoController?operate=product&mobPhoneId=${mobilePhoneInfo.mobPhoneId}"></c:url>"
															class="btn btn-2">详情</a>
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
										</div>
									</div>
								</c:forEach>

							</div>
						</div>
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
				<div id="sidebar" class="col-md-4">

					<div class="widget wid-type">
						<div class="heading">
							<h4>TYPE</h4>
						</div>
						<div class="content">
							<form
								action="<c:url value="UserInfoController?operate=listPhone"></c:url>"
								method="post">
								<input type="hidden" name="operate" value="listPhone"> <select
									name="brandName" title="手机品牌">
									<option value="">品牌</option>
									<c:forEach items="${brandList }" var="brand">
										<option value="${brand.brandName }">${brand.brandName }</option>
									</c:forEach>
								</select> <select name="netTypeName" title="网络类型">
									<option value="">网络类型</option>
									<c:forEach items="${networkTypeList }" var="networkType">
										<option value="${networkType.netTypeName }">${networkType.netTypeName }</option>
									</c:forEach>
								</select> <select name="opeSystemName" title="操作系统">
									<option value="">操作系统</option>
									<c:forEach items="${operatingSystemList }"
										var="operatingSystem">
										<option value="${operatingSystem.opeSystemName }">${operatingSystem.opeSystemName }</option>
									</c:forEach>
								</select> <select name="scrSizeName" title="屏幕尺寸">
									<option value="">屏幕尺寸</option>
									<c:forEach items="${screenSizeList }" var="screenSize">
										<option value="${screenSize.scrSizeName }">${screenSize.scrSizeName }</option>
									</c:forEach>
								</select> <input type="submit" value="筛选" />
							</form>
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
</body>
</html>
