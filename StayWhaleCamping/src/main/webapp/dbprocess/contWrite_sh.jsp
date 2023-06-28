<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

// 세션에 따라 상이한 페이지로 이동을 위한  jsp파일 

String id = (String)session.getAttribute("id");

if(id != null){
	response.sendRedirect("../ContWrite.jsp");
	
}else{%>

	<script>
	alert('로그인 후 이용할 수 있습니다.');
	location.href = '../Login.jsp';
	
	
	</script>   <!-- 1)  ""안에는 무조껀 ''작은짜옴표임.2) jsp 코드 블럭과 html사이에는 우선순위가 존재한다. jsp가 먼저임. -->
	
<%}

%>