<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>网络类型</h3>
	<c:forEach items="${networkTypeList}" var="networkType">
		<form action="<c:url value="/NetworkTypeController"></c:url>"
			method="post">
			<input type="hidden" name="operate" value="update"> <input
				type="hidden" name="netTypeId" value="${networkType.netTypeId}">
			<input type="text" name="netTypeName"
				value="${networkType.netTypeName}"> <input type="submit"
				value="更新" />
		</form>
		<a
			href="<c:url value="/NetworkTypeController?operate=delete&netTypeId=${networkType.netTypeId}"></c:url>">删除</a>
	</c:forEach>
	<form action="<c:url value="/NetworkTypeController"></c:url>"
		method="post">
		<input type="hidden" name="operate" value="save"> <input
			type="text" name="netTypeName"><br> <input type="submit"
			value="添加" />
	</form>
	<span>${msg}</span>
</body>
</html>