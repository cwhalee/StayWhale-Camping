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
		obj.setUser_id(request.getParameter("id"));
		obj.setPost_body(request.getParameter("txt").replaceAll("<br>", "\r\n"));
		obj.setPost_title(request.getParameter("title"));
		obj.setPost_num(Integer.parseInt(request.getParameter("num")));
		obj.setPost_rating(Double.parseDouble(request.getParameter("reviewStar")));
		
		int pa = Integer.parseInt(request.getParameter("page"));
		
		data.review_update(obj);
		response.sendRedirect("../reviewSelec.xr?page="+pa+"");
	%>
</body>
</html>