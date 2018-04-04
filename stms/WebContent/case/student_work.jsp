<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible" >
    <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width" name="viewport">
    <title>案例信息</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/font-awesome/css/font-awesome.min.css">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/jquery.twbsPagination.js"></script>
    <script type="text/javascript" src="js/layer/layer.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
    <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation" id="nav">
    <div class="navbar-header">
        <a class="navbar-brand nav-title" href="#">软件测试案例库管理系统</a>
    </div>
    <div class="pull-right">
           <p class="navbar-text">当前用户：
    	【${sessionScope.userInfo.role.roleName}】${sessionScope.userInfo.userName}【${sessionScope.userInfo.department.departmentName}】
	  </p>
        <a href="<c:url value="/UserAction?operate=quit"></c:url>" class="navbar-brand signOut" title="退出登录">
            <i class="fa fa-power-off"></i>&nbsp;退出登录
        </a>
        </div>
</nav>
<div class="left-box">
    <div class="menu">
        <div class="logo">
            <img src="images/pdsts.png" alt="">
        </div>
        <div class="menu-content">
           <ul>
            	<c:if test="${fn:contains(sessionScope.listUrl,'listCase')}">
                <li>
                    <a href="<c:url value="/CaseAction?operate=listCase"></c:url>" ><i class="fa fa-folder-open">&nbsp;&nbsp;共享案例</i></a>
                </li>
                </c:if>
                <c:if test="${fn:contains(sessionScope.listUrl,'addCase')}">
                <li>
                    <a href="<c:url value="/CaseAction?operate=listMyCase"></c:url>" class="active"><i class="fa fa-file-text"></i>&nbsp;&nbsp;我的案例</a>
                </li>
                <li>
                    <a href="<c:url value="/CaseAction?operate=addCase"></c:url>"><i class="fa fa-upload"></i>&nbsp;&nbsp;上传案例</a>
                </li>
                 </c:if>
                 <c:if test="${fn:contains(sessionScope.listUrl,'addHomework')}">
                <li>
                    <a href="<c:url value="/HomeworkAction?operate=listMyHomework"></c:url>"><i class="fa fa-repeat"></i>&nbsp;&nbsp;我的作业</a>
                </li>
                </c:if>
                <c:if test="${fn:contains(sessionScope.listUrl,'addUser')}">
                <li>
                    <a href="<c:url value="/UserAction?operate=listUser"></c:url>" ><i class="fa fa-user"></i>&nbsp;&nbsp;用户管理</a>
                </li>
                </c:if>
                 <c:if test="${fn:contains(sessionScope.listUrl,'addRole')}">
                <li>
                    <a href="<c:url value="/RoleAction?operate=systemManage"></c:url>"><i class="fa fa-gear"></i>&nbsp;&nbsp;系统管理</a>
                </li>
                </c:if>
                <c:if test="${fn:contains(sessionScope.listUrl,'changePassword')}">
                <li>
                    <a href="<c:url value="/UserAction?operate=changePassword"></c:url>"><i class="fa fa-key"></i>&nbsp;&nbsp;修改密码</a>
                </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<div class="right-box container-fluid top-h1">
    <div class="row">
        <div class="col-xs-6 information">
                        <div class="panel panel-default">
                <div class="panel-heading text-center">
                    案例信息
                </div>
                <div class="panel-body">
                    <ul class="info-ul">
                        <li>
                            案例名称：<span>${caseInfo.caseName}</span>
                        </li>
                        <li>
                            备注：<span>${caseInfo.remark}</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-xs-6 information">
<div class="panel panel-default">
                <div class="panel-heading text-center">
                    附件下载
                </div>
                <div class="panel-body right-info">
                    <table class="table text-center file-table " width="100%">
                        <thead>
                            <tr>
                                <th class="th1">序号</th>
                                <th>文件名称</th>
                                <th class="th1">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                         <c:forEach items="${appendixList}" var="appendix" varStatus="idx" >
                            <tr id="${appendix.id}del">
                                <td>${idx.index+1}</td>
                                <td class="quantity">${appendix.appendixName}</td>
                                <td>
                                	<button type="button" class="btn btn-info btn-xs" onclick="dowl('${appendix.id}')">
                        				<i class="fa fa-download"></i>
                   					</button>
                                    <button type="button" class="btn btn-danger btn-xs" onclick="delAppendix('${appendix.id}')">
                                        <i class="glyphicon glyphicon-trash"></i>
                                    </button>
                                </td>
                            </tr>
                          </c:forEach>  
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div style="margin: 5px 0;">
    	<p>学生作业：</p>
    </div>
    <div class="list" style="margin-top: 0">
        <div class="panel panel-default">
            <div class="panel-heading">
                <table class="table-striped share-table" width="100%">
                    <tr style="background: none;">
                        <th width="5%">序号</th>
                        <th width="40%">作业名称</th>
                        <th width="10%">上传者</th>
                        <th width="20%">上传时间</th>
                        <th width="5%">附件</th>
                        <th width="20%">操作</th>
                    </tr>
                </table>
            </div>
        </div>
        <div class="table-list" >
            <ul class="table-ul col-xs-12">
            <c:forEach items="${homeworkList}" var="homework" varStatus="idx" >
                <li>
                    <table>
                        <tr>
                            <td  width="5%">${idx.index+1}</td>
                            <td  width="40%">${homework.homeworkName}</td>
                            <td  width="10%">${homework.user.userName}</td>
                            <td  width="20%">${homework.createDate}</td>
                            <td  width="5%">
                               <a href="#" class="tooltip-test annex" data-toggle="tooltip" title="下载附件" onclick="dowl('${homework.appendix.id}')">
                                   <i class="fa fa-file-code-o"></i>
                               </a>
                            </td>
                            <td  width="20%">
                                <c:if test="${fn:contains(sessionScope.listUrl,'updateHomework')}">
                                <button type="button" class="btn btn-primary btn-xs details">
                                    <i class="fa fa-edit" onclick="updateHome('${homework.id}')">&nbsp;编辑</i>
                                </button>
                                </c:if> 
                                <c:if test="${fn:contains(sessionScope.listUrl,'deleteHome')}">
                                <button type="button" class="btn btn-danger btn-xs" onclick="deleteHome('${homework.id}')">
                                    <i class="fa fa-trash">&nbsp;删除</i>
                                </button>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                    <div class="remark">
                        <i>${homework.remark}</i>
                    </div>
                </li>
              </c:forEach>
            </ul>
        </div>
        <ul class="pagination pull-right">
	<c:if test="${pageNo > 1 }">
		<li><a href="<c:url value='/HomeworkAction?operate=listHomework&search=${search}&pageNo=${pageNo-1 }'/>">&laquo;</a></li>
	</c:if>
	
	<%------------------------------------ --%>
	  <%-- 页码列表的长度自己定，10个长 --%>
	<c:choose>
	<%-- 第一条：如果总页数<=10，那么页码列表为1 ~ tp --%>
	  <c:when test="${totalPage <= 10 }">
	    <c:set var="begin" value="1"/>
	    <c:set var="end" value="${totalPage}"/>
	  </c:when>
	  <c:otherwise>
	  	<%-- 第二条：按公式计算，让列表的头为当前页-4；列表的尾为当前页+5 --%>
	  	<c:set var="begin" value="${pageNo-4 }"/>
	    <c:set var="end" value="${pageNo+5 }"/>
	    
	    <%-- 第三条：第二条只适合在中间，而两端会出问题。这里处理begin出界！ --%>
	    <%-- 如果begin<1，那么让begin=1，相应end=10 --%>
	    <c:if test="${begin<1 }">
	    	<c:set var="begin" value="1"/>
	    	<c:set var="end" value="10"/>
	    </c:if>
	    <%-- 第四条：处理end出界。如果end>tp，那么让end=tp，相应begin=tp-9 --%>
	    <c:if test="${end>totalPage }">
	    	<c:set var="begin" value="${totalPage-9 }"/>
	    	<c:set var="end" value="${totalPage}"/>
	    </c:if>
	  </c:otherwise>
	</c:choose>
	
	<%-- 循环显示页码列表 --%>
	<c:forEach begin="${begin }" end="${end }" var="i">
	  <c:choose>
	  	<c:when test="${i eq pageNo }"><li class="active"><a href="#" >${i }</a></li></c:when>
	  	<c:otherwise>
	  		<li><a href="<c:url value='/HomeworkAction?operate=listHomework&search=${search}&pageNo=${i}'/>">${i }</a></li>
	  	</c:otherwise>
	  </c:choose>
	</c:forEach>
	
	<%------------------------------------ --%>
	
	<c:if test="${pageNo < totalPage }">　
		<li><a href="<c:url value='/HomeworkAction?operate=listHomework&search=${search}&pageNo=${pageNo+1 }'/>">&raquo;</a></li>
	</c:if>
</ul>　
    </div>
</div>

<script>
function dowl(id) {
	window.location.href="<c:url value='/CaseAction?operate=download&appendixId="+id+"'></c:url>"
}
function deleteHome(id) {
	$.ajax({
		url:"<c:url value='/HomeworkAction'></c:url>",
		type:'post',
		dataType:'json',
		data:{operate:"deleteHomework",homeworkId:id},
		success:function(msg){
			if(msg.state=="success"){
				layer.msg('删除成功', {
					icon: 1,
					time: 1000//时间设置无反应
				});
				setTimeout(function () {
					window.location.reload();
				}, 1000);
			}else{
				layer.msg('删除失败', {
					icon: 2,
					time: 1000//时间设置无反应
				});
			}
		}
	})
}
function updateHome(id) {
	layer.open({
        type: 2,
        title:'',
        skin: 'layui-layer-rim', //加上边框
        area: ['800px', '550px'], //宽高
        content: "<c:url value='/HomeworkAction?operate=updateHomework&homeworkId="+id+"'></c:url>"
    });
}

function delAppendix(id) {
	 $.ajax({
			url:"<c:url value='/CaseAction'></c:url>",
			type:'post',
			dataType:'json',
			data:{operate:"delApp",appendixId:id},
			success:function(msg){
				$("#"+id+"del").remove();
				if(msg.success=="true"){
					layer.msg('删除成功', {
						icon: 1,
						time: 1000//时间设置无反应
					});
				}else{
					layer.msg('删除失败', {
						icon: 2,
						time: 1000//时间设置无反应
					});
				}
			}
		})
}
    $(document).ready(function(){
    	//限制字符个数
    	        $('.quantity').each(function(){
    	            var maxwidth=15;
    	            if($(this).text().length>maxwidth){
    	                $(this).text($(this).text().substring(0,maxwidth));
    	                $(this).html($(this).html()+"…");
    	            }
    	        });
    	    });
</script>
</body>
</html>