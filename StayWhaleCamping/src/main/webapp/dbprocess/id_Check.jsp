<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*"%>
	<% 
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		Connection conn = null;
		Statement stmt = null;
		String str = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/websitedb","root","1234");
			if(conn == null) {
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			}
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select user_id from sign_up where user_id='"+id+"';");
		if(rs.next()) {
			out.println("1");
		}else{
			out.println("0");
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