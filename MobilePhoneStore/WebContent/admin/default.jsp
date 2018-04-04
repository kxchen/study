<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台首页</title>
</head>
<body>
	<h5>
		<c:if test="${!empty sessionScope.adminInfo }">

			<a
				href="<c:url value="/AdminInfoController?operate=list&adminId=${sessionScope.adminInfo.adminId}"></c:url>">${sessionScope.adminInfo.adminName}</a>
			<a href="<c:url value="/UserInfoController?operate=quit"></c:url>">退出</a>
		</c:if>

	</h5>
	<a
		href="<c:url value="/MobilePhoneInfoController?operate=list"></c:url>">手机管理</a>
	<a href="<c:url value="/UserInfoController?operate=list"></c:url>">会员列表</a>
	<a href="<c:url value="/mobilePhone/category.jsp"></c:url>">类别管理</a>
	<a href="<c:url value="/index.jsp"></c:url>">返回网站首页</a>
</body>
</html>