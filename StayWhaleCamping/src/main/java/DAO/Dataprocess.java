package DAO;

import java.sql.*;
import java.util.*;
import DTO.Bulletin_Board;
import DTO.Bulletin_Board_Free;

public class Dataprocess 
{
	Connection conn = null;
	Statement stmt = null;
	public Dataprocess()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(Exception e)
		{
			
		}
	}
	public void conn()
	{
		try
		{
//			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/websitedb", "root", "1234");
			if(conn == null)
			{
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			}
			stmt = conn.createStatement();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void diconn()
	{
		try
		{
			stmt.close();
			conn.close();
		}
		catch(Exception ignored)
		{

		}
	}
	
	public ArrayList<Bulletin_Board> boardList() //전체 가져오는 메소드
	{
		conn();
		ArrayList<Bulletin_Board> boardList = new ArrayList<Bulletin_Board>();
//		Connection conn = null;
//		Statement stmt = null;
		try
		{
//			conn();
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbase", "root", "1234");
//			if(conn == null)
//			{
//				throw new Exception("데이터베이스에 연결할 수 없습니다.");
//			}
//			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select post_num, post_title, user_id, DATE_FORMAT(post_date, '%Y.%m.%d') as post_date from bulletin_board_inquiry where post_category='1:1 문의' order by post_num desc");//값을 가져올때 쓰는 것이 ResultSet
			
			while(rs.next())
			{
				Bulletin_Board board = new Bulletin_Board();
				
				board.setPost_num(rs.getInt("post_num"));
				board.setPost_title(rs.getString("post_title"));
				board.setUser_id(rs.getString("user_id"));
				board.setPost_date(rs.getString("post_date"));
				
				boardList.add(board);
			}
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			diconn();
		}
		return boardList;
	}
	
	public ArrayList<Bulletin_Board> boardList(String id) //특정 ID값에 맞는 게시글만 가져오는 메소드
	{
		conn();
		ArrayList<Bulletin_Board> boardList = new ArrayList<Bulletin_Board>();
//		Connection conn = null;
//		Statement stmt = null;
		try
		{
//			conn();
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbase", "root", "1234");
//			if(conn == null)
//			{
//				throw new Exception("데이터베이스에 연결할 수 없습니다.");
//			}
//			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select post_num, post_title, user_id, DATE_FORMAT(post_date, '%Y.%m.%d') as post_date from bulletin_board_inquiry where user_id='"+ id +"' and post_category='1:1 문의' order by post_num desc");//값을 가져올때 쓰는 것이 ResultSet
			
			while(rs.next())
			{
				Bulletin_Board board = new Bulletin_Board();
				
				board.setPost_num(rs.getInt("post_num"));
				board.setPost_title(rs.getString("post_title"));
				board.setUser_id(rs.getString("user_id"));
				board.setPost_date(rs.getString("post_date"));
				
				boardList.add(board);
			}
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			diconn();
		}
		return boardList;
	}
	
	public void boardDelete(int post_num)
	{
		conn();
//		diconn();
//		Connection conn = null;
//		Statement stmt = null;
		try
		{
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbase", "root", "1234");
//			if(conn == null)
//			{
//				throw new Exception("데이터베이스에 연결할 수 없습니다.");
//			}
//			stmt = conn.createStatement();
			stmt.executeUpdate("delete from bulletin_board_inquiry where post_num='"+post_num+"'");//값을 가져올때 쓰는 것이 ResultSet
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			diconn();
		}
	}
	
	public void boardInsert(Bulletin_Board board)
	{
		conn();
//		diconn();
//		Connection conn = null;
//		Statement stmt = null;
	
		try
		{
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbase", "root", "1234");
//			if(conn == null)
//			{
//				throw new Exception("데이터베이스에 연결할 수 없습니다.");
//			}
//			stmt = conn.createStatement();
			stmt.executeUpdate("insert into bulletin_board_inquiry (user_id, post_title, post_body, post_category, post_date) values ('"
					+ board.getUser_id() +"', '" +board.getPost_title()+"', '"
					+ board.getPost_body() +"', '" +board.getPost_category()+"', now())");//값을 가져올때 쓰는 것이 ResultSet
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			diconn();
		}
	}
	
	public Bulletin_Board boardDetail(int post_num)
	{
		conn();
		Bulletin_Board boardDetail = new Bulletin_Board();
//		Connection conn = null;
//		Statement stmt = null;
		try
		{
//			conn();
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbase", "root", "1234");
//			if(conn == null)
//			{
//				throw new Exception("데이터베이스에 연결할 수 없습니다.");
//			}
//			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select post_title, user_id, post_date, post_body from bulletin_board_inquiry where post_num="+post_num);//값을 가져올때 쓰는 것이 ResultSet
			
			while(rs.next())
			{	
				boardDetail.setPost_title(rs.getString("post_title"));
				boardDetail.setUser_id(rs.getString("user_id"));
				boardDetail.setPost_date(rs.getString("post_date"));
				boardDetail.setPost_body(rs.getString("post_body"));
			}
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			diconn();
		}
		return boardDetail;
	}
	
	public void boardUpdate(Bulletin_Board board)
	{
		conn();
//		diconn();
//		Connection conn = null;
//		Statement stmt = null;
	
		try
		{
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbase", "root", "1234");
//			if(conn == null)
//			{
//				throw new Exception("데이터베이스에 연결할 수 없습니다.");
//			}
//			stmt = conn.createStatement();
			stmt.executeUpdate("update bulletin_board_inquiry set post_title='" +board.getPost_title()+"', post_body='"+board.getPost_body()+"', post_category='"+board.getPost_category()+"', post_date=now() where post_num="+board.getPost_num());//값을 가져올때 쓰는 것이 ResultSet
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			diconn();
		}
	}
	
public ArrayList<Bulletin_Board> contentSearchshow(int option, String search, String id) {
		
	ArrayList<Bulletin_Board> list = new ArrayList<Bulletin_Board>();
		
		try{
			conn();
			stmt = conn.createStatement();
			ResultSet rs = null;
			
			if(option == 1) {  // 1.제목, 2.아이디, 3,아이디+제목
				
				rs = stmt.executeQuery("select user_id, post_title,DATE_FORMAT(post_date,'%y.%m.%d') as post_date, post_num from bulletin_board_inquiry"
						+ " where post_title like '%"+search+"%' and post_category='1:1 문의' and user_id='"+id+"' order by post_num desc");
				
			}else if(option ==2) {
				rs = stmt.executeQuery("select user_id, post_title, DATE_FORMAT(post_date,'%y.%m.%d') as post_date, post_num from bulletin_board_inquiry"
						+ " where user_id ='"+search+"' and post_category='1:1 문의' order by post_num desc");
				
			}else {
				rs = stmt.executeQuery("select user_id, post_title, DATE_FORMAT(post_date,'%y.%m.%d') as post_date,post_num from bulletin_board_inquiry "
						+ " where (post_title like '%"+search+"%' or user_id ='"+search+"') and post_category='1:1 문의' order by post_num desc");
			}
			
			
			   
			while(rs.next()) {
				Bulletin_Board data = new Bulletin_Board();
				
				data.setPost_num(rs.getInt("post_num"));
				data.setPost_date(rs.getString("post_date"));
				data.setUser_id(rs.getString("user_id"));
				data.setPost_title(rs.getString("post_title"));
		
			
				
				list.add(data);
		
				
		
			}
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("예외발생");
	
			
			}finally {
				diconn();
			}
		return list;
		
		
		}
	
}
