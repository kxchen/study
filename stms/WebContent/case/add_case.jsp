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
    <title>上传案例</title>
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
                    <a href="<c:url value="/CaseAction?operate=addCase"></c:url>" class="active"><i class="fa fa-upload"></i>&nbsp;&nbsp;上传案例</a>
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
    <div class="main-content col-xs-10 col-xs-offset-1 ">
        <div class="col-xs-10 col-xs-offset-1 " >
            <div class="upload-table" style="margin-top: 40px">
                <h2 class="text-left">上传案例</h2>
                <hr>
            </div>
            <form class="form-horizontal col-xs-12" role="form" method="post" enctype="multipart/form-data">
            <input type="hidden" name="caseId" value="${caseId}" id="caseId">
            <input type="hidden" name="appendixId" id="appendixId">
                <div class="form-group">
                    <label for="firstname" class="col-xs-2 control-label">标   题</label>
                    <div class="col-xs-10">
                        <input type="text" class="form-control" id="firstname" placeholder="请输入标题">
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastname" class="col-xs-2 control-label">备   注</label>
                    <div class="col-xs-10">
                        <textarea rows="3" class="form-control" id="lastname"></textarea>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="file-1" class="col-xs-2 control-label">附   件</label>
                    <div class="col-xs-10">
                         <input id="resources_file" name="uploadfile"  type="file" class="file" multiple  />
                    </div>
                </div>   
                  <script>
                  var caseId=$("#caseId").val();
                         $("#resources_file").fileinput({
                           language: "zh",
                            uploadUrl: "<c:url value='UploadAction?caseId="+caseId+"'></c:url>", //上传的地址
                             maxFileCount: 10,
                            validateInitialCount: true,
                            showPreview: false,//文件的预览图
                            autoReplace: true,
                            showRemove:false,
                             }).on('fileuploaded', function(event, data, id, index) {
                            	 var uuid = data.response.initialPreviewConfig[0].key;
                            	 console.log(uuid)
                             	$("#appendixId").val(uuid);
                              }).on('filedeleted', function (event, key) {
                               
                              });
                    </script>       
            </form>
            <div class="col-xs-12" style="margin-bottom: 20px">
                <hr>
                <button type="button" class="btn btn-primary pull-right" onclick="doAddCase()">提 交</button>
            </div>
            
          
        </div>
    </div>
</div>

<script>
function doAddCase() {
	//appendix可以为空
	var appendixId=$("#appendixId").val();
	if(appendixId.length==0){  
		 layer.msg("请先上传附件", {
				icon: 2,
				time: 1000//时间设置无反应
			});
		 return
	}  	
	var caseId=$("#caseId").val();
	var caseName=$("#firstname").val();
	if(caseName.length==0){  
		 layer.msg("案例名称不能为空", {
				icon: 2,
				time: 1000//时间设置无反应
			});
		 return
	}  	
	var remark=$("#lastname").val();
	$.ajax({
		url:"<c:url value='/CaseAction'></c:url>",
		type:'post',
		dataType:'json',
		data:{operate:"doAddCase",caseId:caseId,caseName:caseName,remark:remark,appendixId:appendixId},
		success:function(msg){
			if(msg.state=="success"){
				layer.msg('添加成功', {
					icon: 1,
					time: 1000//时间设置无反应
				});
				setTimeout(function () {
					window.location.reload();
				}, 1000);
			}else{
				layer.msg('添加失败', {
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