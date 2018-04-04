<%@page import="com.school.dto.StudentInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询信息显示</title>
</head>
<body>
<%@include file="/admin/nav.jsp"%>
	<h1>查询信息显示</h1>
	<%
		StudentInfo studentInfo = (StudentInfo) request.getAttribute("studentInfo");
	%>
	<table>
		<tr>
			<td>身份证号</td>
			<td>学号</td>
			<td>姓名</td>
			<td>性别</td>
			<td>专业</td>
			<td>班级</td>
		</tr>
		<tr>
			<td><%=studentInfo.getId()%></td>
			<td><%=studentInfo.getStudentNum()%></td>
			<td><%=studentInfo.getStudentName()%></td>
			<td><%=studentInfo.getSex()%></td>
			<td><%=studentInfo.getMajor()%></td>
			<td><%=studentInfo.getClasses()%></td>
		</tr>
		<tr>
		</tr>
	</table>
</body>
</html>