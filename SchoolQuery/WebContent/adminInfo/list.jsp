<%@page import="com.school.dto.AdminInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员列表</title>
<script type="text/javascript">
	function del(obj){
		if(window.confirm("确定删除？")){
			location.href="<%=request.getContextPath()%>/AdminInfoController?operate=delete&adminId="+obj;
			return true;
		}else{
			return false;
		}
	}
</script>

</head>
<body>
	<%@include file="/admin/nav.jsp"%>
	<%
		ArrayList<AdminInfo> adminInfoList = (ArrayList<AdminInfo>) request.getAttribute("adminInfoList");
	%>
	<h1>管理员列表</h1>
	<form action="<%=request.getContextPath()%>/AdminInfoController"
		method="post">
		<input type="hidden" name="operate" value="search" /> <input
			type="text" name="account" placeholder="用户名/姓名关键字"> <input
			type="submit" name="submit" value="搜索" />
	</form>
	<span><%=request.getAttribute("searchMsg") == null ? "" : request.getAttribute("searchMsg").toString()%></span>
	<table>
		<tr>
			<td>序号</td>
			<td>用户名</td>
			<td>姓名</td>
		</tr>
		<%
			for (int i = 0; i < adminInfoList.size(); i++) {
				AdminInfo adminInfo = adminInfoList.get(i);
		%>
		<tr>
			<td><%=i + 1%></td>
			<td><%=adminInfo.getAccount()%></td>
			<td><%=adminInfo.getAdminName()%></td>
			<td><a
				href="<%=request.getContextPath()%>/AdminInfoController?operate=show&adminId=<%=adminInfo.getId()%>">查看</a>
				<!--  <a
				href="<%=request.getContextPath()%>/AdminInfoController?operate=delete&adminId=<%=adminInfo.getId()%>">删除</a>-->
				<a
				href="<%=request.getContextPath()%>/AdminInfoController?operate=edit&adminId=<%=adminInfo.getId()%>">修改</a>
				<a onclick="del(<%=adminInfo.getId()%>)" href="javaScript:void(0)">删除</a></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td><a
				href="<%=request.getContextPath()%>/AdminInfoController?operate=create">管理员添加</a></td>
	</table>
	<span><%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg").toString()%></span>

</body>
</html>