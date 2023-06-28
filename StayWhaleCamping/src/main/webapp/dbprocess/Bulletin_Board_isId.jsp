<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*"%>
	<% 
		request.setCharacterEncoding("utf-8");
		String id = (String)session.getAttribute("id");

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//※이 구문에서 오류가 난다면 mysql-connector-java.jar의 버전을 확인해주세요.(최신버전일시 cj포함 구버전일시 cj미포함)
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/websitedb","root","1234"); //※본인 DB로 연결 및 ID, Password확인해주세요
			if(conn == null) {
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			}
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select user_id from sign_up where user_id='"+ id + "'"); //※본인 DB에 맞는 필드명, 테이블인지 확인해주세요.
		if(rs.next()) {
			%>
			<script>
				location.href = "..\\Bulletin_Board_Insert.jsp";
			</script>
			<%
		} else {
			%>
			<script>
				alert("로그인을 먼저 진행해주세요.")
				location.href = "..\\Login.jsp";
			</script>
			<%
		}
		} finally {
			try {
				stmt.close();
			} catch (Exception ignored) {

			} try {
				conn.close();
			} catch (Exception ignored) {

			}
		}
	%>