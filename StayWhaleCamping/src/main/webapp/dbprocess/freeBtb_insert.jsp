<%@ page language = "java" contentType = "text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import = "java.sql.*"%>
<!DOCTYPE html>
<html>
<head><!-- 문서정보-->
	<meta charset="utf-8">
	<title>회원가입 db</title>
</head>
<%
String id =(String)session.getAttribute("id");
String type = request.getParameter("contType");
String title = request.getParameter("title");
String body = request.getParameter("body");




Connection conn = null;
Statement stmt = null;

try{
	Class.forName("com.mysql.cj.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/websitedb", "root", "1234");
	if(conn == null){
		throw new Exception("데이터베이스에 연결할 수 없습니닷.");
	}
		stmt = conn.createStatement();
		String command = "insert into bulletin_board_free (user_id, post_title, post_body, post_category, post_date)"+
		"values('"+id+"',"+ "'"+title+"',"+"'"+body+"',"+"'"+type+"', now());";
	
		
                                         
		int rowNum = stmt.executeUpdate(command);

		if(rowNum<1){
			throw new Exception("데이터를 DB에 입력할 수 없습니다.");

		}else{%>
		<script>
		alert("게시되었습니다.");
		location.href="../FreeBtb.jsp";
		</script>
			
			
		<% }
	}finally{
		try{
			stmt.close();
		}catch(Exception ignored){ 

		}
		try{
			conn.close();

		}catch(Exception ignored){

		}
	}


%>

<body> 
	


	
	



</body>
</html>