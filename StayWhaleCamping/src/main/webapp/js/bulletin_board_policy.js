$(function(){
      var $header = $('header'); //헤더를 변수에 넣기
      var $page = $('.section_wrap'); //색상이 변할 부분
      var $window = $(window);
      var pageOffsetTop = $page.offset().top;//색상 변할 부분의 top값 구하기

      $window.resize(function(){ //반응형을 대비하여 리사이즈시 top값을 다시 계산
        pageOffsetTop = $page.offset().top;
      });
      
      $window.on('scroll', function(){ //스크롤시
        var scrolled = $window.scrollTop() >= pageOffsetTop; //스크롤된 상태; true or false
        $header.toggleClass('down', scrolled); //클래스 토글
        $(".logo").toggleClass('logodown', scrolled);
      });
    });
    
    $(function(){
		$(".custom-btn span").click(function(){
			var txt = $(this).text();
			if(txt === "글쓰기")
			{
				location.href="dbprocess/Bulletin_Board_isId.jsp";
			}
			else
			{
				
			}
		});
	});
	
	$(function(){
		$(".col2").click(function(){
			var tr = $(this).parent();
			var td = tr.children();
			var post_num = td.eq(0).text();
			location.href= "Board_Detail.jsp?post_num="+post_num;
		});
	});
	
	$(function(){
		$("li[data-num='1']").click(function(){
			location.href="Bulletin_Board_Notice.jsp";
		});
		$("li[data-num='2']").click(function(){
			location.href="Bulletin_Board_Event.jsp";
		});
		$("li[data-num='3']").click(function(){
			location.href="Bulletin_Board_QnA.jsp";
		});
		$("li[data-num='4']").click(function(){
			location.href="Bulletin_Board.jsp";
		});
	});
	
	
	
	/*var waveBtn = (function () {
  'use strict';
  var btn = document.querySelectorAll('.wave'),
      tab = document.querySelector('.tab-bar'),
      indicator = document.querySelector('.indicator'),
      indi = 0;
  indicator.style.marginLeft = indi + 'px';

  for(var i = 0; i < btn.length; i++) {
    btn[i].onmousedown = function (e) {
      var newRound = document.createElement('div'),x,y;

      newRound.className = 'cercle';
      this.appendChild(newRound);

      x = e.pageX - this.offsetLeft;
      y = e.pageY - this.offsetTop;

      newRound.style.left = x + "px";
      newRound.style.top = y + "px";
      newRound.className += " anim";

      indicator.style.marginLeft = indi + (this.dataset.num-1) * 150 + 'px';

      newRound.addEventListener('animationend', function () {
        newRound.remove();
      });
    };
  }
})();*/