<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width" name="viewport">
    <title>编辑用户</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
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

    <div class="col-xs-10 col-xs-offset-1">
        <div id="upload-table" class="upload-table">
            <h2 class="text-left font-size">编辑用户</h2>
            <hr>
        </div>
        <form class="form-horizontal col-xs-12" role="form" method="post" id="addUserForm">
            <div class="form-group updatePwd">
                <label for="name" class="col-xs-3 control-label">姓名：</label>
                <div class="col-xs-8">
                	<input type="hidden" value="${userInfo.id}" id="userId">
                    <input type="text" class="form-control" id="name" name="userName" value="${userInfo.userName }" placeholder="请输入姓名">
                </div>
            </div>
          
            <div class="form-group updatePwd">
                <label for="job" class="col-xs-3 control-label">角色：</label>
                <div class="col-xs-8">
                    <select class="form-control" id="job" name="roleId" >
                    <c:forEach items="${roleList }" var="role">
						<option value="${role.id}">${role.roleName}</option>
					</c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group updatePwd">
                <label for="xibu" class="col-xs-3 control-label">系部：</label>
                <div class="col-xs-8">
                    <select class="form-control" id="xibu" name="departmentId" >
                    <c:forEach items="${departmentList }" var="dep">
						<option value="${dep.id}">${dep.departmentName}</option>
					</c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group updatePwd">
                <label for="account" class="col-xs-3 control-label">账号：</label>
               
                <div class="col-xs-8">
                    <input type="text" class="form-control" id="account"  name="phone" disabled="disabled" value="${userInfo.phone }" placeholder="请输入手机号">
                </div>
            </div>
            <div class="form-group updatePwd">
                <label for="password" class="col-xs-3 control-label">状态：</label>
                <div class="col-xs-8">
                  <select class="form-control" id="state" name="state">
                        <option value="0">启用</option>
                        <option value="1">禁用</option>
                    </select>
                </div>
            </div>
            <div class="updateBtn" data-toggle="buttons">
                <hr>
                <label class="pull-right">
                    <button type="button" class="btn btn-primary" onclick="doUpdateUser()">确   定</button>
                </label>
            </div>
        </form>
    </div>

<script type="text/javascript">

function doUpdateUser() {
	
	 var user_name=$("#name").val();
	 if(user_name.length==0){  
		 layer.msg("请输入用户名", {
				icon: 2,
				time: 1000//时间设置无反应
			});
		 return
	}  
	 var phone=$("#account").val();
	 if(phone.length==0){  
		 layer.msg("请输入手机号", {
				icon: 2,
				time: 1000//时间设置无反应
			});
		 return
	}  
	 var myReg =/^1[34578]\d{9}$/; //手机号正则
	 if(!myReg.test(phone)){
		 layer.msg("手机号格式不正确", {
				icon: 2,
				time: 1000//时间设置无反应
			});
		 return
     }
	 var state=$('#state option:selected').val();
	 var userId=$("#userId").val();
	 var role_id=$('#job option:selected').val();
	 var department_id=$('#xibu option:selected').val();
	 $.ajax({
			url:"<c:url value='/UserAction'></c:url>",
			type:'post',
			dataType:'json',
			data:{operate:"doUpdateUser",userName:user_name,phone:phone,state:state,roleId:role_id,departmentId:department_id,userId:userId},
			success:function(msg){
				if(msg.state=="success"){
					layer.msg('修改成功', {
						icon: 1,
						time: 1000//时间设置无反应
					});
					setTimeout(function () {
						var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
						 parent.layer.close(index);
						//		 跳转到首页
	                   parent.location ="<c:url value='/UserAction?operate=listUser'></c:url>";
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

$(document).ready(function(){ 
	var dep=${userInfo.department.id}
	 $("#xibu").val(dep);
	var role=${userInfo.role.id};
	$("#job").val(role);
	var state=${userInfo.state};
	$("#state").val(state);
	}); 
</script>
</body>
</html>