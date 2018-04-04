$(function(){

	$(".txt_qiche").focus(function(){
		$(".qiche_i").hide();
		$(this).addClass("txt_focus");
	})
	$(".txt_qiche").blur(function(){
		if($(this).val()==""){
			$(".qiche_i").show();
			$(this).removeClass("txt_focus");
		}
	})
	
	function center(obj) {
        var screenWidth = $(window).width(), screenHeight = $(window).height(); 
        var scrolltop = $(document).scrollTop();
        var objLeft = (screenWidth - obj.width())/2;
        var objTop = (screenHeight - obj.height()) /  2 + scrolltop;
        obj.css({ top: objTop + 'px' });
        $(window).resize(function () {
            screenWidth = $(window).width();
            screenHeight = $(window).height();
            scrolltop = $(document).scrollTop();

            objLeft = (screenWidth - obj.width())/2;
            objTop = (screenHeight - obj.height()) /  2 + scrolltop;

            obj.css({ top: objTop + 'px' });

        });
		  $(window).scroll(function () {
			  screenWidth = $(window).width();
			  screenHeight = $(window).height();
			  scrolltop = $(document).scrollTop();

			  objLeft = (screenWidth - obj.width())/2;
			  objTop = (screenHeight - obj.height()) / 2 + scrolltop;

			  obj.css({  top: objTop + 'px' });
		  });
    }
	
		$(".btn_yy").click(function(){
			$(".zhe").css("height",$(document).height());
			center($(".gw_tan"));
			$(".zhe").show();
			$(".gw_tan").show();
			return false;
		})
	
	
	
	$(".btn_ok").click(function(){
		$(".zhe").hide();
		$(".gw_tan").hide();	
	})

})