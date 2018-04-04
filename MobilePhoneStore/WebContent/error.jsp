<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误页</title>
</head>
<body>
<h3>对不起程序出错</h3>
<h4><a href="<c:url value="MobilePhoneInfoController?operate=hotSale"></c:url>">点此回到首页</a></h4>
<a href="<c:url value="MobilePhoneInfoController?operate=hotSale"></c:url>">点击</a>
</body>
</html>