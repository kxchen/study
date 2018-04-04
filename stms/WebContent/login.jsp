<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>登录</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/login.css">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/jquery.twbsPagination.js"></script>
    <script type="text/javascript" src="js/layer/layer.js"></script>
    <script type="text/javascript" src="js/jquery.placeholder.min.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
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
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="account-wall">
                <img class="profile-img" src="images/pdsts1.png" alt="软件测试案例库管理系统">
                <div class="alert alert-warning alert-dismissable warning" id="warning">
                    	号码格式错误，请重新输入！
                </div>
                <div class="alert ">
                ${msg}
                </div>
                <form  class="form-signin" action="<c:url value="/UserAction"></c:url>" id="login_form" method="post" >
                	<input type="hidden" name="operate" value="login">
                    <input id="phone" class="form-control" placeholder="手机号码" value="${mobilePhone }" name="mobilePhone" type="tel"  required autofocus>
                    <input class="form-control password" placeholder="密码" value="${password }" name="password" type="password" value="" required>
                    <button class="btn btn-lg btn-primary btn-block" type="button" value="" id="loginBtn">登 录</button>
                </form>
            </div>
            <a href="javascript:void(0);" class="text-center new-account">Copyright 2016 上海浦东软件平台有限公司. All Rights
                Reserved. </a>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        // 登录验证
        $("#loginBtn").click(function(){
            // 做表单输入校验
            var userId = $("#phone");
            var password = $(".password");
            var warning=$(".warning");
            if ($.trim(userId.val()) == ""){
                warning.css("display","block");
                warning.html("用户名不能为空！");
                userId.focus(function () {
                    warning.css("display","none");
                });
            }else if (!/^1[34578]\d{9}$/.test($.trim(userId.val()))){
                warning.css("display","block");
                warning.html("用户名格式不正确！");
                userId.focus(function () {
                    warning.css("display","none");
                });
            }else if ($.trim(password.val()) == ""){
                warning.css("display","block");
                warning.html("密码不能为空！");
                password.focus(function () {
                    warning.css("display","none");
                });
            }
/*            else if (!/^[a-zA-Z\d_]{8,}$/.test($.trim(password.val()))) {
                 warning.css("display","block");
                 warning.html("密码长度不够！");//长度8
                password.focus();
            }*/
            
            $("#login_form").submit();
           
        });

        // 为document绑定onkeydown事件监听是否按了回车键
        $(document).keydown(function(event){
            if (event.keyCode === 13){ // 按了回车键
                $("#loginBtn").trigger("click");
            }
        });
    });
</script>

</body>
</html>