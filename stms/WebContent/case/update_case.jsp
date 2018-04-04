<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width" name="viewport">
    <title>编辑案例</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/fileinput.min.css">
    <link rel="stylesheet" href="css/font-awesome/css/font-awesome.min.css">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/fileinput/fileinput.min.js"></script>
    <script type="text/javascript" src="js/fileinput/zh.js"></script>
        <script type="text/javascript" src="js/layer/layer.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
    <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

    <div class="col-xs-12" style="padding:0 20px">
        <div class="upload-table1" style="margin-top:30px;">
            <h2 class="text-left">编辑案例</h2>
            <hr>
        </div>
        <form class="form-horizontal col-xs-12" role="form" method="post" enctype="multipart/form-data">
            <input type="hidden" name="caseId" value="${caseInfo.id}" id="caseId">
            <input type="hidden" name="appendixId" id="appendixId">
                <div class="form-group">
                    <label for="firstname" class="col-xs-2 control-label">标   题</label>
                    <div class="col-xs-10">
                        <input type="text" class="form-control" id="firstname" value="${caseInfo.caseName }" placeholder="请输入标题">
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastname" class="col-xs-2 control-label">备   注</label>
                    <div class="col-xs-10">
                        <textarea rows="3" class="form-control" id="lastname" >${caseInfo.remark}</textarea>
                    </div>
                </div>
                 <div class="form-group">
                <label for="upload-file" class="col-xs-2 control-label">附   件:</label>
                <div class="col-xs-10">
                    <table class="table text-center file-table" id="upload-file">
                        <thead>
                            <tr>
                                
                                <th>文件名称</th>
                                <th class="th1">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${appendixList}" var="appendix" varStatus="idx" >
                            <tr id="${appendix.id}del">
                                
                                <td class="quantity">${appendix.appendixName}</td>
                                <td>
                                    <button type="button" class="btn btn-danger btn-xs" onclick="delAppendix('${appendix.id}')">
                                        <i class="fa fa-search">删除</i>
                                    </button>
                                </td>
                            </tr>
                           </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
                <div class="form-group">
                    <label for="file-1" class="col-xs-2 control-label"></label>
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
        <div class="col-xs-12" style="margin-bottom: 20px;">
            <hr>
            <button type="button" class="btn btn-primary pull-right" onclick="doUpdateCase()" >提 交</button>
        </div>
    </div>

<script type="text/javascript">
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
function doUpdateCase() {
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
			data:{operate:"doUpdateCase",caseId:caseId,caseName:caseName,remark:remark},
			success:function(msg){
				if(msg.state=="success"){
					layer.msg('修改成功', {
						icon: 1,
						time: 1000//时间设置无反应
					});
					setTimeout(function () {
						parent.location.reload();
						var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
						 parent.layer.close(index);
					}, 1000);
					
				}else{
					layer.msg('修改失败', {
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
	            var maxwidth=25;
	            if($(this).text().length>maxwidth){
	                $(this).text($(this).text().substring(0,maxwidth));
	                $(this).html($(this).html()+"…");
	            }
	        });
	    });
</script>
</body>
</html>