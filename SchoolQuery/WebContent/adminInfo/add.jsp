<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员添加</title>
</head>
<body>
<%@include file="/admin/nav.jsp"%>
	<form action="<%=request.getContextPath()%>/AdminInfoController"
		method="post">
		<input type="hidden" name="operate" value="save"> 用户名：<input
			type="text" name="account"><br> 密码：<input
			type="password" name="password"><br> 姓名：<input
			type="text" name="adminName"><br> <input
			type="submit" value="添加" />
	</form>
	<span><%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg").toString()%></span>
</body>
</html>