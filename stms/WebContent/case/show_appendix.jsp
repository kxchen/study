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
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
    <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="col-xs-12" style="padding:0 20px">
    <div class="upload-table" style="margin-top:30px">
        <h2 class="text-left">附件</h2>
        <hr>
    </div>
    <div class="form-group col-xs-12">
        <table class="table text-center file-table">
            <thead>
            <tr>
                <th class="th1">序号</th>
                <th>文件名称</th>
                <th class="th1">操作</th>
            </tr>
            </thead>
            <tbody>
           <c:forEach items="${appendixList}" var="appendix" varStatus="idx" >
            <tr>
                <td>${idx.index+1}</td>
                <td class="quantity">${appendix.appendixName}</td>
                <td>
                    <button type="button" class="btn btn-primary btn-xs" onclick="dowl('${appendix.id}')">
                        <i class="fa fa-download">&nbsp;下载</i>
                    </button>
                </td>
            </tr>
          </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script>
function dowl(id) {
	window.location.href="<c:url value='/CaseAction?operate=download&appendixId="+id+"'></c:url>"
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