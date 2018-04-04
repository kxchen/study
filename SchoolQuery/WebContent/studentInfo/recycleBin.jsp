
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
<%@include file="/admin/nav.jsp"%>
	<%
		ArrayList<StudentInfo> studentInfoList = (ArrayList<StudentInfo>) request.getAttribute("studentInfoList");
	%>
	<h2>回收站</h2>
	<form action="<%=request.getContextPath()%>/StudentInfoController"
		method="post">
		<input type="hidden" name="operate" value="search" /> <input
			type="text" name="account" placeholder="姓名关键字"> <input
			type="submit" name="submit" value="搜索" />
	</form>
	<span><%=request.getAttribute("searchMsg") == null ? "" : request.getAttribute("searchMsg").toString()%></span>
	<table>
		<tr>
			<td>序号</td>
			<td>身份证号</td>
			<td>学号</td>
			<td>姓名</td>
			<td>性别</td>
			<td>专业</td>
			<td>班级</td>
		</tr>
		<%
			for (int i = 0; i < studentInfoList.size(); i++) {
				StudentInfo studentInfo = studentInfoList.get(i);
		%>
		<tr>
			<td><%=i + 1%></td>
			<td><%=studentInfo.getId()%></td>
			<td><%=studentInfo.getStudentNum()%></td>
			<td><%=studentInfo.getStudentName()%></td>
			<td><%=studentInfo.getSex()%></td>
			<td><%=studentInfo.getMajor()%></td>
			<td><%=studentInfo.getClasses()%></td>
			<td><a
				href="<%=request.getContextPath()%>/StudentInfoController?operate=show&studentId=<%=studentInfo.getId()%>">查看</a>
				<a
				href="<%=request.getContextPath()%>/StudentInfoController?operate=recover&studentId=<%=studentInfo.getId()%>">还原</a>
				<a
				href="<%=request.getContextPath()%>/StudentInfoController?operate=delete&studentId=<%=studentInfo.getId()%>">彻底删除</a>
		</tr>
		<%
			}
		%>
		<tr>
			<td>
			<td><a
				href="<%=request.getContextPath()%>/StudentInfoController?operate=clearRecycleBin">清空回收站</a></td>
		</tr>
	</table>
	<span><%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg").toString()%></span>

</body>
</html>