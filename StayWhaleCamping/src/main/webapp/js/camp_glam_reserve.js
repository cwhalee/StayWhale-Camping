$(function(){
$('.slider-for').slick({
	slidesToShow: 1,
	slidesToScroll: 1,
	arrows: false,
	fade: true,
	asNavFor: '.slider-nav',
	autoplay: true,
	autoplaySpeed : 2000,
	pauseOnHover : true
});
$('.slider-nav').slick({
	centerMode: true,
	centerPadding: '60px',
	slidesToShow: 3,
	slidesToScroll: 1,
	asNavFor: '.slider-for',
	dots: false,
	centerMode: true,
	focusOnSelect: true,
	prevArrow : $('.prevArrow'), 
	nextArrow : $('.nextArrow')
});

$('.nav_button:first').addClass('selected');

$('.nav_button').click(function(e) {
  	e.preventDefault();
  	$('.nav_button').removeClass('selected');
  	$(this).addClass('selected');
  	$(this).attr('data-content-id');
});

$(document).ready(function() {
	
	
    $('.reserve_button').each(function() {
        var price = parseFloat($(this).closest('li').find('.price').text().replace(/[^0-9.-]+/g, ""));
        $(this).data('price', price); // 개별 가격을 요소의 데이터 속성에 저장
    });
    
    initializeDatepicker();
});

});

function initializeDatepicker() {
    var a = new Date();
    var year = a.getFullYear();
    var month = a.getMonth() + 1;
    var date = a.getDate();

    $("#datepicker").daterangepicker({
        "autoApply": false,
        "locale": {
            "format": "YYYY-MM-DD",
            "separator": " ~ ",
            "applyLabel": "확인",
            "cancelLabel": "취소",
            "fromLabel": "From",
            "toLabel": "To",
            "customRangeLabel": "Custom",
            "weekLabel": "W",
            "font-size": "40",
            "daysOfWeek": ["일", "월", "화", "수", "목", "금", "토"],
            "monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]
        },
        "minDate": year + '-' + month + '-' + date,
        "startDate": year + '-' + month + '-' + date, // 선택 가능한 최소 날짜와 시작 날짜를 동일하게 설정
        "endDate": year + '-' + month + '-' + date, // 선택 가능한 최소 날짜와 종료 날짜를 동일하게 설정
    }, function(start, end, label) {
        console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');var startDate = start.format('YYYY-MM-DD');

        $('#hidden_start_date').val(start.format('YYYY-MM-DD'));
        $('#hidden_end_date').val(end.format('YYYY-MM-DD'));
        
    });
    $('.datepicker').change(function() {
	var startDate = $('#hidden_start_date').val(); // 함수 호출로 수정: val()
    var endDate = $('#hidden_end_date').val();
	//var price = parseFloat($(this).closest('li').find('.price').text().replace(/[^0-9.-]+/g, ""));
	
	$.ajax({
		    url: 'reserve_check.cp',
		    type: 'POST',
		    data: { startDate: startDate, endDate: endDate },
		    success: function(data) {
		        // 서버에서 반환된 데이터를 기반으로 예약 가능한 객실 표시
		        console.log(data);
		        
		       	$('.reserve_button').each(function() {
		    		var facilityNum = $(this).closest('li').find('#facility_num_c').val();
		    
		    		// facility_num_c가 응답 데이터에 포함되어 있다면 버튼을 비활성화
		    		if (Array.isArray(data) && data.some(obj => obj.facility_num_c === facilityNum)) {
		                $(this).attr('disabled', 'disabled'); // 버튼을 비활성화
		                $(this).text('예약 완료'); // 버튼 텍스트 변경
		                $(this).addClass('disabled-button'); // CSS 효과 변경
		            } else {
		                $(this).removeAttr('disabled'); // 버튼 활성화
		                $(this).text('예약하기'); // 버튼 텍스트 원래대로 변경
		                $(this).removeClass('disabled-button'); // CSS 효과 원래대로 변경
		            }
		
		            var days = parseInt(data[0].daysBetween);		
		            var price = $(this).data('price');
		            var totalPrice = price * days;
		            $(this).closest('li').find('.price').text(totalPrice.toLocaleString() + '원');
		        });
		    },
		    error: function(error) {
		        console.log(error);
		    }
		})
});
}


// 좋아요 기능 추가
function like(id, reg_num_c) {
	if (id == "null") {
		alert("로그인이 필요한 서비스입니다.");
		window.location.href = "Login.jsp";
	} else {
		$.ajax({
			url: "camping_Like.cp",
			type: "POST",
			data: { id: id, reg_num_c: reg_num_c },
			success: function (data) {
				$("#top_info_right").load(location.href + " #top_info_right", function () {
					initializeDatepicker(); // 데이트피커 초기화
				});
				
				$(".likeicon").attr("src", "image/camping_like.png");
				$(".like_text").html("해제");
				
				
			}
		});
	}
}
// 좋아요 기능 삭제
function unlike(id, reg_num_c) {
	if (id == "null") {
		alert("로그인이 필요한 서비스입니다.");
		window.location.href = "Login.jsp";
	} else {
		$.ajax({
			url: "camping_unLike.cp",
			type: "POST",
			data: { id: id, reg_num_c: reg_num_c },
			success: function (data) {
				$("#top_info_right").load(location.href + " #top_info_right", function () {
					initializeDatepicker(); // 데이트피커 초기화
				});
				$(".likeicon").attr("src", "image/camping_unlike.png");
				$(".like_text").html("위시리스트");
				
			}
		});
	}
}

//예약데이터 전송 이벤트
function reserve_insert(id, index) {
    var start = document.getElementById("hidden_start_date").value;
    var end = document.getElementById("hidden_end_date").value;
    var user_id = id;
    var num_c = document.getElementsByClassName("facility_num_c")[index].value;
	console.log(user_id);
	
	//자바스크립트에서 null값은 false 가 아니며 undefined 와 null도 다르기 때문에 유효성검사를 아래 조건처럼 작성해주면됨.
	if (user_id !== null && user_id !== undefined) {
	    // 데이터를 전달할 URL 설정
	    var url = "reserve_insert.cp?start_date=" + start + "&end_date=" + end + "&user_id=" + user_id + "&facility_num_c=" + num_c;
	    
	    // 링크 클릭으로 GET 요청 보내기
	    var link = document.createElement("a");
	    link.href = url;
	    link.click();
	} else {
	    alert('로그인 후 사용 가능한 서비스입니다.');
	    location.href="Login.jsp";
	}

}


// 예약페이지 네비게이션바 클릭 이벤트
document.getElementById("reserve").addEventListener("click", function() {
  document.getElementById("room_section").style.display = "block";
  
  document.getElementById("review_info").style.display = "none";
});

document.getElementById("review").addEventListener("click", function() {
  document.getElementById("review_info").style.display = "block";
  
  document.getElementById("room_section").style.display = "none";
});

$(function() {
  const isModalActive = []; // 각 모달의 활성화 여부를 저장하는 배열
  const modals = Array.from(document.getElementsByClassName("modal-overlay")); // 모달 요소들을 배열로 변환

  function modalOn(index) {
    const modal = modals[index];
    modal.style.display = "flex";
    isModalActive[index] = true; // 해당 모달이 활성화됨을 표시
	
	
	
    if (isModalActive[index]) {
      $('.slider-for2').slick({
        slidesToShow: 1, 
        slidesToScroll: 1,
        arrows: false, 
        fade: true, 
        asNavFor: '.slider-nav2',
        autoplay: true, 
        autoplaySpeed: 2000,
        pauseOnHover: true
      });

      $('.slider-nav2').slick({
        centerMode: true, 
        centerPadding: '60px',
        slidesToShow: 3, 
        slidesToScroll: 1,
        asNavFor: '.slider-for2', 
        dots: false,
        focusOnSelect: true,
        prevArrow: $(".prevArrow2"),
        nextArrow: $(".nextArrow2")
      });
    }
  }

  function isModalOn(index) {
    return isModalActive[index];
  }

  function modalOff(index) {
    const modal = modals[index];
    modal.style.display = "none";
    isModalActive[index] = false; // 모달이 비활성화됨을 표시
    $('.slider-for2').slick('unslick');
    $('.slider-nav2').slick('unslick');
  }

  const btnModals = document.getElementsByClassName("btn-modal");
  for (let i = 0; i < btnModals.length; i++) {
    btnModals[i].addEventListener("click", e => {
      modalOn(i);
    });
  }

  const closeBtns = document.querySelectorAll(".modal-overlay .close-area");
  closeBtns.forEach((closeBtn, index) => {
    closeBtn.addEventListener("click", e => {
      modalOff(index);
    });
  });

  modals.forEach((modal, index) => {
    modal.addEventListener("click", e => {
      const evTarget = e.target;
      if (evTarget.classList.contains("modal-overlay")) {
        modalOff(index);
      }
    });
  });

  window.addEventListener("keyup", e => {
    for (let i = 0; i < isModalActive.length; i++) {
      const modal = modals[i]; // modal 변수 선언 및 초기화
      if (isModalOn(i) && e.key === "Escape") {
        modalOff(i);
      }
    }
  });
});
