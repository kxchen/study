<%@page import="com.school.dto.AdminInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索结果业</title>
</head>
<body>
<%@include file="/admin/nav.jsp"%>
	<%
		ArrayList<AdminInfo> adminInfoListByName = (ArrayList<AdminInfo>) request
				.getAttribute("adminInfoListByName");
		ArrayList<AdminInfo> adminInfoListByAccount = (ArrayList<AdminInfo>) request
				.getAttribute("adminInfoListByAccount");
	%>
	<form action="<%=request.getContextPath()%>/AdminInfoController"
		method="post">
		<input type="hidden" name="operate" value="search" /> <input
			type="text" name="account" placeholder="用户名/姓名关键字"> <input
			type="submit" name="submit" value="搜索" />
	</form>
	<span><%=request.getAttribute("searchMsg") == null ? "" : request.getAttribute("searchMsg").toString()%></span>
	<table>
		<tr>
			<td>ID</td>
			<td>用户名</td>
			<td>姓名</td>
		</tr>
		<%
			if (adminInfoListByName != null) {
		%>
		<%
			for (int i = 0; i < adminInfoListByName.size(); i++) {
					AdminInfo adminInfo = adminInfoListByName.get(i);
		%>
		<tr>
			<td><%=adminInfo.getId()%></td>
			<td><%=adminInfo.getAccount()%></td>
			<td><%=adminInfo.getAdminName()%></td>
			<td><a
				href="<%=request.getContextPath()%>/AdminInfoController?operate=delete&adminId=<%=adminInfo.getId()%>">删除</a>
				<a
				href="<%=request.getContextPath()%>/AdminInfoController?operate=edit&adminId=<%=adminInfo.getId()%>">修改</a></td>

		</tr>
		<%
			}
		%>
		<%
			}
		%>
		<%
			if (adminInfoListByAccount != null) {
		%>
		<%
			for (int i = 0; i < adminInfoListByAccount.size(); i++) {
					AdminInfo adminInfo = adminInfoListByAccount.get(i);
		%>
		<tr>
			<td><%=adminInfo.getId()%></td>
			<td><%=adminInfo.getAccount()%></td>
			<td><%=adminInfo.getAdminName()%></td>
			<td><a
				href="<%=request.getContextPath()%>/AminInfoController?operate=delete&adminId=<%=adminInfo.getId()%>">删除</a>
				<a
				href="<%=request.getContextPath()%>/AminInfoController?operate=edit&adminId=<%=adminInfo.getId()%>">修改</a></td>

		</tr>
		<%
			}
		%>
		<%
			}
		%>


		<tr>
			<td><a
				href="<%=request.getContextPath()%>/AminInfoController?operate=create">管理员添加</a></td>
		</tr>
	</table>
	<span><%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg").toString()%></span>
</body>
</html>