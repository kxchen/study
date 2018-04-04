<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width" name="viewport">
    <title>新建角色</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/font-awesome/css/font-awesome.min.css">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/jquery.twbsPagination.js"></script>
        <script type="text/javascript" src="js/jquery.placeholder.min.js"></script>
    <script type="text/javascript" src="js/layer/layer.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
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
    <style>
        .updatePwd{
            margin: 10px 0;
        }
        .authority1 {
			margin-bottom:20px;
		}
        .authority1>h4{
            font-size: 14px;
        }
    </style>
</head>
<body>
<div class="container ">
    <div class="col-xs-12 ">
        <div id="upload-table">
            <h2 class="text-left font-size">新建角色</h2>
            <hr>
        </div>
        <form class="form-horizontal col-xs-12" role="form" method="post">
            <div class="form-group updatePwd">
                <label for="name" class="col-xs-3 control-label">角色名：</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="roleName" placeholder="请输入角色名">
                </div>
            </div>
            <div class="form-group updatePwd">
                <label for="admin" class="col-xs-3 control-label ">权限：</label>
                <div class="col-xs-9">
                    <div id="admin">
                        <div class="authority1">
                            <h4>*用户管理</h4>
                             <c:forEach items="${uAuList}" var="u" varStatus="idx" >
                            <label class="checkbox-inline">
                                <input type="checkbox"  name="checkbox" value="${u.id }">${u.authorityName}
                            </label>
                            </c:forEach>
                        </div>
                        <div class="authority1">
                            <h4>*作业管理</h4>
                            <c:forEach items="${wAuList}" var="w" varStatus="idx" >
                            <label class="checkbox-inline">
                                <input type="checkbox" name="checkbox" value="${w.id }">${w.authorityName}
                            </label>
                            </c:forEach>
                        </div>
 						<div class="authority1">
                            <h4>*案例管理</h4>
                            <c:forEach items="${cAuList}" var="c" varStatus="idx" >
                            <label class="checkbox-inline">
                                <input type="checkbox" name="checkbox" ${c.id=="14"?"checked":"" } value="${c.id }">${c.authorityName}
                            </label>
                            </c:forEach>
                        </div>
                        <div class="authority1">
                            <h4>*角色权限</h4>
                           <c:forEach items="${rAuList}" var="r" varStatus="idx" >
                            <label class="checkbox-inline">
                                <input type="checkbox" name="checkbox" value="${r.id }">${r.authorityName}
                            </label>
                            </c:forEach>
                        </div>
                        <div class="authority1">
                            <h4>*部门管理</h4>
                           <c:forEach items="${dAuList}" var="d" varStatus="idx" >
                            <label class="checkbox-inline">
                                <input type="checkbox" name="checkbox" value="${d.id }">${d.authorityName}
                            </label>
                            </c:forEach>
                        </div>
                    </div>

                </div>
            </div>
            <div class="updateBtn" data-toggle="buttons">
                <hr>
                <button type="button" class="btn btn-primary pull-right btn-click" onclick="doAddRole()">确   定</button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
	function doAddRole() {
		var roleName=$("#roleName").val();
		if(roleName.length==0){  
			 layer.msg("请输入角色名", {
					icon: 2,
					time: 1000//时间设置无反应
				});
			 return
		}  
		var obj=document.getElementsByName("checkbox");
		var count = obj.length;
		var fileId = null;
		var selectCount = 0;
		fileId = new Array();
		for(var i = 0; i < count; i++)
		{
			if(obj[i].checked == true)
			{
				selectCount++;
//				勾选后id往数组里添加
				fileId.push(obj[i].value)
			}
		}
		var  fileIds= fileId.join(',')
		if(fileIds.length==0){  
			 layer.msg("请选择权限", {
					icon: 2,
					time: 1000//时间设置无反应
				});
			 return
		}  
		 $.ajax({
				url:"<c:url value='/RoleAction'></c:url>",
				type:'post',
				dataType:'json',
				data:{operate:"doAddRole",roleName:roleName,authorityIds:fileIds},
				success:function(msg){
					if(msg.state=="success"){
						layer.msg('添加成功', {
							icon: 1,
							time: 1000//时间设置无反应
						});
						setTimeout(function () {
							parent.location.reload();
							var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
							parent.layer.close(index);
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

</script>
</body>
</html>