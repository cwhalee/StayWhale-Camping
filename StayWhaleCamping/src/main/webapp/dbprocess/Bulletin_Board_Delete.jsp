<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
    <%@ page import="DTO.Bulletin_Board" %>
    <%@ page import="ExamDAO.Dataprocess" %>
    <%
    	int post_num = Integer.parseInt((String)request.getParameter("post_num"));
    	Dataprocess dp = new Dataprocess();
    	
    	dp.boardDelete(post_num);
    	%>
    	<%if(true) {%>
    	<script>
    	alert("삭제되었습니다.")
    	location.href = "../Bulletin_Board_Inquiry.jsp";
    	</script>
    	<%} %>
    	<%/* 
    	response.sendRedirect("../Bulletin_Board.jsp");
    */%>
