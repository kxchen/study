<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/adminStyle.css">
<title>滁州职业技术学院学籍查询系统</title>
</head>
<body>
	<ul id="nav">
		<li><a href="#">欢迎进入滁州职业技术学院学籍查询系统</a></li>


	</ul>
	<form action="<%=request.getContextPath()%>/StudentInfoController"
		method="post">
		<input type="hidden" name="operate" value="groupSearch" /> 请输入身份证号：<input
			type="text" name="studentId" placeholder="身份证号码"> 学号：<input
			type="text" name="studentNum" placeholder="学号"> <input
			type="submit" name="submit" value="搜索" />
	</form>
	<table>
		<tr>
			<td><a href="<%=request.getContextPath()%>/login.jsp">后台登陆</a></td>
		</tr>
	</table>
	<span><%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg").toString()%></span>
</body>
</html>