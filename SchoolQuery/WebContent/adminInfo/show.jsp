<%@page import="com.school.dto.AdminInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>详细信息显示</title>
</head>
<body>
<%@include file="/admin/nav.jsp"%>
	<%
		AdminInfo adminInfo = (AdminInfo) request.getAttribute("adminInfo");
	%>
	<table>
		<tr>
			<td>ID</td>
			<td>用户名</td>
			<td>姓名</td>
		</tr>

		<tr>
			<td><%=adminInfo.getId()%></td>
			<td><%=adminInfo.getAccount()%></td>
			<td><%=adminInfo.getAdminName()%></td>
			<td><a
				href="<%=request.getContextPath()%>/AdminInfoController?operate=delete&adminId=<%=adminInfo.getId()%>">删除</a>
				<a
				href="<%=request.getContextPath()%>/AdminInfoController?operate=edit&adminId=<%=adminInfo.getId()%>">修改</a>
			</td>
		</tr>
		<tr>
		</tr>
	</table>
</body>
</html>