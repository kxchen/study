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
	<h3>品牌</h3>
	<c:forEach items="${brandList}" var="brand">
		<form action="<c:url value="/BrandController"></c:url>" method="post">
			<input type="hidden" name="operate" value="update"> <input
				type="hidden" name="brandId" value="${brand.brandId}"> <input
				type="text" name="brandName" value="${brand.brandName}"> <input
				type="submit" value="更新" />
		</form>
		<a
			href="<c:url value="/BrandController?operate=delete&brandId=${brand.brandId}"></c:url>">删除</a>
	</c:forEach>
	<form action="<c:url value="/BrandController"></c:url>" method="post">
		<input type="hidden" name="operate" value="save"> <input
			type="text" name="brandName"><br> <input type="submit" value="添加" />
	</form>
	<span>${msg}</span>
</body>
</html>