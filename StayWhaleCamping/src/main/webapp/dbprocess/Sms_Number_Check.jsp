<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ExamDAO.SmsApi" %>
<% request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	
	String phone = (String)request.getParameter("phone");
	//int check = 1;
	SmsApi sa = new SmsApi();
	String number = sa.numbersend(phone);
	//Connection conn = null;
	//Statement stmt = null;
	try
	{
		//Class.forName("com.mysql.cj.jdbc.Driver");//※이 구문에서 오류가 난다면 mysql-connector-java.jar의 버전을 확인해주세요.(최신버전일시 cj포함 구버전일시 cj미포함)
		//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/websitedb", "root", "1234");//※본인 DB로 연결 및 ID, PassWord확인해주세요.
		//if(conn == null)
		//{
		//	throw new Exception("데이터베이스에 연결할 수 없습니다.");
		//}
		//stmt = conn.createStatement();
		
		out.println(number);
		// request.setAttribute("user_id", idList);
		// request.setAttribute("nickname", nicknameList);
		
		// RequestDispatcher dispatcher = request.getRequestDispatcher("Sign_Up.jsp");
		// // RequestDispatcher dispatcher = request.getRequestDispatcher("DataProcessUpdate.jsp");
		// dispatcher.forward(request, response);
	}
	catch(Exception e)
	{
		
	}
	finally
	{
		
	}
%>