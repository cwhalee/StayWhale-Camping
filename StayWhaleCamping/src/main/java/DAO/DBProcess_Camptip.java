package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.Bulletin_Board_Camptip;

public class DBProcess_Camptip {
	private Connection conn;
	private ResultSet rs;
	private Statement stmt;
	
	//DB 연동
	public DBProcess_Camptip() {		
		try {
			String dbURL="jdbc:mysql://localhost:3306/websitedb";	
			String dbID="root";
			String dbPassword="1234";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn =DriverManager.getConnection(dbURL,dbID,dbPassword); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public void db_Insert(Bulletin_Board_Camptip obj) {
		try {			
			stmt=conn.createStatement();
			String command = "insert into Bulletin_Board_Camptip values(0"+",'"+obj.getUser_id()+"',now(),'"+obj.getPost_title()+"','"+obj.getPost_body()+"','"+obj.getPost_category()+"','"+obj.getPost_img()+"',0,0);";
			stmt.executeUpdate(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void db_Delete(Bulletin_Board_Camptip obj) {
		try {
			stmt = conn.createStatement();
			String command = "delete from Bulletin_Board_Camptip where post_num="+obj.getPost_num()+"";
			stmt.executeUpdate(command);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void db_Update(Bulletin_Board_Camptip obj) {
		try {
			stmt = conn.createStatement();
			String command = "update Bulletin_Board_Camptip set post_date=now(),post_title='" + obj.getPost_title() + "',post_body='" +  obj.getPost_body() + "',post_category='"+obj.getPost_category()+"',post_img='"+obj.getPost_img()+"' where post_num = " + obj.getPost_num() + ";";
			stmt.executeUpdate(command);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public ArrayList<Bulletin_Board_Camptip> db_SelectList() {
		ArrayList<Bulletin_Board_Camptip> list = new ArrayList<Bulletin_Board_Camptip>();
			try {
				stmt=conn.createStatement();
				rs = stmt.executeQuery("select post_num, user_id, DATE_FORMAT(post_date, '%y-%m-%d') as post_date, post_title, post_category, post_readcount, post_like from Bulletin_Board_Camptip order by post_num desc;");
				while(rs.next()) {
					Bulletin_Board_Camptip bbc = new Bulletin_Board_Camptip();
					bbc.setPost_num(rs.getInt("post_num"));
					bbc.setUser_id(rs.getString("user_id"));
					bbc.setPost_date(rs.getString("post_date"));
					bbc.setPost_title(rs.getString("post_title"));
					bbc.setPost_category(rs.getString("post_category"));
					bbc.setPost_readcount(rs.getInt("post_readcount"));
					bbc.setPost_like(rs.getInt("post_like"));
					list.add(bbc);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
	
	
	public ArrayList<Bulletin_Board_Camptip> db_SelectAll(int num) {
		ArrayList<Bulletin_Board_Camptip> list = new ArrayList<Bulletin_Board_Camptip>();
			try {
				stmt=conn.createStatement();
				rs = stmt.executeQuery("select post_num, user_id, DATE_FORMAT(post_date, '%y-%m-%d') as post_date, post_title, post_body, post_category, post_img from Bulletin_Board_Camptip where post_num=" + num + ";");
				while(rs.next()) {
					Bulletin_Board_Camptip bbc = new Bulletin_Board_Camptip();
					bbc.setPost_num(rs.getInt("post_num"));
					bbc.setUser_id(rs.getString("user_id"));
					bbc.setPost_date(rs.getString("post_date"));
					bbc.setPost_title(rs.getString("post_title"));
					bbc.setPost_body(rs.getString("post_body"));
					bbc.setPost_category(rs.getString("post_category"));
					bbc.setPost_img(rs.getString("post_img"));
					list.add(bbc);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return list;
		}
	
	public ArrayList<Bulletin_Board_Camptip> db_SelectBefore(int post_num) {
		ArrayList<Bulletin_Board_Camptip> list = new ArrayList<Bulletin_Board_Camptip>();
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from Bulletin_Board_Camptip where post_num < "+post_num+" order by post_num desc limit 1");        
			while(rs.next()) {
				Bulletin_Board_Camptip bbc = new Bulletin_Board_Camptip();
				bbc.setPost_num(rs.getInt("post_num"));
				bbc.setPost_category(rs.getString("post_category"));
				bbc.setPost_title(rs.getString("post_title"));
				bbc.setPost_body(rs.getString("post_body"));
				bbc.setUser_id(rs.getString("user_id"));
					
				list.add(bbc);
				}
			
			}catch (Exception e){
				e.printStackTrace();
			}
			return list;
	}
	
	public ArrayList<Bulletin_Board_Camptip> db_SelectAfter(int post_num) {
		ArrayList<Bulletin_Board_Camptip> list = new ArrayList<Bulletin_Board_Camptip>();
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from Bulletin_Board_Camptip where post_num > "+post_num+" order by post_num asc limit 1");        
			while(rs.next()) {
				Bulletin_Board_Camptip bbc = new Bulletin_Board_Camptip();
				
				bbc.setPost_num(rs.getInt("post_num"));
				bbc.setPost_category(rs.getString("post_category"));
				bbc.setPost_title(rs.getString("post_title"));
				bbc.setPost_body(rs.getString("post_body"));
				bbc.setUser_id(rs.getString("user_id"));
					
				list.add(bbc);
				}
			
			}catch (Exception e){
				e.printStackTrace();
			}
			return list;
	}

	
	
	//	아직 안씀
//	public String board_prev(int num) {
//		String title = "";
//			try {	
//				stmt=conn.createStatement();
//				rs = stmt.executeQuery("select boardTitle from Bulletin_Board_Camptip where boardNum = (select boardNum from Bulletin_Board_Camptip where boardNum >" + num +" order by boardNum desc limit 1);");
//				while(rs.next()) {
//				title = (rs.getString("title"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//			return title;
//	}
//	
//	public String board_next(int num) {
//		String title = "";
//			try {
//				stmt=conn.createStatement();
//				rs = stmt.executeQuery("select boardTitle from Bulletin_Board_Camptip where boardNum = (select boardNum from Bulletin_Board_Camptip where boardNum >" + num + " order by boardNum asc limit 1);");
//				while(rs.next()) {
//				title = (rs.getString("title"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//			return title;
//	}
//	public int prev_next(String title) {
//			int num = 0;
//			try {
//				stmt=conn.createStatement();
//				rs = stmt.executeQuery("select boardNum, boardTitle from Bulletin_Board_Camptip where boardTitle='" + title + "';");
//				while(rs.next()) {
//					num = rs.getInt("num");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
//			return num;
//		}
}
