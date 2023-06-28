<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
    <%@ page import="DTO.Bulletin_Board" %>
    <%@ page import="ExamDAO.Dataprocess" %>
    <%
    	Bulletin_Board board = new Bulletin_Board();
    	Dataprocess dp = new Dataprocess();
    	
    	board.setPost_num(Integer.parseInt(request.getParameter("post_num")));
    	board.setUser_id((String)session.getAttribute("id"));
    	board.setPost_title((String)request.getParameter("title"));
    	board.setPost_body((String)request.getParameter("body"));
    	board.setPost_category((String)request.getParameter("category"));
    	
    	dp.boardUpdate(board);
    	%>
    	<%if(true) {%>
    	<script>
    	alert("수정되었습니다.")
    	location.href = "../Bulletin_Board_Inquiry.jsp";
    	</script>
    	<%} %>
    	<%/* 
    	response.sendRedirect("../Bulletin_Board.jsp");
    */%>
