<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="vo.Accmodation_Camping" %>
<%@ page import="vo.Camping_Youtube" %>
<% request.setCharacterEncoding("UTF-8");%>

<html>
<head>
	 <meta charset="utf-8">
	 <title>STAY WHALE || Camp_Glam</title>
	 <link rel="stylesheet" type="text/css" href="css/camp_glam_index.css">
	 <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>	 
</head>
<body>
  <%
    request.setCharacterEncoding("utf-8");
    String id = (String)session.getAttribute("id"); 
    ArrayList<Accmodation_Camping> campList = (ArrayList<Accmodation_Camping>)request.getAttribute("articleList");
    ArrayList<Camping_Youtube> youtubeData = (ArrayList<Camping_Youtube>)request.getAttribute("youtubeData");
    
    ArrayList<Accmodation_Camping> searchPlaceList = (ArrayList<Accmodation_Camping>)request.getAttribute("searchPlaceList");
    ArrayList<Accmodation_Camping> searchDateList = (ArrayList<Accmodation_Camping>)request.getAttribute("searchDateList");
    ArrayList<Accmodation_Camping> searchKeywordList = (ArrayList<Accmodation_Camping>)request.getAttribute("searchKeywordList");
    
    if(searchPlaceList != null && campList == null && searchDateList==null && searchKeywordList==null){
    	campList = searchPlaceList;
    }else if(searchPlaceList == null && campList == null && searchDateList != null && searchKeywordList==null){
    	campList = searchDateList;
    }else if(searchPlaceList == null && campList == null && searchDateList == null && searchKeywordList!=null){
    	campList = searchKeywordList;
    }
    
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

    <!-- 메인 -->
    <main>
    	
      	<!-- 타이틀 및 추천 상품 -->
		<div class="title">
			<h1><a href="Camp_Glam_Index.cp" style="color: white">STAY WHALE</a></h1>
			<p>캠핑 / 글램핑</p>
			<div class="slider_controls">
				<button id="prevButton">이전</button>
				<button id="nextButton">다음</button>
			</div>
		</div>
	
      <!-- 조건별 검색 -->
		<div class="content">      	
	      
	      	<section class="reserve_section">
	      	
				<div class="reserve_title"> 캠핑 테마</div>
				
				<div class="reserve_content">
					<form id="search_keyword" action="search_keyword.cp" method="post">
						<ul>
							<li>
								<div><label class="keyword_label" for="keyword"> 연관 검색 </label></div>
								<div><input type="text" class="keyword" name="keyword" placeholder="검색하고싶은 상품을 입력해주세요."></div>
								<div><button type="submit" id="keyword_button" class="reservation_button"> 상품 검색 </button></div>
							</li>
						</ul>
					</form>
					<form id="search_date" action="search_date.cp" method="post">
						<ul>
							<li>
								<div>
									<label class="check_in_label" style="margin: 0px 5px 5px 0px;" for="check_in"> Check-in Date </label>
									<label class="check_out_label" style="margin: 0px 5px 5px 0px;" for="check_out"> Check-out Date </label>
								</div>
								<div>
									<input style="margin: 0px 5px 5px 0px;" type="date" id="check_in" name="check_in" min="today"  />
									<input style="margin: 0px 5px 5px 0px;" type="date" id="check_out" name="check_out" min="today"  />
								</div>
								<div>
									<button type="submit" id="date_button" class="reservation_button"> 날짜 검색 </button>
								</div>
							</li>
						</ul>
					</form>
					<form id="search_place" action="search_place.cp" method="post">
						<ul>
							<li>
								<div class="sidogugun_label">
									<label style="margin-right:30px; margin-bottom: 5px;" for="sido1">시/도</label>
									<label style="margin-bottom: 5px;" for="gugun1" >구/군</label>  
								</div>
								<div class="sidogugun_select">
									<select name="sido1" id="sido1"></select>
									<select name="gugun1" id="gugun1"></select>	
								</div>
								<div>
									<button type="submit" id="place_button" class="reservation_button"> 지역 검색 </button>
								</div>
							</li>
						</ul>
					</form>
				</div>
				<div class="reserve_content2">
					<nav>
						<button type="button" id="btn_camping" class="spot_button" data-content-id="content_camping">Camp</button>
		        		<button type="button" id="btn_caravan" class="spot_button" data-content-id="content_caravan">Caravan</button>
		        		<button type="button" id="btn_glamping" class="spot_button" data-content-id="content_glamping">Glamping</button>
		        	</nav>		
				</div>
			</section>	  
			
	      <!--캠핑 테마 및 메인상품 목록 -->
	      <section class="spots_section">			
	        <div class="spot_container">
				<div class="post-slider" id="content_camping" style="display: block">
<% 
				boolean isCategoryCampingExist = false;
				for (int i=0;i<campList.size();i++) {
				    if ("Camping".equals(campList.get(i).getCategory())) {
				        isCategoryCampingExist = true;
				        break;
				    }
				}
				if (!isCategoryCampingExist) {
				    out.println("<div class='product_none'>검색된 Camping 상품이 없습니다.</div>");
				}else{					
%>
					<ul class="slider-nav">
<% 
					for(int i=0; i<campList.size(); i++) {
						if("Camping".equals(campList.get(i).getCategory())){
				            out.println("<li class='post'>");
				            out.println("<div>");
				            out.println("<div> <input type='hidden' value='"+campList.get(i).getReg_num_c() +"'>");
				            out.println("<a href='camping_reserve.cp?reg_num_c="+campList.get(i).getReg_num_c()+"'>");
				            out.println("<img src='image/"+ campList.get(i).getAcc_picture().split(",")[0] +"' alt='이미지 준비중'>");
				            out.println("</a> </div>");
				            out.println("</div>");
				            out.println("<div>");
				            out.println("<a class='h2'><h2>"+campList.get(i).getAcc_name()+"</h2></a>");
				            out.println("<p class='h3'><h3>"+campList.get(i).getSite_1()+"</h3></p>");
				            out.println("<div class='h3'><h3>"+campList.get(i).getSite_2()+"</h3></div>");
				            if(campList.get(i).getRating() != 0.0){
				            	out.println("<div class='rating'>★"+campList.get(i).getRating()+"</div>");
				            }else{
				            	out.println("<div class='rating'>평점 없음</div>");
				            }
				            out.println("<div><a href='camping_reserve.cp?reg_num_c="+campList.get(i).getReg_num_c()+"&user_id="+id+"'>");
				            out.println("<button class='spots_button'>예약하기</button>");
				           	out.println("</a></div>");
				           	out.println("</div>");
				            out.println("</li>");
						} 
					} 
				}
%>
					</ul>
				</div>
	        	
	          	<div class="post-slider" id="content_caravan" style="display:none">
<% 
				boolean isCategoryCaravanExist = false;
				for (int i=0;i<campList.size();i++) {
				    if ("Caravan".equals(campList.get(i).getCategory())) {
				        isCategoryCaravanExist = true;
				        break;
				    }
				}
				
				if (!isCategoryCaravanExist) {
				    out.println("<div class='product_none'>검색된 Caravan 상품이 없습니다.</div>");
				    System.out.println("Category 'Caravan' data not found.");
				}else{					
%>	          	
		        	<ul class="slider-nav">
<% 
					for(int i=0; i<campList.size(); i++) {
						if("Caravan".equals(campList.get(i).getCategory())){
							out.println("<li class='post'>");
				            out.println("<div>");
				            out.println("<div> <input type='hidden' value='"+campList.get(i).getReg_num_c() +"'>");
				            out.println("<a href='camping_reserve.cp?reg_num_c="+campList.get(i).getReg_num_c()+"'>");
				            out.println("<img src='image/"+ campList.get(i).getAcc_picture().split(",")[0] +"' alt='이미지 준비중'>");
				            out.println("</a> </div>");
				            out.println("</div>");
				            out.println("<div>");
				            out.println("<a class='h2'><h2>"+campList.get(i).getAcc_name()+"</h2></a>");
				            out.println("<p class='h3'><h3>"+campList.get(i).getSite_1()+"</h3></p>");
				            out.println("<div class='h3'><h3>"+campList.get(i).getSite_2()+"</h3></div>");
				            if(campList.get(i).getRating() != 0.0){
				            	out.println("<div class='rating'>★"+campList.get(i).getRating()+"</div>");
				            }else{
				            	out.println("<div class='rating'>평점 없음</div>");
				            }
				            out.println("<div><a href='camping_reserve.cp?reg_num_c="+campList.get(i).getReg_num_c()+"'>");
				            out.println("<button class='spots_button'>예약하기</button>");
				           	out.println("</a></div>");
				           	out.println("</div>");
				            out.println("</li>");
						}
					}
				}
%>
		          	</ul>
	          	</div>
	          	<div class="post-slider" id="content_glamping" style="display:none">
<% 
				boolean isCategoryGlampingExist = false;
				for (int i=0;i<campList.size();i++) {
				    if ("Glamping".equals(campList.get(i).getCategory())) {
				        isCategoryGlampingExist = true;
				        break;
				    }
				}
				
				if (!isCategoryGlampingExist) {
				    out.println("<div class='product_none'>검색된 Glamping 상품이 없습니다.</div>");
				    System.out.println("Category 'Glamping' data not found.");
				}else{					
%>	          	
		        	<ul class="slider-nav">
<% 
					for(int i=0; i<campList.size(); i++) {
						if("Glamping".equals(campList.get(i).getCategory())){
							out.println("<li class='post'>");
				            out.println("<div>");
				            out.println("<div> <input type='hidden' value='"+campList.get(i).getReg_num_c() +"'>");
				            out.println("<a href='camping_reserve.cp?reg_num_c="+campList.get(i).getReg_num_c()+"'>");
				            out.println("<img src='image/"+ campList.get(i).getAcc_picture().split(",")[0] +"' alt='이미지 준비중'>");
				            out.println("</a> </div>");
				            out.println("</div>");
				            out.println("<div>");
				            out.println("<a class='h2'><h2>"+campList.get(i).getAcc_name()+"</h2></a>");
				            out.println("<p class='h3'><h3>"+campList.get(i).getSite_1()+"</h3></p>");
				            out.println("<div class='h3'><h3>"+campList.get(i).getSite_2()+"</h3></div>");
				            if(campList.get(i).getRating() != 0.0){
				            	out.println("<div class='rating'>★"+campList.get(i).getRating()+"</div>");
				            }else{
				            	out.println("<div class='rating'>평점 없음</div>");
				            }
				            out.println("<div><a href='camping_reserve.cp?reg_num_c="+campList.get(i).getReg_num_c()+"'>");
				            out.println("<button class='spots_button'>예약하기</button>");
				           	out.println("</a></div>");
				           	out.println("</div>");
				            out.println("</li>");
						}
					}
				}
%>
		          	</ul>
	          	</div >

	          	
	          	
	      	</div>
	      </section>
	      
	      <!-- 캠핑팁 유튜브API 추천 섹션 -->
	     <section class="special_section">
	      	<article>
	      	
	      		<div class="special_main">
				  <div class="special_title">
				    <button type="button" style="margin-right: 10px;" class="move_button"><span>☾</span></button>
				    <h2>CAMPING TIP</h2>
				    <button type="button" style="margin-left: 10px;" class="move_button"><span>☽</span></button>
				  </div>
				  <div class="special_body">
				    <div class="move_main">
				      <ul>
				        <% for (int i = 0; i < youtubeData.size(); i++) { %>
				        <li>
				          <div>
				            <div onclick="openModal('<%= youtubeData.get(i).getVideoId() %>');">
				              <input type="hidden" value="<%= i %>">
				              <!-- maxresdefault : 기본적으로는 default 이미지로 출력되는데 화질이 낮은편이므로 이 구문을 작성해주면 고화질로 썸네일이 출력됨 -->
				              <img src="https://i.ytimg.com/vi/<%= youtubeData.get(i).getVideoId() %>/maxresdefault.jpg" alt="Video Thumbnail">
				            </div>
				          </div>
				          <div class="move_main_body">
				          	<p><%= youtubeData.get(i).getVideoTitle() %></p>
				          </div>
				        </li>
				        <% } %>
				      </ul>
				    </div>
				  </div>
				</div>
	      		
	      	</article>	
	      </section>
	      
	      <!-- 모달 창 -->
			<section class="modal-section">
				<div id="video-modal" class="modal">
					<div class="modal-content">
					    <div id="close-modal" class="close">&times;</div>
					    <div id="video-container"></div>
					</div>
				</div>
			</section>		
		</div>
    </main>

    <!-- 푸터 영역 -->
    <jsp:include page="footer.jsp"/>
	<script src="js/camp_glam_index.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript" src="daterangepicker-Camping/daterangepicker.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
	<script src="https://www.youtube.com/iframe_api"></script>
  </body>
  
</html>
