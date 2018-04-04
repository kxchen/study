<%@page import="com.school.dto.StudentInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改页</title>
</head>
<body>
<%@include file="/admin/nav.jsp"%>
	<h1>修改页</h1>
	<%
		StudentInfo studentInfo = (StudentInfo) request.getAttribute("studentInfo");
	%>
	<form action="<%=request.getContextPath()%>/StudentInfoController"
		method="post">
		<input type="hidden" name="operate" value="update" /> 
		身份证号码:<input
			type="text" name="studentId" value="<%=studentInfo.getId()%>"
			readonly="readonly" /> 
			学号：<input type="text"
			name="studentNum" value="<%=studentInfo.getId()%>" /> 
			姓名:<input
			type="text" name="studentName"
			value="<%=studentInfo.getStudentName()%>" /> 
			性别:<input type="text"
			name="sex" value="<%=studentInfo.getSex()%>" /> 
			专业:<input
			type="text" name="major" value="<%=studentInfo.getMajor()%>" /> 
			班级:<input
			type="text" name="classes" value="<%=studentInfo.getClasses()%>" /><input
			type="submit" name="submit" value="更新" /> <input type="reset"
			name="reset" value="重置" />
	</form>
	<span><%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg").toString()%></span>
</body>
</html>