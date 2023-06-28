<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
  	<jsp:useBean id="ob" class="DTO.Bulletin_Board_Camptip"> </jsp:useBean>
	<jsp:useBean id="db" class="BoardDAO.DBProcess_Camptip"> </jsp:useBean>
    
    <%
   int post_num = Integer.parseInt(request.getParameter("post_num")); 
    	String title = request.getParameter("title");
    	String body  = request.getParameter("body");
    	String category = request.getParameter("contType");
    	
    	ob.setPost_title(title);
    	ob.setPost_body(body);
    	ob.setPost_num(post_num);
    	ob.setPost_category(category);
    	
    	db.db_Update(ob);
    %>
    <script>
    	alert("수정이 완료되었습니다.");
    	location.href="../Camp_Glam_Index.jsp";
    </script>
