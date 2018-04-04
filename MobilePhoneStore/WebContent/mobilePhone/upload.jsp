<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>图片上传</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="description" content="在线手机商城" />
<meta name="keywords" content="在线 手机 商城" />
<meta name="author" content="c-kx@outlook.com" />
</head>

<body>
	${error}
	<form name="tab" method="post" action="<c:url value="/UploadController"/>"
		enctype="multipart/form-data">
		<input type="file" id="uploadfile" name="uploadfile"> <input
			type="submit" value="上传">
	</form>
</body>
<c:if test="${!empty imgPath }">
	<script type="text/javascript">
		window.opener.document.getElementById('imgPath').value = '${imgPath}';
		window.opener.document.getElementById('photo').src = '<c:url value="/upload/${imgPath}"/>';
		window.close();
	</script>
</c:if>
</html>
