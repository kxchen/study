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
    <script type="text/javascript" src="js/jquery.placeholder.min.js"></script>
    <script type="text/javascript" src="js/layer/layer.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="js/fileinput/fileinput.min.js"></script>
    <script type="text/javascript" src="js/fileinput/zh.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
    <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <script type="text/javascript">
		$(function () {
		    // Invoke the plugin
		    $('input, textarea').placeholder();
		});
	</script>
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
                    <a href="<c:url value="/RoleAction?operate=systemManage"></c:url>"><i class="fa fa-gear"></i>&nbsp;&nbsp;系统管理</a>
                </li>
                </c:if>
                <c:if test="${fn:contains(sessionScope.listUrl,'changePassword')}">
                <li>
                    <a href="<c:url value="/UserAction?operate=changePassword"></c:url>" class="active"><i class="fa fa-key"></i>&nbsp;&nbsp;修改密码</a>
                </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<div class="right-box container-fluid">
    <div class="main-content col-xs-10 col-xs-offset-1 top-h1">
        <div class="col-xs-10 col-xs-offset-1 ">
            <div class="upload-table" style="margin-top: 40px;">
                <h2 class="text-left">修改密码</h2>
                <hr>
            </div>
            <form class="form-horizontal col-xs-12" role="form" method="post">
                <div class="form-group updatePwd">
                    <label for="firstpwd" class="col-xs-3 control-label">旧 密 码</label>
                    <div class="col-xs-8">
                        <input type="password" class="form-control" id="firstpwd" placeholder="请输入旧密码">
                    </div>
                </div>
                <div class="form-group updatePwd">
                    <label for="lastpwd" class="col-xs-3 control-label">新 密 码</label>
                    <div class="col-xs-8">
                        <input type="password" class="form-control" id="lastpwd" placeholder="请输入新密码">
                    </div>
                </div>
                 <div class="form-group updatePwd">
                    <label for="lastpwd1" class="col-xs-3 control-label">确认密码</label>
                    <div class="col-xs-8">
                        <input type="password" class="form-control" id="lastpwd1" placeholder="请再次输入密码">
                    </div>
                </div>
                <div class="updateBtn" data-toggle="buttons" style="margin-bottom: 40px">
                    <hr>
                    <label class="center-block">
                        <input type="button"class="btn btn-primary" onclick="doChangePassword()" value="确   定"  >
                        <input type="reset" id="reset" class="btn btn-default" value="重   置">
                    </label>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">

function doChangePassword() {
	 var old_password=$("#firstpwd").val();
	 if(old_password.length==0){  
		 layer.msg("请输入旧密码", {
				icon: 2,
				time: 1000//时间设置无反应
			});
		 return
	}  
	 var new_password=$("#lastpwd").val();
	 var new_password1=$("#lastpwd1").val();
	 if(new_password.length==0){  
		 layer.msg("请输入新密码", {
				icon: 2,
				time: 1000//时间设置无反应
			});
		 return
	}  
	 if(new_password!=new_password1){  
		 layer.msg("两次新密码输入不同", {
				icon: 2,
				time: 1000//时间设置无反应
			});
		 return
	} 
	 $.ajax({
			url:"<c:url value='/UserAction'></c:url>",
			type:'post',
			dataType:'json',
			data:{operate:"doChangePassword",oldPassword:old_password,newPassword:new_password},
			success:function(msg){
				if(msg.state=="success"){
					layer.msg('修改成功', {
						icon: 1,
						time: 1000//时间设置无反应
					});
					setTimeout(function () {
						 window.location.href = "<c:url value='/UserAction?operate=quit'></c:url>";
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


window.onload=function () {
    var tijao=document.getElementById('tijiao');
    var reset=document.getElementById('reset');
    var newpwd1=document.getElementById("lastpwd");
    var lastpwd1=document.getElementById("lastpwd1");
    tijiao.onclick=function () {
        var lastpwd=document.getElementById("lastpwd").value;
        var lastpwd1=document.getElementById("lastpwd1").value;
        if (lastpwd!=lastpwd1){
            alert('两次密码不同，请重新输入！');
            return false;
        }
    };
    reset.onclick=function () {
        newpwd1.value='';
        lastpwd1.value='';
    }
}
</script>
</body>
</html>