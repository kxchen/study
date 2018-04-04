<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加手机页面</title>
<script type="text/javascript">
	function upload(){
		window.open('<c:url value='mobilePhone/upload.jsp' />',"","width=450,height=250")
	}
</script>
</head>
<body>
<form action='<c:url value='/MobilePhoneInfoController?operate=save' />' method="post">
	手机图片:
	<input type="hidden" name="imgPath" id="imgPath">
	<img id="photo" width="170" height="300" src="<c:if test="${!empty mobilePhoneInfo.imgPath }">
													<c:url value="/upload/${mobilePhoneInfo.imgPath}"></c:url>
												</c:if>
												<c:if test="${empty mobilePhoneInfo.imgPath }">
												images/default.jpg
												</c:if>">
	<a href="#" onclick="upload();">请上传</a>
	<br/>
	品牌:
	<select name="brandId" title="手机品牌">
		<option value="">==请选择==</option>
		<c:forEach items="${brandList }" var="brand">
		<option value="${brand.brandId }" >${brand.brandName }</option>
		</c:forEach>
	</select>
	<br/>
	网络类型:
	<select name="netTypeId" title="网络类型">
		<option value="">==请选择==</option>
		<c:forEach items="${networkTypeList }" var="networkType">
		<option value="${networkType.netTypeId }" >${networkType.netTypeName }</option>
		</c:forEach>
	</select>
	<br/>
	操作系统:
	<select name="opeSystemId" title="操作系统">
		<option value="">==请选择==</option>
		<c:forEach items="${operatingSystemList }" var="operatingSystem">
		<option value="${operatingSystem.opeSystemId }" >${operatingSystem.opeSystemName }</option>
		</c:forEach>
	</select>
	<br/>
	屏幕尺寸:
	<select name="scrSizeId" title="屏幕尺寸">
		<option value="">==请选择==</option>
		<c:forEach items="${screenSizeList }" var="screenSize">
		<option value="${screenSize.scrSizeId }" >${screenSize.scrSizeName }</option>
		</c:forEach>
	</select>
	<br/>
	型号:<input type="text" name="model"><br/>
	颜色:<input type="text" name="color"><br/>	
	像素:<input type="text" name="pixels"><br/>
	价格:<input type="text" name="price"><br/>
	售价:<input type="text" name="realPrice"><br/>
	RAM:<input type="text" name="ram"><br/>
	ROM:<input type="text" name="rom"><br/>
	简介:
	<textarea name="descipt" style="height: 150px;width: 260px;">
    </textarea><br/>
	
	<input type="submit" value="添加">
</form>
<span>${msg}</span>
</body>
</html>