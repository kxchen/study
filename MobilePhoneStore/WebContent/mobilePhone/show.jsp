<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>图片</td>
			<td>品牌</td>
			<td>型号</td>
			<td>网络类型</td>
			<td>操作系统</td>
			<td>屏幕尺寸</td>
			<td>价格</td>
			<td>颜色</td>
			<td>实际售价</td>
			<td>描述</td>
			<td>像素</td>
			<td>ram</td>
			<td>rom</td>
			<td>上架时间</td>
		</tr>
		<tr>
			<td><img id="photo" width="20" height="34"
				src="<c:if test="${!empty mobilePhoneInfo.imgPath }">
													<c:url value="/upload/${mobilePhoneInfo.imgPath}"></c:url>
												</c:if>
												<c:if test="${empty mobilePhoneInfo.imgPath }">
												images/default.jpg
												</c:if>"></td>
			<td>${mobilePhoneInfo.brand.brandName}</td>
			<td>${mobilePhoneInfo.model}</td>
			<td>${mobilePhoneInfo.networkType.netTypeName}</td>
			<td>${mobilePhoneInfo.operatingSystem.opeSystemName}</td>
			<td>${mobilePhoneInfo.screenSize.scrSizeName}</td>
			<td>${mobilePhoneInfo.price}</td>
			<td>${mobilePhoneInfo.color}</td>
			<td>${mobilePhoneInfo.realPrice}</td>
			<td>${mobilePhoneInfo.descipt}</td>
			<td>${mobilePhoneInfo.pixels}</td>
			<td>${mobilePhoneInfo.ram}</td>
			<td>${mobilePhoneInfo.rom}</td>
			<td>${mobilePhoneInfo.regDate}</td>
			<td><a
				href="
	<c:url value="/MobilePhoneInfoController?operate=edit&mobPhoneId=${mobilePhoneInfo.mobPhoneId}"></c:url>
		">编辑
			</a> <a
				href="<c:url value="/MobilePhoneInfoController?operate=delete&mobPhoneId=${mobilePhoneInfo.mobPhoneId}"></c:url>">删除</a>
			</td>
		</tr>

	</table>

</body>
</html>