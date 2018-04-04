<%@page import="com.school.dto.AdminInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改页</title>
</head>
<body>
<%@include file="/admin/nav.jsp"%>
	<h1>修改页</h1>
	<%
		AdminInfo adminInfo = (AdminInfo) request.getAttribute("adminInfo");
	%>
	<form action="<%=request.getContextPath()%>/AdminInfoController"
		method="post">
		<input type="hidden" name="operate" value="update" /> ID:<input
			type="text" name="id" value="<%=adminInfo.getId()%>"
			readonly="readonly" /> 用户名:<input type="text" name="account"
			value="<%=adminInfo.getAccount()%>" readonly="readonly" /> 姓名:<input
			type="text" name="adminName" value="<%=adminInfo.getAdminName()%>" />
		<input type="submit" name="submit" value="更新" /> <input type="reset"
			name="reset" value="重置" />
	</form>
	<span><%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg").toString()%></span>
</body>
</html>