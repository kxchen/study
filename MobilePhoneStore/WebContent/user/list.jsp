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
		var pageNo = document.forms[0].pageNo.value;
		var totalPage = document.forms[0].totalPage.value;
		if (method == "next") {
			if (parseInt(pageNo) >= parseInt(totalPage)) {
				alert("当前已经是最后一页！");
				return false;
			} else {
				document.forms[0].pageNo.value = parseInt(pageNo) + 1;
			}
		}
		if (method == "previous") {
			if (parseInt(pageNo) <= 1) {
				alert("当前已经是第一页");
				return false;
			} else {
				document.forms[0].pageNo.value = parseInt(pageNo) - 1;
			}
		}
		if (method == "frist") {
			document.forms[0].pageNo.value = 1;
		}
		if (method == "end") {
			document.forms[0].pageNo.value = totalPage;
		}

		document.forms[0].submit();
	}
</script>
</head>
</head>
<body>
	<!--Top-->
	<nav id="top">
		<div class="container">
			<div class="row">
				<div class="col-xs-6">
					<form class="form-search"
						action="<c:url value="/UserInfoController?operate=list"/>"
						method="post">
						<div class="input-append">
							<div class="col-sm-5">
								<input type="hidden" name="pageNo" value="${pageNo}"> <input
									type="hidden" name="totalPage" value="${totalPage}"> <input
									type="hidden" name="loginName" value="${loginName}"> <input
									type="hidden" name="phone" value="${phone}"> <input
									type="hidden" name="email" value="${email}"> <input
									name="search" type="text" value="${search}"
									class="form-control" placeholder="超级搜索">
							</div>
							<button type="submit" class="btn btn-info" onclick="changePage('frist')"><i class="glyphicon glyphicon-search"></i> </button>
							
							<!--  <input type="submit" value="搜索" onclick="changePage('frist')"
								class="btn btn-info">-->
						</div>
					</form>
					<i class="icon-search "></i>
				</div>
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
									class="glyphicon"></span>退出</a></li>
						</c:if>
						<c:if test="${empty sessionScope.adminInfo }">
							<li><a href="<c:url value="/login.jsp"></c:url>"><span
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

	<!--//////////////////////////////////////////////////-->
	<!--///////////////////Category Page//////////////////-->
	<!--//////////////////////////////////////////////////-->
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div id="main-content" class="col-md-8">
					<div class="row">
						<div class="col-md-12">
							<div class="products">
								<!-- 商品 -->
								<div class="container">
									<form
										action="<c:url value="/UserInfoController?operate=list"/>"
										method="post">
										用户名:<input type="text" name="loginName" value="${loginName}">
										联系电话:<input type="text" name="phone" value="${phone}">
										电子邮箱:<input type="text" name="email" value="${email}">
										<input type="submit" class="btn btn-info" value="查询"
											onclick="changePage('frist')"> <a href="<c:url value="/UserInfoController?operate=list"/>"
											class="btn btn-info btn-small"><i class="glyphicon glyphicon-refresh"></i></a>
									</form>
									<br>
									<table class="table table-hover table-bordered ">
										<thead>
											<tr>
												<th width="60" align="left">序号</th>
												<th width="120" align="left">用户名</th>
												<th width="120" align="left">真实姓名</th>
												<th width="120" align="left">联系电话</th>
												<th width="120" align="left">电子邮箱</th>
												<th width="120" align="left">注册时间</th>
												<th colspan="3"><div class="text-center">操作</div></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${userInfoList}" var="userInfo"
												varStatus="index">
												<tr>
													<td>${index.count}</td>
													<td>${userInfo.loginName}</td>
													<td>${userInfo.realName}</td>
													<td>${userInfo.phone}</td>
													<td>${userInfo.email}</td>
													<td>${userInfo.regtime}</td>
													<td><a class="btn btn-2 "
														href="<c:url value="/UserInfoController?operate=delete&userId=${userInfo.userId}"></c:url>"><i class="glyphicon glyphicon-trash"></i></a>
														<a class="btn btn-2 "
														href="<c:url value="/UserInfoController?operate=edit&userId=${userInfo.userId}"></c:url>">编辑</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row text-center">
					<ul class="pagination">
						<!-- <li class="active"><a href="#">1</a></li> -->
						<li><a>共有${count }条记录，当前第${pageNo}/${totalPage}页</a></li>
						<li><a href="#" onclick="changePage('frist')">首页</a></li>
						<li><a href="#" onclick="changePage('previous')">上一页</a></li>
						<li><a href="#" onclick="changePage('next')">下一页</a></li>
						<li><a href="#" onclick="changePage('end')">尾页</a></li>
					</ul>
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
