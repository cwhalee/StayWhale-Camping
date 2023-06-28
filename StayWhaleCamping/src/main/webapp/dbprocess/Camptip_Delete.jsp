<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>DELETE</title>
</head>
<body>
	<jsp:useBean id="ob" class="DTO.Bulletin_Board_Camptip"> </jsp:useBean>
	<jsp:useBean id="db" class="BoardDAO.DBProcess_Camptip"> </jsp:useBean>
	
	<%
		ob.setPost_num(Integer.parseInt(request.getParameter("post_num")));
		
		db.db_Delete(ob);
		response.sendRedirect("../Camp_Glam_Index.jsp");
	%>
</body>
</html>