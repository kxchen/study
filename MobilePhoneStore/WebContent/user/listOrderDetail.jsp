<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">×</button>
	<h4 id="myModalLabel">订单详情</h4>
</div>

<span>订单编号:${orderInfo.orderId} &nbsp总价:￥${orderInfo.totalPrice}&nbsp
	订单日期:${orderInfo.submitTime}</span>


<table class="table table-hover table-bordered ">
	<tr>
		<th width="80" align="left">序号</th>
		<th width="80" align="left">图片</th>
		<th width="120" align="left">品牌</th>
		<th width="120" align="left">型号</th>
		<th width="80" align="left">数量</th>
		<th width="120" align="left">总价</th>
	</tr>

	<c:forEach items="${orderDetailList }" var="orderDetail"
		varStatus="index">
		<tr>
			<td>${index.count}</td>

			<td><img id="photo" width="20" height="34"
				src="<c:if test="${!empty orderDetail.mobilePhone.imgPath }">
													<c:url value="/upload/${orderDetail.mobilePhone.imgPath}"></c:url>
												</c:if>
												<c:if test="${empty orderDetail.mobilePhone.imgPath }">
												images/default.jpg
												</c:if>"></td>

			<td>${orderDetail.mobilePhone.brand.brandName}</td>
			<td>${orderDetail.mobilePhone.model}</td>
			<td>${orderDetail.amount}</td>
			<td>￥${orderDetail.amount * orderDetail.buyPrice}</td>
		</tr>
	</c:forEach>
</table>
<div class="modal-body"></div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭
	</button>
</div>