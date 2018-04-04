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
	<h3>屏幕尺寸</h3>
	<c:forEach items="${screenSizeList}" var="screenSize">
		<form action="<c:url value="/ScreenSizeController"></c:url>"
			method="post">
			<input type="hidden" name="operate" value="update"> <input
				type="hidden" name="scrSizeId" value="${screenSize.scrSizeId}">
			<input type="text" name="scrSizeName"
				value="${screenSize.scrSizeName}"> <input type="submit"
				value="更新" />
		</form>
		<a
			href="<c:url value="/ScreenSizeController?operate=delete&scrSizeId=${screenSize.scrSizeId}"></c:url>">删除</a>
	</c:forEach>
	<form action="<c:url value="/ScreenSizeController"></c:url>"
		method="post">
		<input type="hidden" name="operate" value="save"> <input
			type="text" name="scrSizeName"><br> <input type="submit"
			value="添加" />
	</form>
	<span>${msg}</span>
</body>
</html>