<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.*" %>
<%@ page import="vo.Reserve_Camping" %>
<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>STAY WHALE</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String id = (String)session.getAttribute("id");
		String room_c = request.getParameter("facility_num_c");
		int price = Integer.parseInt(request.getParameter("price"));
		NumberFormat formatter = new DecimalFormat("#,###"); // 형식화 객체 생성
		String formatprice = formatter.format(price);
		
		ArrayList <Reserve_Camping> dateList = (ArrayList<Reserve_Camping>)request.getAttribute("dateList");
	%>
	<%
		if(id == null) {
	%>
		<jsp:include page="Header_Login_Fail.jsp" >
			<jsp:param name="id" value="<%= id%>"/>
		</jsp:include>
		<script>
			alert("로그인 후 이용가능합니다.");
			history.back();
		</script>
	<%
	} else {
	%>
		<jsp:include page="Header_Login_Success.jsp"/>
	<%
	}
	%>

	<div class="section_wrap"></div>
	<!-- 여기부터 작성 -->
	<main>
		<div>
			<section id="modal" class="moday_overlay" >	
				<div class="modal-window">
					<div>
						<h2>예약 및 결제</h2>
					</div>
					
					<form action="reserve_insert.cp" id="reserve_form" name="reserve_form" method="post">
						<table id='reserve_table'>
							<tr>
								<td><input type="hidden" name="facility_num_c" value="<%= room_c%>"></td>
							</tr>
							<tr>
								<td colspan="2">예약자<input id ="userid" name="user_id" type="text" value="<%= id%>" readonly></td>
							</tr>
							<tr>
								<td colspan="2">
								체크인<input type="text" id="check_in" name="check_in" readonly placeholder="날짜 선택" />
								 	<input type="hidden" id="hidden_start_date" name="start_date">
  									<input type="hidden" id="hidden_end_date" name="end_date">
								</td>
							</tr>
							<tr>
								<td colspan="2">결제수단
									<select id='pay_option' name ='pay_option'>
										<option>신용카드</option>
										<option>계좌이체</option>
										<option>카카오페이</option>
										<option>네이버페이</option>
									</select>
								</td>
								<td>
									가격
									<input type="text" name="pay_view" value="<%= formatprice%>">원
									<input type="hidden" name="pay_price" value="<%= price%>">
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<input id='confirm' type="submit" value="예약하기" class="button">
									<input id='cont_cancel' type="button" value="취소" class="button" onclick="location.href='Camp_Glam_Index.cp'">
								</td>
							</tr>
						</table>
					</form>
					
				</div>
			</section>
					
		</div>
	</main>
          
	<jsp:include page="footer.jsp"/>
</body>

<link rel="stylesheet" type="text/css" href="css/camp_glam_.css">
<script type="text/javascript" src="js/camp_glam_payment.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript" src="daterangepicker-master/daterangepicker.js"></script>
	<link rel="stylesheet" type="text/css" href="daterangepicker-master/daterangepicker.css"/>
</html>