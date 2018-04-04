<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>购物车</title>
    <link rel="stylesheet" type="text/css" href="css/css.css">
	<script src="js/mui.js"></script>
    <script src="js/jQuery.js"></script>
    <script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
    <script src="js/common.js"></script>
    
    
   <script type="text/javascript">
   
   $(function() {
   	showTotal();//计算总计	
   	yuFu();
   	/*
	给全选添加click事件
	*/
	$("#selectAll").click(function() {
		/*
		1. 获取全选的状态
		*/
		var bool = $("#selectAll").attr("checked");
		/*
		2. 让所有条目的复选框与全选的状态同步
		*/
		setCheckBox(bool);
		/*
		3. 让结算按钮与全选同步
		*/
		setJieSuan(bool);
		/*
		4. 重新计算总计
		*/
		showTotal();
		yuFu();
   });
   
	/*
	给所有条目的复选框添加click事件
	*/
	$(":checkbox[name=checkboxBtn]").click(function() {
		var all = $(":checkbox[name=checkboxBtn]").length;//所有条目的个数
		var select = $(":checkbox[name=checkboxBtn][checked=true]").length;//获取所有被选择条目的个数

		if(all == select) {//全都选中了
			$("#selectAll").attr("checked", true);//勾选全选复选框
			//setJieSuan(true);//让结算按钮有效
		} else if(select == 0) {//谁都没有选中
			$("#selectAll").attr("checked", false);//取消全选
		//	setJieSuan(false);//让结算失效
		} else {
			$("#selectAll").attr("checked", false);//取消全选
		}
		showTotal();//重新计算总计
		yuFu();
		setDisabled();
	});
});
   
   
   function setCheckBox(bool) {
		$(":checkbox[name=checkboxBtn]").attr("checked", bool);
		showTotal();
		yuFu();
		setDisabled();

	}
   
   function showTotal() {
		var total = 0;
		/*
		1. 获取所有的被勾选的条目复选框！循环遍历之
		*/
		$(":checkbox[name=checkboxBtn][checked=true]").each(function() {
			//2. 获取复选框的值，即其他元素的前缀
			var id = $(this).val();
			//3. 再通过前缀找到小计元素，获取其文本
			var text = $("#" + id + "Subtotal").text();
			//4. 累加计算
			total += Number(text);
		});
		// 5. 把总计显示在总计元素上
		$("#total").html(total);
	}  
   function yuFu() {
		var total = 0;
		/*
		1. 获取所有的被勾选的条目复选框！循环遍历之
		*/
		$(":checkbox[name=checkboxBtn][checked=true]").each(function() {
			//2. 获取复选框的值，即其他元素的前缀
			var id = $(this).val();
			//3. 再通过前缀找到小计元素，获取其文本
			var text = $("#" + id + "yufu").text();
			//4. 累加计算
			total += Number(text);
		});
		// 5. 把总计显示在总计元素上
		$("#yufu").html(total);
	}  
   
   function setDisabled() {
		var select = $(":checkbox[name=checkboxBtn][checked=true]").length;
		if(select == 0){
			document.getElementById("btn").setAttribute('disabled','disabled');
		}else{
			document.getElementById("btn").removeAttribute('disabled');
		}
	}

 function buys() {
	// 1. 获取所有被选中条目的复选框
	// 2. 创建一数组，把所有被选中的复选框的值添加到数组中
	// 3. 指定location为CartItemServlet，参数method=batchDelete，参数cartItemIds=数组的toString()
	var shopCarIdList = new Array();
	$(":checkbox[name=checkboxBtn][checked=true]").each(function() {
		shopCarIdList.push($(this).val());//把复选框的值添加到数组中
	});
	location = "/Hertz/UserAction?operate=buys&shopCarIds=" + shopCarIdList;
} 
 function dele() {
		// 1. 获取所有被选中条目的复选框
		// 2. 创建一数组，把所有被选中的复选框的值添加到数组中
		// 3. 指定location为CartItemServlet，参数method=batchDelete，参数cartItemIds=数组的toString()
		var shopCarIdList = new Array();
		$(":checkbox[name=checkboxBtn][checked=true]").each(function() {
			shopCarIdList.push($(this).val());//把复选框的值添加到数组中
		});
		location = "/Hertz/UserAction?operate=deleShopCar&shopCarIds=" + shopCarIdList;
	}  
 
 
/*
 function selAll() {
		var selectIts = document.getElementsByName("checkboxBtn");
		for (var i = 0; i < selectIts.length; i++) {
			var selectIt1 = selectIts[i];
			selectIt1.checked = true;
		}
	}

	function selNo() {
		var selectIts = document.getElementsByName("checkboxBtn");
		for (var i = 0; i < selectIts.length; i++) {
			var selectIt1 = selectIts[i];
			selectIt1.checked = false;
		}
	}
	function selAllNo() {
		var box = document.getElementById("selectAll");
		if (box.checked == true) {
			selAll();
			showTotal();
		} else {
			selNo();
			showTotal();
		}
	}
  */
 
</script> 
    
    
</head>
<body class="body">
<!--头部 开始-->
<div class="header">
	<div class="header_main">
        <a href="javascript:history.go(-1)" class="a_fh" class="a_fh">&lt返回</a>
        <a href="#" class="a_right" onclick="dele()">删除</a>
        <h3>购物车</h3>
    </div>
</div>
<!--头部 结束-->

<!--主体内容 开始-->
<i>${msg}</i>
<c:forEach items="${shopCarInfoList}" var="shopCarInfo">
<ul class="gw">
    <li>
		<label class="rad2">
        	<input type="checkbox" name="checkboxBtn" value="${shopCarInfo.shopCarId}"  />
            <img class="img" src="<c:if test="${!empty shopCarInfo.carInfo.carImg }">
					<c:url value="/upload/${shopCarInfo.carInfo.carImg}"></c:url>
					</c:if>
					<c:if test="${empty shopCarInfo.carInfo.carImg }">
						img/01.jpg
					</c:if>
					" >
       	</label>
       	<div class="left">
          <i>${shopCarInfo.carInfo.brand}${shopCarInfo.carInfo.model}</i><br>
          乘坐${shopCarInfo.carInfo.seats}<br>
      ${shopCarInfo.carInfo.type}/${shopCarInfo.carInfo.cc}${shopCarInfo.carInfo.gears}<br>
         起：${shopCarInfo.takeTime}<br>
           止 ：${shopCarInfo.retTime}
		</div>
       	<p>
        	<i> <span>${shopCarInfo.carInfo.price}</span></i><span>元/天</span><br>
        		<span>共：${shopCarInfo.amount}天</span><br>
        		<span>小计：</span><i id="${shopCarInfo.shopCarId}Subtotal"> <span>${shopCarInfo.carInfo.price*shopCarInfo.amount}</span></i><br>
        	<span>预付：</span><i id="${shopCarInfo.shopCarId}yufu">${(shopCarInfo.carInfo.price *shopCarInfo.amount)*(shopCarInfo.carInfo.paidPer/100)}</i><br>
       </p> 
	</li>
</ul>
</c:forEach>
<!--主体内容 结束-->

<!--尾部 开始-->
<div class="footer_menu6">
     <div class="left">
      <label class="rad1"><input type="checkbox" name="checkbox" id="selectAll" /><span >全选</span></label>
      合计：￥<i id="total"></i> 预付：￥<i id=yufu> </i>
   </div>
   <input type="button" disabled="disabled" class="btn btn_yy" value="预约" id="btn">
</div>
<!--尾部 结束-->
<div class="zhe"></div>
<div class="gw_tan">
	<h3>请选择</h3>
    <p><label class="rad1"><input type="radio" name="checkboxBtn" value="quan"/>全款</label></p>
    <p><label class="rad1"><input type="radio" name="checkboxBtn" value="dingjin"/>付定金</label></p>
    <p><label class="rad1"><input type="radio" name="checkboxBtn"/>租车劵（98元）</label></p>
    <input type="button" class="btn btn_ok" value="确定" onclick="buys()">
</div>

</body>
</html>