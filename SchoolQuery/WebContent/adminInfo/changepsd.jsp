<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>密码修改</title>
<link rel="stylesheet" type="text/css" href="css/main.css" ></link>
<script type="text/javascript">
function submitform() {
	if(stu.oldpassword.value==null || stu.oldpassword.value==""){
		alert("您旧密码不能为空");
		return false;
	}
	if(stu.newpassword.value==null ||stu.newpassword.value==""){
		alert("您新密码不能为空!");
		return false;
	}
	stu.submit();
}
</script>
</head>
<body>
<form action="<%=request.getContextPath()%>/AdminInfo" name="stu" method="post">
<input type="hidden" name="operate" value="changepsd" />
修改账号：<input type="text" name="adminNo"/>
旧密码：<input type="text" name="oldpassword" />
新密码：<input type="text" name="newpassword" />
<input type="button" value="修改" onclick="submitform();" class="font" />
</form>
<span><%=request.getAttribute("msg")==null?"":request.getAttribute("msg").toString() %></span>
</body>
</html>
