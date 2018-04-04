<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="/admin/nav.jsp"%>
	<form action="<%=request.getContextPath()%>/AdminInfoController"
		method="post">
		<input type="hidden" name="operate" value="changePsw"> 旧密码：<input
			type="password" name="password"><br>新密码：<input
			type="password" name="newPassword1"><br>再次输入新密码：<input
			type="password" name="newPassword2"><br> <input
			type="submit" value="修改" />
	</form>
	<span><%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg").toString()%></span>
</body>
</html>