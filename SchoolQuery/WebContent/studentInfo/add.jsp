<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学籍信息添加</title>
</head>
<body>
<%@include file="/admin/nav.jsp"%>
<h1>学籍信息添加</h1>
	<form action="<%=request.getContextPath()%>/StudentInfoController"
		method="post">
		<input type="hidden" name="operate" value="save">
		身份证号：<input
			type="text" name="studentId"><br> 
			学号：<input
			type="text" name="studentNum"><br> 
		 姓名：<input
			type="text" name="studentName"><br> 
			
			性别：<input
			type="text" name="sex"><br> 
			专业：<input
			type="text" name="major"><br> 
			班级：<input
			type="text" name="classes"><br>
			<input
			type="submit" value="添加" />
	</form>
	<span><%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg").toString()%></span>
</body>
</html>