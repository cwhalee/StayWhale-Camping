<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="data" class="BoardDAO.DataProcess_Review"> </jsp:useBean>
	<jsp:useBean id="obj" class="DTO.Writer"> </jsp:useBean>
	<%
		obj.setPost_num(Integer.parseInt(request.getParameter("num")));
		String command = "delete from review where num='"+obj.getPost_num()+"'";
		
		data.review_delete(obj);
		response.sendRedirect("../reviewSelec.xr?page=1");
	%>
</body>
</html>