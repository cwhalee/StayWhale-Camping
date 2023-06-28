/**
 * 
 *//*$(document).ready(function() {
  $(".tab").on("click", function() {
    $(".tab").removeClass("active");
    $(this).addClass("active");
    var tabNum = $(this).data("num");
    var indicatorWidth = $(".tab").width();
    $(".indicator").animate({
      left: (indicatorWidth * (tabNum - 1)) + "px"
    }, 300);
  });
});*/




var Pagination = {
    code: '',

    Extend: function(data) {
        data = data || {};
        Pagination.size = data.size || 300;
        Pagination.page = data.page || 1;
        Pagination.step = data.step || 3;
    },

    Add: function(s, f) {
        for (var i = s; i < f; i++) {
            Pagination.code += '<a>' + i + '</a>';
        }
    },

    Last: function() {
        Pagination.code += '<i>...</i><a>' + Pagination.size + '</a>';
    },

    First: function() {
        Pagination.code += '<a>1</a><i>...</i>';
    },


    Click: function() {
        Pagination.page = +this.innerHTML;
        Pagination.Start();
    },

    Prev: function() {
        Pagination.page--;
        if (Pagination.page < 1) {
            Pagination.page = 1;
        }
        Pagination.Start();
    },

    Next: function() {
        Pagination.page++;

        if (Pagination.page > Pagination.size) {
            Pagination.page = Pagination.size;
        }
        Pagination.Start();
    },

    TypePage: function() {
        Pagination.code = '<input onclick="this.setSelectionRange(0, this.value.length);this.focus();" onkeypress="if (event.keyCode == 13) { this.blur(); }" value="' + Pagination.page + '" /> &nbsp; / &nbsp; ' + Pagination.size;
        Pagination.Finish();
        var v = Pagination.e.getElementsByTagName('input')[0];
        v.click();

    },

    Bind: function() {
        var a = Pagination.e.getElementsByTagName('a');
        for (var i = 0; i < a.length; i++) {
            if (+a[i].innerHTML === Pagination.page) a[i].className = 'current';
            a[i].addEventListener('click', Pagination.Click, false);
        }
        var d = Pagination.e.getElementsByTagName('i');
        for (i = 0; i < d.length; i++) {
            d[i].addEventListener('click', Pagination.TypePage, false);
        }
    },

    Finish: function() {
        Pagination.e.innerHTML = Pagination.code;
        Pagination.code = '';
        Pagination.Bind();
    },

    Start: function() {
      
     
        Pagination.step = 3;
    
      
        if (Pagination.size < Pagination.step * 2 + 6) {
            Pagination.Add(1, Pagination.size + 1);
        } else if (Pagination.page < Pagination.step * 2 + 1) {
            Pagination.Add(1, Pagination.step * 2 + 4);
            Pagination.Last();
        } else if (Pagination.page > Pagination.size - Pagination.step * 2) {
            Pagination.First();
            Pagination.Add(Pagination.size - Pagination.step * 2 - 2, Pagination.size + 1);
        } else {
            Pagination.First();
            Pagination.Add(Pagination.page - Pagination.step, Pagination.page + Pagination.step + 1);
            Pagination.Last();
        }
        Pagination.Finish();
      
    },

    Buttons: function(e) {
        var nav = e.getElementsByTagName('a');
        nav[0].addEventListener('click', Pagination.Prev, false);
        nav[1].addEventListener('click', Pagination.Next, false);
    },

    Create: function(e) {
        var html = [
            '<a>&#9668;</a>', // previous button
            '<span></span>', // pagination container
            '<a>&#9658;</a>' // next button
        ];
        e.innerHTML = html.join('');
        Pagination.e = e.getElementsByTagName('span')[0];
        Pagination.Buttons(e);
    },

    Init: function(e, data) {
        Pagination.Extend(data);
        Pagination.Create(e);
        Pagination.Start();
    }
};





var init = function() {
  
    Pagination.Init(document.getElementById('pagination'), {
        size: 10, // pages size
        page: 1, // selected page
        step: 1 // pages before and after current
    });
  
};

document.addEventListener('DOMContentLoaded', init, true);

/*$(function(){
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
    });*/
    
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
			/*location.href="Bulletin_Board_Notice.jsp";*/
		});
		$("li[data-num='2']").click(function(){
			location.href="FreeBtb.jsp";
		});
		$("li[data-num='3']").click(function(){
			location.href="Bulletin_Board_Review.jsp";
		});
		$("li[data-num='4']").click(function(){
			location.href="Bulletin_Board_Inquiry.jsp";
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






