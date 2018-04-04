<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width" name="viewport">
    <title>修改密码</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/fileinput.min.css">
    <link rel="stylesheet" href="css/font-awesome/css/font-awesome.min.css">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/jquery.twbsPagination.js"></script>
    <script type="text/javascript" src="js/layer/layer.js"></script>
    <script type="text/javascript" src="js/popup.js"></script>
    <script type="text/javascript" src="js/fileinput/fileinput.min.js"></script>
    <script type="text/javascript" src="js/fileinput/zh.js"></script>
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
                    <a href="<c:url value="/CaseAction?operate=listCase"></c:url>"><i class="fa fa-folder-open">&nbsp;&nbsp;共享案例</i></a>
                </li>
                </c:if>
                <c:if test="${fn:contains(sessionScope.listUrl,'addCase')}">
                <li>
                    <a href="<c:url value="/CaseAction?operate=listMyCase"></c:url>"><i class="fa fa-file-text"></i>&nbsp;&nbsp;我的案例</a>
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
                    <a href="<c:url value="/RoleAction?operate=systemManage"></c:url>" class="active"><i class="fa fa-gear"></i>&nbsp;&nbsp;系统管理</a>
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
<div class="right-box container-fluid  top-h1">
    <div class="col-xs-6">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                角色管理
            </div>
            <div class="panel-body">
             	<c:if test="${fn:contains(sessionScope.listUrl,'addRole')}">
                <button type="button" class="pull-right btn btn-primary new-user new-user1">
                    <i class="glyphicon glyphicon-plus"></i>&nbsp;新建角色
                </button>
                </c:if>
                <div class="list">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <table class="table-striped share-table" width="100%">
                                <tr style="background: none;">
                                    <th width="20%">序号</th>
                                    <th width="40%">角色</th>
                                    <th width="40%">操作</th>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="table-list" >
                        <ul class="table-ul col-xs-12" id="table-ul">
                         <c:forEach items="${roleList}" var="role" varStatus="idx" >
                            <li>
                                <table>
                               
                                    <tr>
                                        <td  width="20%">${idx.index+1}</td>
                                        <td  width="40%">${role.roleName}</td>
                                        <td  width="40%">
                                        	<c:if test="${fn:contains(sessionScope.listUrl,'updateRole')}">
                                            <button type="button" class="btn btn-primary btn-xs authority" onclick="updateRole('${role.id}')">
                                                <i class="fa fa-edit">&nbsp;编辑</i>
                                            </button>
                                            </c:if>
                                            <c:if test="${fn:contains(sessionScope.listUrl,'deleteRole')}">
                                            <button type="button" class="btn btn-danger btn-xs" onclick="delRole('${role.id}')">
                                                <i class="fa fa-trash-o">&nbsp;删除</i>
                                            </button>
                                            </c:if>
                                        </td>
                                    </tr>
                                    
                                </table>
                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xs-6">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                部门管理
            </div>
            <div class="panel-body">
            	<c:if test="${fn:contains(sessionScope.listUrl,'addDep')}">
                <button type="button" class="pull-right btn btn-primary new-user new-user2">
                    <i class="glyphicon glyphicon-plus"></i>&nbsp;新建部门
                </button>
                </c:if>
                <div class="list">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <table class="table-striped share-table" width="100%">
                                <tr style="background: none;">
                                    <th width="30%">序号</th>
                                    <th width="40%">部门</th>
                                    <th width="30%">操作</th>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="table-list" >
                        <ul class="table-ul col-xs-12" id="table-ul1">
                        <c:forEach items="${departmentList}" var="dep" varStatus="idx" >
                            <li>
                                <table>
                                
                                    <tr>
                                        <td  width="30%">${idx.index+1}</td>
                                        <td  width="40%">${dep.departmentName }</td>
                                        <td  width="30%">
                                        	<c:if test="${fn:contains(sessionScope.listUrl,'updateDep')}">
                                            <button type="button" class="btn btn-primary btn-xs authority" onclick="updateDep('${dep.id}')">
                                                <i class="fa fa-edit">&nbsp;编辑</i>
                                            </button>
                                            </c:if>
                                       		<c:if test="${fn:contains(sessionScope.listUrl,'deleteDep')}">
                                            <button type="button" class="btn btn-danger btn-xs" onclick="deleteDep('${dep.id}')">
                                                <i class="fa fa-trash-o">&nbsp;删除</i>
                                            </button>
                                            </c:if>
                                        </td>
                                    </tr>
                                    
                                </table>
                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<script type="text/javascript">
function updateDep(id) {
	layer.open({
        type: 2,
        title:'',
        skin: 'layui-layer-rim', //加上边框
        area: ['600px', '200px'], //宽高
        content: "<c:url value='/DepartmentAction?operate=updateDep&departmentId="+id+"'></c:url>"
    });
}
function deleteDep(id) {
	$.ajax({
		url:"<c:url value='/DepartmentAction'></c:url>",
		type:'post',
		dataType:'json',
		data:{operate:"deleteDep",departmentId:id},
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
				layer.msg(msg.state, {
					icon: 2,
					time: 1000//时间设置无反应
				});
			}
		}
	})
}
function delRole(id) {
	$.ajax({
				url:"<c:url value='/RoleAction'></c:url>",
				type:'post',
				dataType:'json',
				data:{operate:"deleteRole",roleId:id},
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
						layer.msg(msg.state, {
							icon: 2,
							time: 1000//时间设置无反应
						});
					}
				}
			})
}
//添加角色
    $('.new-user1').on('click', function(){
        layer.open({
            type: 2,
            title:'',
            skin: 'layui-layer-rim', //加上边框
            area: ['800px', '620px'], //宽高
            content: "<c:url value='/RoleAction?operate=addRole'></c:url>"
        });
    });
    function updateRole(id) {
    	layer.open({
            type: 2,
            title:'',
            skin: 'layui-layer-rim', //加上边框
            area: ['800px', '620px'], //宽高
            content: "<c:url value='/RoleAction?operate=updateRole&roleId="+id+"'></c:url>"
        });
	}
    //添加部门
    $('.new-user2').on('click', function(){
        layer.open({
            type: 2,
            title:'',
            skin: 'layui-layer-rim', //加上边框
            area: ['600px', '200px'], //宽高
            content: "<c:url value='/DepartmentAction?operate=addDep'></c:url>"
        });
    });
</script>
</body>
</html>