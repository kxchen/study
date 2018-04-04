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

</head>
<body>
	<!--Top-->
	<nav id="top">
		<div class="container">
			<div class="row">
				<div class="col-xs-6"></div>
				<div class="col-xs-6">
					<ul class="top-link">
						<c:if test="${!empty sessionScope.adminInfo }">
							<li><a
								href="<c:url value="/AdminInfoController?operate=list"></c:url>"><span
									class="glyphicon glyphicon-user"></span>${sessionScope.adminInfo.adminName}</a>
							</li>
							<li><a href="<c:url value="/admin/update.jsp"></c:url>"><span
									class="glyphicon "></span>修改密码</a></li>
							<li><a
								href="<c:url value="/AdminInfoController?operate=quit"></c:url>"><span
									class="glyphicon  "></span></a>退出</li>
						</c:if>
						<c:if test="${empty sessionScope.adminInfo }">
							<li><a href="<c:url value="/account.jsp"></c:url>"><span
									class="glyphicon glyphicon-user"></span>登陆</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<!--Header-->

	<!--Navigation-->
	<nav id="menu" class="navbar">
		<div class="container">
			<div class="navbar-header">
				<span id="heading" class="visible-xs">Categories</span>
				<button type="button" class="btn btn-navbar navbar-toggle"
					data-toggle="collapse" data-target=".navbar-ex1-collapse">
					<i class="fa fa-bars"></i>
				</button>
			</div>
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">

					<li><a href="<c:url value="index.jsp"></c:url>">前台首页</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">手机管理</a>
						<div class="dropdown-menu">
							<div class="dropdown-inner">
								<ul class="list-unstyled">
									<li><a
										href="<c:url value="/MobilePhoneInfoController?operate=list"></c:url>">手机列表</a></li>
									<li><a
										href="<c:url value="/MobilePhoneInfoController?operate=create"></c:url>">添加手机</a></li>
								</ul>
							</div>
						</div></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">类别管理</a>
						<div class="dropdown-menu">
							<div class="dropdown-inner">
								<ul class="list-unstyled">
									<li><a
										href="<c:url value="/BrandController?operate=list"></c:url>">品牌管理</a></li>
									<li><a
										href="<c:url value="/NetworkTypeController?operate=list"></c:url>">网络类型</a></li>
									<li><a
										href="<c:url value="/OperatingSystemController?operate=list"></c:url>">操作系统</a></li>
									<li><a
										href="<c:url value="/ScreenSizeController?operate=list"></c:url>">屏幕尺寸</a></li>
								</ul>
							</div>
						</div></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">订单管理</a>
						<div class="dropdown-menu">
							<div class="dropdown-inner">
								<ul class="list-unstyled">
									<li><a
										href="<c:url value="/OrderController?operate=list"></c:url>">订单列表</a></li>
								</ul>
							</div>
						</div></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">会员管理</a>
						<div class="dropdown-menu">
							<div class="dropdown-inner">
								<ul class="list-unstyled">
									<li><a
										href="<c:url value="/UserInfoController?operate=list"></c:url>">会员列表</a></li>
								</ul>
							</div>
						</div></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">管理员管理</a>
						<div class="dropdown-menu">
							<div class="dropdown-inner">
								<ul class="list-unstyled">
									<li><a
										href="<c:url value="/AdminInfoController?operate=list"></c:url>">管理员列表</a></li>
									<li><a href="<c:url value="/admin/save.jsp"></c:url>">添加管理员</a></li>
								</ul>
							</div>
						</div></li>
				</ul>
			</div>
		</div>
	</nav>
	<!--///////////////////Cart Page//////////////////////-->
	<div id="page-content" class="single-page"></div>
	<footer> </footer>

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
