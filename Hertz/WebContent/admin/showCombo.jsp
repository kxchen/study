<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">×</button>
	<h4 id="myModalLabel">用户详情</h4>
</div>



<table class="table table-hover table-bordered ">
	<tr>
		<td align="left">数量</td>
		<td align="left">套餐名</td>
		<td align="left">品牌</td>
		<td align="left">车系</td>
	</tr>

	<tr>
		<td>${carInfo.count}量/套</td>
		<td>${carInfo.comboName}</td>
		<td>${carInfo.brand}</td>
		<td>${carInfo.model}</td>
	</tr>

	<tr>
		<td align="left">车型</td>
		<td align="left">排挡</td>
		<td align="left">排量</td>
		<td align="left">颜色</td>

	</tr>

	<tr>
		<td>${carInfo.type}</td>
		<td>${carInfo.gears}</td>
		<td>${carInfo.cc}</td>
		<td>￥${carInfo.color}</td>
	
	</tr>	
		
		<tr>
		<td align="left">出租价格</td>
		<td align="left">座位数</td>
		<td align="left">车辆用途</td>

	</tr>

	<tr>
		<td>￥${carInfo.price}元/天</td>
		<td>${carInfo.seats}</td>
		<td>${carInfo.purpose}</td>
	
	</tr>	
		
</table>
<div class="modal-body"></div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭
	</button>
</div>