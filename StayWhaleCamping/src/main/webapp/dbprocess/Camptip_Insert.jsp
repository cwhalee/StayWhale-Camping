<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>INSERT</title>
</head>
<body>
	<jsp:useBean id="ob" class="DTO.Bulletin_Board_Camptip"> </jsp:useBean>
	<jsp:useBean id="db" class="BoardDAO.DBProcess_Camptip"> </jsp:useBean>
	
	<%
		out.println(ob);
		ob.setUser_id(request.getParameter("userid"));
		ob.setPost_title(request.getParameter("title"));
		ob.setPost_body(request.getParameter("write_body").replace("\r\n","<br>"));
		ob.setPost_category(request.getParameter("contType"));
		ob.setPost_img(request.getParameter("file"));
		db.db_Insert(ob);
		response.sendRedirect("../Camp_Glam_Index.jsp");
	%>
</body>
</html>