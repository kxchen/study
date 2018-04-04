<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员列表</title>
</head>
<body>
	<h2>列表页</h2>
	<table>
		<tr>
			<td>用户名</td>
		</tr>
		<c:forEach items="${adminInfoList}" var="adminInfo">
			<tr>
				<td>${adminInfo.adminName}</td>
				<td><a
					href="<c:url value="/AdminInfoController?operate=delete&adminId=${adminInfo.adminId}"></c:url>">删除</a>
			</tr>
		</c:forEach>

	</table>
	<a href="<c:url value="/admin/save.jsp"></c:url>">添加管理员</a>
	<a href="<c:url value="/admin/update.jsp"></c:url>">修改我的密码</a>
	<span>${msg}</span>

</body>
</html>