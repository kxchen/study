<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">×</button>
	<h4 id="myModalLabel">用户详情</h4>
</div>

<span>订单编号:${orderDetail.orderInfo.orderId}<br>
	订单日期:${orderDetail.orderInfo.buyTime}</span>


<table class="table table-hover table-bordered ">
	<tr>
		<td  align="left">联系人</td>
		<td align="left">购买时价格</td>
		<td align="left">提车时间</td>
	</tr>

	<tr>
		<td>${orderDetail.orderInfo.contacts}</td>
		<td>${orderDetail.reaPrice}</td>
		<td>${orderDetail.takeTime}</td>
	</tr>

	<tr>
		<td align="left">还车时间</td>
		<td align="left">
		<c:if test="${orderDetail.orderInfo.isPay==1&&orderDetail.isSend==0&&orderDetail.isAbolish==0}" >
		未发车
		</c:if>
		<c:if test="${orderDetail.isSend==1&&orderDetail.isReturn==0}">
		租赁中
		</c:if>
		<c:if test="${orderDetail.isReturn==1&&orderDetail.isAbolish==0}">
		已完成
		</c:if>
		<c:if test="${orderDetail.isAbolish==2}">
		已取消
		</c:if>
		<c:if test="${orderDetail.isAbolish==1}">
		申请取消
		</c:if>
		
		</td>
		<td align="left">已付金额</td>
	</tr>

	<tr>
		<td>${orderDetail.retTime}</td>
		
		<td>￥${orderDetail. paid}</td>
	</tr>	
		
		
		
</table>
<div class="modal-body"></div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭
	</button>
</div>