<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <jsp:useBean class="DTO.Bulletin_Board_Free" id="data"></jsp:useBean>
    <jsp:useBean class="BoardDAO.FreeBtbcont_show" id="process"></jsp:useBean>
    
    <%
   int post_num = Integer.parseInt(request.getParameter("post_num")); 
    	String title = request.getParameter("title");
    	String body  = request.getParameter("body");
    	
    	data.setPost_title(title);
    	data.setPost_body(body);
    	data.setPost_num(post_num);
    	
    	process.contentRevise(data);
    %>
    <script>
    	alert("수정이 완료되었습니다.");
    	location.href="../FreeBtb.jsp";
    </script>
