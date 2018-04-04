<%@page import="com.school.dto.StudentInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回收站</title>
</head>
<body>

	<%
		ArrayList<StudentInfo> studentInfoList = (ArrayList<StudentInfo>) request.getAttribute("studentInfoList");
	%>
	<h2>回收站</h2>
	<form action="<%=request.getContextPath()%>/StudentInfoController"
		method="post">
		<input type="hidden" name="operate" value="search" />
	
	<table>
		<tr>
			<th>学号</th>
			<th>姓名</th>
			<th>身份证号</th>
			<th>性别</th>
			<th>专业</th>
			<th>班级</th>
		</tr>
			<%
				for (int i=0;i<studentInfoList.size();i++) {
				
					StudentInfo studentInfo = studentInfoList.get(i);
			%>
		<tr>
			<td><%=studentInfo.getStuNum() %></td>
			  <td><%=studentInfo.getStuName() %></td>
			  <td><%=studentInfo.getStuId() %></td>
			  <td><%=studentInfo.getStuSex() %></td>
			  <td><%=studentInfo.getStuClass() %></td>
			  <td><%=studentInfo.getStuProfession() %></td>
			<td><a href="<%=request.getContextPath()%>/studentInfoController?operate=restore&stuNum=<%=studentInfo.getStuNum() %>">还原</a></td>
			  <td><a href="<%=request.getContextPath()%>/studentInfoController?operate=delete&stuNum=<%=studentInfo.getStuNum() %>">删除</a></td>
		</tr>
		<%
			}
		%>
		<tr>
			
			
		</tr>
	</table>
	<span><%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg").toString()%></span>

</body>
</html>