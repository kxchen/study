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

	<a href="<c:url value="/BrandController?operate=list"></c:url>">品牌</a>
	<a href="<c:url value="/NetworkTypeController?operate=list"></c:url>">网络类型</a>
	<a
		href="<c:url value="/OperatingSystemController?operate=list"></c:url>">操作系统</a>
	<a href="<c:url value="/ScreenSizeController?operate=list"></c:url>">屏幕尺寸</a>
	<span>${msg}</span>
</body>
</html>