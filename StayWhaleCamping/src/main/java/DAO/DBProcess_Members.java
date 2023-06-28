package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.Bulletin_Board_Camptip;
import DTO.Members;

public class DBProcess_Members {
	private Connection conn;
	private ResultSet rs;
	private Statement stmt;
	
	//DB 연동
	public DBProcess_Members() {		
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
		
//	public void db_Insert(Members obj) {
//		try {			
//			stmt=conn.createStatement();
//			String command = "insert into Bulletin_Board_Camptip values(0"+",'"+obj.getUser_id()+"',now(),'"+obj.getPost_title()+"','"+obj.getPost_body()+"','"+obj.getPost_category()+"','"+obj.getPost_img()+"',0,0);";
//			stmt.executeUpdate(command);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}	
	
//	public void db_Delete(Members obj) {
//		try {
//			stmt = conn.createStatement();
//			String command = "delete from sign_up where user_id="+obj.getUser_id()+"";
//			stmt.executeUpdate(command);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//	}
//	
//	public void db_Update(Members obj) {
//		try {
//			stmt = conn.createStatement();
//			String command = "update sign_up set post_date=now(),post_title='" + obj.getPost_title() + "',post_body='" +  obj.getPost_body() + "',post_category='"+obj.getPost_category()+"',post_img='"+obj.getPost_img()+"' where post_num = " + obj.getPost_num() + ";";
//			stmt.executeUpdate(command);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//	}

	public ArrayList<Members> db_SelectMain(String id) {
		ArrayList<Members> list = new ArrayList<Members>();
			try {
				stmt=conn.createStatement();
				rs = stmt.executeQuery("select user_id, name, nickname, hp from sign_up where user_id='"+id+"';");
				while(rs.next()) {
					Members bbc = new Members();
					bbc.setUser_id(rs.getString("user_id"));
					bbc.setName(rs.getString("name"));
					bbc.setNickname(rs.getString("nickname"));
					bbc.setHp(rs.getString("hp"));
					list.add(bbc);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
	public ArrayList<Bulletin_Board_Camptip> db_SelectBoard(String id) {
		ArrayList<Bulletin_Board_Camptip> list = new ArrayList<Bulletin_Board_Camptip>();
			try {
				stmt=conn.createStatement();
				rs = stmt.executeQuery("select post_num, DATE_FORMAT(post_date, '%y-%m-%d') as post_date, post_title, post_category, post_readcount, post_like from Bulletin_Board_Camptip where user_id='"+id+"' order by post_num desc limit 1;");
				while(rs.next()) {
					Bulletin_Board_Camptip bbc = new Bulletin_Board_Camptip();
					bbc.setPost_num(rs.getInt("post_num"));
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
	
	
//	public ArrayList<Members> db_SelectAll(int num) {
//		ArrayList<Members> list = new ArrayList<Members>();
//			try {
//				stmt=conn.createStatement();
//				rs = stmt.executeQuery("select post_num, user_id, DATE_FORMAT(post_date, '%y-%m-%d') as post_date, post_title, post_body, post_category, post_img from Bulletin_Board_Camptip where post_num=" + num + ";");
//				while(rs.next()) {
//					Members bbc = new Members();
//					bbc.setPost_num(rs.getInt("post_num"));
//					bbc.setUser_id(rs.getString("user_id"));
//					bbc.setPost_date(rs.getString("post_date"));
//					bbc.setPost_title(rs.getString("post_title"));
//					bbc.setPost_body(rs.getString("post_body"));
//					bbc.setPost_category(rs.getString("post_category"));
//					bbc.setPost_img(rs.getString("post_img"));
//					list.add(bbc);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
//			return list;
//		}
	
//	public ArrayList<Members> db_SelectBefore(int post_num) {
//		ArrayList<Members> list = new ArrayList<Members>();
//		try{
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery("select * from Bulletin_Board_Camptip where post_num < "+post_num+" order by post_num desc limit 1");        
//			while(rs.next()) {
//				Members bbc = new Members();
//				bbc.setPost_num(rs.getInt("post_num"));
//				bbc.setPost_category(rs.getString("post_category"));
//				bbc.setPost_title(rs.getString("post_title"));
//				bbc.setPost_body(rs.getString("post_body"));
//				bbc.setUser_id(rs.getString("user_id"));
//					
//				list.add(bbc);
//				}
//			
//			}catch (Exception e){
//				e.printStackTrace();
//			}
//			return list;
//	}
//	
//	public ArrayList<Members> db_SelectAfter(int post_num) {
//		ArrayList<Members> list = new ArrayList<Members>();
//		try{
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery("select * from Bulletin_Board_Camptip where post_num > "+post_num+" order by post_num asc limit 1");        
//			while(rs.next()) {
//				Members bbc = new Members();
//				
//				bbc.setPost_num(rs.getInt("post_num"));
//				bbc.setPost_category(rs.getString("post_category"));
//				bbc.setPost_title(rs.getString("post_title"));
//				bbc.setPost_body(rs.getString("post_body"));
//				bbc.setUser_id(rs.getString("user_id"));
//					
//				list.add(bbc);
//				}
//			
//			}catch (Exception e){
//				e.printStackTrace();
//			}
//			return list;
}