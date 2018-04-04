
//提示工具
$(function () { $("[data-toggle='tooltip']").tooltip(); });

$(document).ready(function(){
//限制字符个数
    $('.quantity').each(function(){
        var maxwidth=20;
        if($(this).text().length>maxwidth){
            $(this).text($(this).text().substring(0,maxwidth));
            $(this).html($(this).html()+"…");
        }
    });
    $('.quantitys').each(function(){
        var maxwidth=30;
        if($(this).text().length>maxwidth){
            $(this).text($(this).text().substring(0,maxwidth));
            $(this).html($(this).html()+"…");
        }
    });

});



