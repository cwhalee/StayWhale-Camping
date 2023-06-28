<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*" %>
<%@ page import="java.sql.*"%>
<%@ page import="vo.Accmodation_Camping" %>
<%@ page import="vo.Room_Info_Camping" %>
<%@ page import="vo.Camping_Like" %>
<%@ page import="vo.Bulletin_Board_Review" %>
<% request.setCharacterEncoding("UTF-8");%>
<html>
<head>
  <meta charset="utf-8">
  <title>STAY WHALE || Camp_Glam</title>
  <link rel="stylesheet" type="text/css" href="css/camp_glam_reserve.css">
  <link rel="stylesheet" type="text/css" href="daterangepicker-Camping/daterangepicker.css"/>
  <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
</head>
<body>
 <%
    request.setCharacterEncoding("utf-8");
    String id = (String)session.getAttribute("id");
    
    String reg_num_c = request.getParameter("reg_num_c");
    DecimalFormat df = new DecimalFormat("#,###");
    ArrayList<Object> articleList = (ArrayList<Object>)request.getAttribute("articleList");
    Camping_Like like_check = (Camping_Like)request.getAttribute("like_check");
    pageContext.setAttribute("like_check",like_check.getLike_check());
    
    ArrayList<Accmodation_Camping> campList = (ArrayList<Accmodation_Camping>) articleList.get(0);
    ArrayList<Room_Info_Camping> roomList = (ArrayList<Room_Info_Camping>) articleList.get(1);
    ArrayList<Bulletin_Board_Review> reviewList = (ArrayList<Bulletin_Board_Review>) articleList.get(2);
    
    String st="";
	st = campList.get(0).getAcc_picture();
	String[] stlist = st.split(",");
	
	/* roomList의 facility_picture에는 각각의 객실마다 다른 객실의 이미지파일명이 여러개 있으므로
		split으로 직접적인 데이터 구분하기전에 += 으로 한줄의 String데이터로 다합쳐준 후 같이 입력한
		구분자를 통해 split을 해주면 아래의 배열 str에는 각 객실의 이름들이 0~3번까지 입력되어있음. 
	*/
	String room_imgs="";
	for(int i = 0; i< roomList.size();i++){
		room_imgs += roomList.get(i).getFacility_picture()+"/";
	}
	String[] room_array = room_imgs.split("/");

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

    <!-- 메인 -->
    <main>
		<div class="top">
			<div class="top_info_left">
				  <div class="slider-for">
				  <%
				  	for(int i=0; i<stlist.length; i++) {
				  		out.println("<div class='item'><img src='image/" + stlist[i] + "'></div>");
				  	}
				  %>
				  </div>
				  <div class="sub_image_wrap">
					  <span class="prevArrow"><img src="image/arrow_left.png"></span>
						  <div class="slider-nav">
							<%
							 	for(int i=0; i<stlist.length; i++) {
							 		out.println("<div class='item'><img src='image/" + stlist[i] + "'></div>");
								}
							%>
						  </div>
					  <span class="nextArrow"><img src="image/arrow_right.png"></span>
				  </div>
			</div>
			
			<div class="top_right">
				<div id="top_info_right">
					<div class="top_right_name">
						<h1><% out.println(campList.get(0).getAcc_name()); %></h1>
					</div>
					<div class="top_right_location">
  						<h2><% out.println(campList.get(0).getSite_1() + " " + campList.get(0).getSite_2() + " " + campList.get(0).getLocation()); %></h2>
					</div>
					<div class="top_right_detail">
						<h3>캠핑장 설명</h3><br>
						<% out.println(campList.get(0).getDetail()); %>
					</div>
					<div class="top_right_any">
						<h3>캠핑장 공용 서비스</h3><br/>
						<% out.println(campList.get(0).getFacility_list());%>
					</div>
					<div class="like_wrap" id="top_info_right">
						<div class="date_box">
					        <h3>이용날짜</h3><br/>
					        <input type="text" class="datepicker" id="datepicker" name="datepicker">
					        
					        <input type="hidden" id="hidden_start_date" name="start_date">
					        <input type="hidden" id="hidden_end_date" name="end_date">
						</div>
						<div class="like_box" id="like_box">
					<%
						if (like_check.getLike_check() == 1) {
							out.println("<div class='likeCheck' onclick=\"unlike('" + id + "','" + reg_num_c + "'); return false;\">");
							out.println("<img class='likeicon' src='image/camping_like.png'></div>");
							out.println("<div class='like_text'>해제</div>");
						} else {
							out.println("<div class='likeCheck' onclick=\"like('" + id + "','" + reg_num_c + "'); return false;\">");
							out.println("<img class='likeicon' src='image/camping_unlike.png'></div>");
							out.println("<div class='like_text'>위시리스트</div>");
						}

					%>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		
		<div class="contant_nav">
			<button type="button" id="reserve" class="nav_button" data-content-id="1">객실 안내 / 예약</button>
			<button type="button" id="review" class="nav_button" data-content-id="2">리 뷰</button>
		</div>
      <!-- 객실 안내 / 예약 출력 -->
		<div class="main-content">
			
      		<section id="room_section" class="room_section" style="display: block">
        		<div class="room_title">
        			<h2>객실 정보</h2>
        		</div>
        		<div class="room_content">
	        		<div class="room_container">
			        	<ul>			        		
						<%
						 String[] roomimg =null;
							for(int i=0; i<roomList.size(); i++) {
								out.println("<li><div>");
								for(int x=0;x<room_array.length;x++){
									roomimg = room_array[i].split(",");
								}
								out.println("<div class='btn-modal' id='btn-modal"+i+"'><img src='image/"+roomimg[0]+"' alt='이미지 준비중' ></div>");
								out.println("<div>");
							    out.println("<div><input id='facility_num_c' class='facility_num_c' type='hidden' value="+roomList.get(i).getFacility_num_c()+"></div>");
							    out.println("<div class='room-box'>객실명 : <b>"+roomList.get(i).getFacility_name()+"</b></div>");
							    out.println("<div class='room-box'>기준 인원 : <b>"+roomList.get(i).getStandard_amount()+"명</b></div>");
							    out.println("<div class='room-box'><b id='price' class='price'>"+df.format(roomList.get(i).getPrice())+"원</b></div>");
							    out.println("</div>");
							    out.println("<div>");
							    out.println("<button type='button' class='reserve_button' id='reserve_button' onclick=\"reserve_insert('" + id + "', " + i + ")\">");
							    out.println("예약하기</button></div>");
							    out.println("</div></li>");
							}							
						%> 					 
						</ul>
			       	</div>
			     </div>
			     
			</section>
			
			<!-- 모달 section -->
			<section>				
			<% for (int i = 0; i < room_array.length; i++) {
	            String[] slick_img = room_array[i].split(","); // 각각의 객실에 대한 이미지 파일명들 가져오기
	        %>
				<div id="modal<%= i %>" class="modal-overlay">
				    <div class="modal-window">
				        <div class="modal-title">객실정보</div>
				        <div class="close-area">ESC</div>
				        
				        <div class="modal-content">
				        
				           <div class="modal-left">
				           
				               <div class="slider-for2">
				                    <% for (int z = 0; z < slick_img.length; z++) {%>
							             <div class='item2'><img src="image/<%= slick_img[z]%>"></div>
							        <% } %>
				                </div>
				                <div class="modal-right">
				                	<div class="modal-label">객실명</div>
				                    <div class="modal-box"><strong><%= roomList.get(i).getFacility_name() %></strong></div>
				                    <div class="modal-label">주소</div>
				                    <div class="modal-box"><strong><%= campList.get(0).getSite_1() + " " + campList.get(0).getSite_2() + " " + campList.get(0).getLocation() %></strong></div>
				                    <div class="modal-label">전화번호</div>
				                    <div class="modal-box"><strong><%= campList.get(0).getTel_no() %></strong></div>
				                    <div class="modal-label">객실별 이용 가능 서비스</div>
				                    <div class="modal-box"><strong><%= roomList.get(i).getRoom_detail()%></strong></div>
				                    <div class="modal-label">기준인원</div>
				                    <div class="modal-box"><strong><%= roomList.get(i).getStandard_amount() %> 명</strong></div>
				                    <div class="modal-check">※추가인원 있을시 추가 비용 발생합니다.※</div>
				                </div>
				                <div class="sub_image_wrap2">
								  <span class="prevArrow2"><img src="image/arrow_left.png"></span>
								  <div class="slider-nav2">
								    <% for (int z = 0; z < slick_img.length; z++) { %>
								      <div class="item2"><img src="image/<%= slick_img[z] %>"></div>
								    <% } %>  
								  </div>
								  <span class="nextArrow2"><img src="image/arrow_right.png"></span>
								</div>
				            </div>
				        </div>
				    </div>
				</div>
				<% 
					}
				%>

			
		</section>
			
		<!-- 리뷰 section -->	
		<!-- if문으로 리뷰 없는 경우 없다고 띄워줘야함 -->
		<section id="review_info" class="review_info" style="display:none">
				<div class="review_info_title">
        			<h2>캠핑장 리뷰</h2>
        		</div>
        		<div class="review_infomation">
        			<ul>
        			<% for(int i=0; i<reviewList.size(); i++) { %>
        				<li>
		        			<div class="review_div1"><strong>
		        				작성자 <% out.println(reviewList.get(i).getUser_id());%>&nbsp; 
		        				★<% out.println(reviewList.get(i).getPost_rating());%>&nbsp; 
								<% out.println(reviewList.get(i).getPost_date().toString().substring(0, 10)); %> &nbsp; 
		        				</strong>
		        			</div>
		        			<div class="review_div2">
		        				<% out.println(reviewList.get(i).getPost_title());%>
		        			</div>
		        			<div class="review_div3">
		        				<img alt="이미지 준비중" src="CampingUpload/<% out.println(reviewList.get(i).getPost_file());%>">
							</div>
		        			<div class="review_div4">
		        				<% out.println(reviewList.get(i).getPost_body());%><br/><br/>
		        			</div>
        				</li>
        				<% } %>
        			</ul>	
        		</div>
			</section>
		</div>
    </main>

    <!-- 푸터 영역 -->
    <jsp:include page="footer.jsp"/>
    <!-- 스크립트 -->
    <script type="text/javascript" src="js/camp_glam_reserve.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript" src="daterangepicker-Camping/daterangepicker.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script> 
	
  </body>
</html>
