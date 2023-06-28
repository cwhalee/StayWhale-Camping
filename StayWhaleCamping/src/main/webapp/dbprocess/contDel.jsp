<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="ExamDAO.FreeBtbcont_show" %>
<jsp:useBean class="BoardDAO.FreeBtbcont_show" id="data"></jsp:useBean>
<%
	int post_num = Integer.parseInt(request.getParameter("post_num"));

	data.contentDel(post_num);	
%>

<script>
	alert("삭제되었습니다.");
	location.href="../FreeBtb.jsp";
</script>