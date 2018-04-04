<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width" name="viewport">
    <title>编辑部门</title>
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
</head>
<body>
<div class="container ">
    <div class="col-xs-12 ">
        <div id="upload-table">
            <h2 class="text-left font-size">编辑部门</h2>
            <hr>
        </div>
        <form class="form-horizontal col-xs-12" role="form" method="post" >
            <div class="search">
                <div class="bs-example bs-example-form" role="form">
                    <div class="row">
                        <div class="col-xs-12 pull-right">
                            <div class="input-group">
                            	<input type="hidden" id="depId" value="${department.id}" >
                                <input type="text" class="form-control" name="depName" value="${department.departmentName}" id="depName" placeholder="请输入部门名称">
                                <div class="input-group-btn" onclick="doUpdateDep()">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-legal"></i>&nbsp;&nbsp;确定</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
	function doUpdateDep() {
		var departmentName=$("#depName").val();
		var departmentId=$("#depId").val();
		if(departmentName.length==0){  
			 layer.msg("请输入部门名称", {
					icon: 2,
					time: 1000
				});
			 return
		}  
		 $.ajax({
				url:"<c:url value='/DepartmentAction'></c:url>",
				type:'post',
				dataType:'json',
				data:{operate:"doUpdateDep",departmentName:departmentName,departmentId:departmentId},
				success:function(msg){
					if(msg.state=="success"){
						layer.msg('编辑成功', {
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