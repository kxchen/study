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

<title>用户管理</title>

<link rel="stylesheet"
	href="<c:url value="/admin/css/bootstrap.min.css"></c:url>" type="text/css">

<link rel="stylesheet" href="<c:url value="/admin/css/style.css"></c:url>">

<link href='<c:url value="/admin/css/bootstrap.css"></c:url>' rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value="/admin/font-awesome/css/font-awesome.min.css"></c:url>"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value="/admin/fonts/font-slider.css"></c:url>" type="text/css">

<script src="<c:url value="/admin/js/jquery-2.1.1.js"></c:url>"></script>

<script type="text/javascript" src="<c:url value="/admin/js/jquery.min.js"/>"></script>

<script src="<c:url value="/admin/js/bootstrap.min.js"></c:url>"></script>
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
						action="<c:url value="/AdminAction?operate=listUser"/>"
						method="post">
						<div class="input-append">
							<div class="col-sm-5">
								<input type="hidden" name="pageNo" value="${pageNo}"> <input
									type="hidden" name="totalPage" value="${totalPage}"> <input
									type="hidden" name="userName" value="${userName}"> <input
									type="hidden" name="phone" value="${phone}"><input
									type="hidden" name="isCer" value="${isCer}">
									 <input 
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
								href="<c:url value="/AdminAction?operate=list"></c:url>"><span
									class="glyphicon glyphicon-user"></span>${sessionScope.adminInfo.adminName}</a>
							</li>
							<li><a
								href="<c:url value="/AdminAction?operate=quit"></c:url>"><span
									class="glyphicon  "></span></a>退出</li>
						</c:if>
						<c:if test="${empty sessionScope.adminInfo }">
							<li><a href="<c:url value="/admin/login.jsp"></c:url>"><span
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
					<i class="fafa-bars"></i>
				</button>
			</div>
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">

					<li><a href="<c:url value="UserAction?operate=listCar&index=index"></c:url>">前台首页</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">用户管理</a>
						<div class="dropdown-menu">
							<div class="dropdown-inner">
								<ul class="list-unstyled">
									<li><a
										href="<c:url value="/AdminAction?operate=listUser"></c:url>">用户列表</a></li>
								</ul>
							</div>
						</div></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">车辆管理</a>
						<div class="dropdown-menu">
							<div class="dropdown-inner">
								<ul class="list-unstyled">
									<li><a
										href="<c:url value="/AdminAction?operate=listCar"></c:url>">车辆列表</a></li>
									<li><a
										href="<c:url value="saveCar.jsp"></c:url>">添加车辆</a></li>
								</ul>
							</div>
						</div></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">套餐管理</a>
						<div class="dropdown-menu">
							<div class="dropdown-inner">
								<ul class="list-unstyled">
									<li><a
										href="<c:url value="/AdminAction?operate=listCar&method=listCombo"></c:url>">套餐列表</a></li>
										<li><a
										href="<c:url value="saveCombo.jsp"></c:url>">添加套餐</a></li>
								</ul>
							</div>
						</div></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">订单管理</a>
						<div class="dropdown-menu">
							<div class="dropdown-inner">
								<ul class="list-unstyled">
									<li><a
										href="<c:url value="/AdminAction?operate=listOrder"></c:url>">订单列表</a></li>
								</ul>
							</div>
						</div></li>
					
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">管理员管理</a>
						<div class="dropdown-menu">
							<div class="dropdown-inner">
								<ul class="list-unstyled">
									<li><a
										href="<c:url value="/AdminAction?operate=list"></c:url>">管理员列表</a></li>
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
										action="<c:url value="/AdminAction?operate=listUser"/>"
										method="post">
										用户名:<input type="text" name="userName" value="${userName}">
										联系电话:<input type="text" name="phone" value="${phone}">
										<select class="btn btn-default" name="isCer" title="是否认证">
										<option value="">是否认证</option>
										<option value="0">未认证</option>
										<option value="1">未通过</option>
										<option value="2">已认证</option>
										</select>
										<input type="submit" class="btn btn-info" value="查询"
											onclick="changePage('frist')"> <a href="<c:url value="/AdminAction?operate=listUser"/>"
											class="btn btn-info btn-small"><i class="glyphicon glyphicon-refresh"></i></a>
									</form>
									<br>
									<table class="table table-hover table-bordered ">
										<thead>
											<tr>
												<th width="60" align="left">序号</th>
												<th width="120" align="left">姓名</th>
												<th width="120" align="left">性别</th>
												<th width="120" align="left">联系电话</th>
												<th colspan="3"><div class="text-center">操作</div></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${userInfoList}" var="userInfo"
												varStatus="index">
												<tr>
													<td>${index.count}</td>
													<td>${userInfo.userName}</td>
													<td>${userInfo.sex}</td>
													<td>${userInfo.phone}</td>
													<td><a class="btn btn-2 "
														href="<c:url value="/AdminAction?operate=deleteUser&userId=${userInfo.userId}"></c:url>"><i class="glyphicon glyphicon-trash"></i></a>
														
														<c:if test="${userInfo.isCer==1}">
														<a href="<c:url value="/AdminAction?operate=updateUser&Cer=1&userId=${userInfo.userId}"/>"
														class="btn btn-2">确认认证</a>
														</c:if> 
														<c:if test="${userInfo.isCer==2}">
														<a href="<c:url value="/AdminAction?operate=updateUser&Cer=2&userId=${userInfo.userId}"/>"
														class="btn btn-2">取消认证</a>
														</c:if> 
														 <a data-toggle="modal" class="btn btn-2"
									href="<c:url value="/AdminAction?operate=showUser&userId=${userInfo.userId}"></c:url>"
									data-target="#myModal">详情</a>
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
						web开发工作室&nbsp&nbsp第六组
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
