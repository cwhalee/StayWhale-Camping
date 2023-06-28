<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>STAY WHALE</title>
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script src="js/index.js"></script>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String id = (String)session.getAttribute("id");
	%>
	<%
		if(id == null) {
	%>
		<jsp:include page="Header_Login_Fail.jsp" >
			<jsp:param name="id" value="<%= id%>"/>
		</jsp:include>
	<%
	} else {
	%>
		<jsp:include page="Header_Login_Success.jsp"/>
	<%
	}
	%>
<div class="section_wrap"></div>

<section id="main_section1">
	<nav>
		<span>이미지슬라이더</span>
		<ul>
			<li>슬라이더이미지1</li>
			<li>슬라이더이미지2</li>
		</ul>
	</nav>
</section>

<section id="main_section2">
	<nav>
		<ul>
			<a href="hotelPrint.xr"><li><div><img src="image\호텔리조트.png"></div><b>호텔/리조트</b></li></a>
			<a href="Pension_Index.jsp"><li><div><img src="image\펜션.png"></div><b>펜션</b></li></a>
			<a href="homestay.hs"><li><div><img src="image\홈스테이.png"></div><b>홈스테이</b></li></a>
			<a href="Camp_Glam_Index.cp"><li><div><img src="image\캠핑글램핑.png"></div><b>캠핑/글램핑</b></li></a>
			<a href="HotSight_Index.jsp"><li><div><img src="image\명소.png"></div><b>명소</b></li></a>
			<a href="Jeonsi_Index.jsp"><li><div><img src="image\전시회.png"></div><b>전시/행사</b></li></a>
			<a href="Withtour.jsp"><li><div><img src="image\함께하는투어.png"></div><b>함께하는Tour</b></li></a>
			<a href="Diary_Index.jsp"><li><div><img src="image\커뮤니티.png"></div><b>커뮤니티</b></li></a>
			<a href="Market_Index.jsp"><li><div><img src="image\여행쇼핑몰.png"></div><b>여행쇼핑몰</b></li></a>
		</ul>
	</nav>
</section>

<section id="main_section3">
	<nav>
		<ul>
			Best명소<br><br>
			<li>명소정보(이미지, 명소이름, 위치, 별점)</li>
			<li>명소정보(이미지, 명소이름, 위치, 별점)</li>
			<li>명소정보(이미지, 명소이름, 위치, 별점)</li>
			<li>명소정보(이미지, 명소이름, 위치, 별점)</li>
			<li>명소정보(이미지, 명소이름, 위치, 별점)</li>
		</ul>
	</nav>
</section>

<section id="main_section4">
	<nav>
		<ul>
			시즌 행사<br><br>
			<li>행사정보(이미지, 행사이름, 위치, 가격, 별점)</li>
			<li>행사정보(이미지, 행사이름, 위치, 가격, 별점)</li>
			<li>행사정보(이미지, 행사이름, 위치, 가격, 별점)</li>
			<li>행사정보(이미지, 행사이름, 위치, 가격, 별점)</li>
			<li>행사정보(이미지, 행사이름, 위치, 가격, 별점)</li>
		</ul>
	</nav>
</section>

<section id="main_section5">
	<nav id="section5_nav1">
		<ul>
			최근 인기 숙소(일주일)<br><br>
			<li>호텔</li>
			<li>리조트</li>
			<li>펜션</li>
			<li>캠핑</li>
			<li>홈스테이</li>
		</ul>
	</nav>
	<nav id="section5_nav2">
		<ul>
			<li>숙소정보(숙소이미지, 숙소명, 지역, 별점)</li>
			<li>숙소정보(숙소이미지, 숙소명, 지역, 별점)</li>
			<li>숙소정보(숙소이미지, 숙소명, 지역, 별점)</li>
			<li>숙소정보(숙소이미지, 숙소명, 지역, 별점)</li>
			<li>숙소정보(숙소이미지, 숙소명, 지역, 별점)</li>
			<li>숙소정보(숙소이미지, 숙소명, 지역, 별점)</li>
		</ul>
	</nav>
</section>

<section id="main_section6">
	<nav>
		<p>Best Hot Item!</p><br>
		<ul>
			<li>상품정보(상품명, 가격, 별점)</li>
			<li>상품정보(상품명, 가격, 별점)</li>
			<li>상품정보(상품명, 가격, 별점)</li>
			<li>상품정보(상품명, 가격, 별점)</li>
		</ul>
	</nav>
</section>

<section id="main_section7">
	<p>최고의 여행 경로!</p>
</section>
	<jsp:include page="footer.jsp"/>
</body>
</html>
