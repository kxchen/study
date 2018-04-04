<%@page import="com.school.dto.AdminInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/adminStyle.css">
<ul id="nav">
	<li><a href="<%=request.getContextPath()%>/admin/default.jsp">后台首页</a></li>
	<li><a
		href="<%=request.getContextPath()%>/StudentInfoController?operate=list">学籍管理</a></li>
	<li><a
		href="<%=request.getContextPath()%>/AdminInfoController?operate=list">管理员管理</a></li>
	<li><a
		href="<%=request.getContextPath()%>/adminInfo/changePsw.jsp">修改密码</a></li>
	<li><a
		href="<%=request.getContextPath()%>/AdminInfoController?operate=loginOut">退出</a></li>
	<h5><%=(AdminInfo) session.getAttribute("adminInfo") == null ? ""
					: ((AdminInfo) session.getAttribute("adminInfo")).getAccount()%></h5>
</ul>
