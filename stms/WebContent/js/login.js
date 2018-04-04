var msgdsq;
//错误时：提示调用方法
function show_err_msg(msg){
    $('.msg_bg').html('');
    clearTimeout(msgdsq);
    $('body').append('<div class="sub_err" style="position:absolute;top:60px;left:0;width:500px;z-index:999999;"></div>');
    var errhtml='<div  class="bac" style="padding:8px 0px;width:100%;margin:0 auto;background-color:#fff;color:#B90802;border:3px #ff0000 solid;text-align:center;font-size:16px;font-family:微软雅黑;"><img style="margin-right:10px;" src="images/wrong.png">';
    var errhtmlfoot='</div>';
    $('.msg_bg').height($(document).height());
    $('.sub_err').html(errhtml+msg+errhtmlfoot);
    var left=($(document).width()-500)/2;
    $('.sub_err').css({'left':left+'px'});
    var scroll_height=$(document).scrollTop();
    $('.sub_err').animate({'top': scroll_height+120},300);
    msgdsq=setTimeout(function(){
        $('.sub_err').animate({'top': scroll_height+80},300);
        setTimeout(function(){
            $('.msg_bg').remove();
            $('.sub_err').remove();
        },300);
    }, "1000");
}

//正确时：提示调用方法
function show_msg(msg,url){
    $('.msg_bg').html('');
    clearTimeout(msgdsq);
    $('body').append('<div class="sub_err" style="position:absolute;top:60px;left:0;width:500px;z-index:999999;"></div>');
    var htmltop='<div class="bac" style="padding:8px 0px;border:1px solid #090;width:100%;margin:0 auto;background-color:#FFF2F8;color:#090;border:3px #090 solid;;text-align:center;font-size:16px;"><img style="margin-right:10px;" src="images/loading.gif">';
    var htmlfoot='</div>';
    $('.msg_bg').height($(document).height());
    var left=($(document).width()-500)/2;
    $('.sub_err').css({'left':left+'px'});
    $('.sub_err').html(htmltop+msg+htmlfoot);
    var scroll_height=$(document).scrollTop();
    $('.sub_err').animate({'top': scroll_height+120},500);
    msgdsq=setTimeout(function(){
        $('.sub_err').animate({'top': scroll_height+80},500);
        setTimeout(function(){
            $('.msg_bg').remove();
            $('.sub_err').remove();
            if(url!='')
            {
                location.href=url;
            }
        },800);

    }, "1200");
}
//显示加载动画
function show_loading()
{
    var str='<div class="msg_bg" style="background:#000;opacity:0.5;filter:alpha(opacity=50);z-index:99998;width:100%;position:absolute;left:0;top:0"></div>';
    str+='<div class="msg_bg" style="z-index:99999;width:100%;position:absolute;left:0;top:0;text-align:center;"><img src="images/loading.gif" alt="" class="loading"></div>'
    $('body').append(str);
    var scroll_height=$(document).scrollTop();
    $('.msg_bg').height($(document).height());
    $('.loading').css('margin-top',scroll_height+240);
}
//支持Enter键登录
document.onkeydown = function(e){
    if($(".bac").length==0)
    {
        if(!e) e = window.event;
        if((e.keyCode || e.which) == 13){
            var obtnLogin=document.getElementById("submit_btn")
            obtnLogin.focus();
        }
    }
}
$(function(){
    //提交表单
    $('#submit_btn').click(function(){
        show_loading();
        var myReg =/^1[34578]\d{9}$/; //手机号正则
        if($('#phone').val() == ''){
            show_err_msg('请填写手机号！');
            $('#phone').focus();
        }else if(!myReg.test($('#phone').val())){
            show_err_msg('您的手机号格式错误！');
            $('#phone').focus();
        }else if($('#password').val() == '') {
            show_err_msg('密码还没填呢！');
            $('#password').focus();
        }else{
        	$('#login_form').submit();
        }
    });
});