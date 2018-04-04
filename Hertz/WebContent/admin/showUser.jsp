<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">×</button>
	<h4 id="myModalLabel">用户详情</h4>
</div>




<table class="table table-hover table-bordered ">
	<tr>
		<td  align="left">手机号码</td>
		<td  align="left">姓名</td>
		<td  align="left">性别</td>
		<td  align="left">身份证号码</td>
	</tr>

	<tr>
		<td>${userInfo.phone}</td>
		<td>${userInfo.userName}</td>
		<td>${userInfo.sex}</td>
		<td>${userInfo.idNum}</td>
	</tr>

	<tr>
		<td  align="left">是否认证</td>
		<td align="left">余额</td>
		<td  align="left">优惠卷</td>
	</tr>

	<tr>
		<td>
		<c:if test="${userInfo.isCer==0}">
		未认证
		</c:if>
		<c:if test="${userInfo.isCer==1}">
		已认证
		</c:if>
		</td>
		<td>￥${userInfo.remBal}</td>
		<td>￥${userInfo.disCou}</td>
	</tr>	
		
		
		
</table>
<div class="modal-body"></div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭
	</button>
</div>