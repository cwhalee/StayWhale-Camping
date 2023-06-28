package DAO;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;

import db.JdbcUtil;

import java.util.ArrayList;

import vo.Accmodation_Camping;
import vo.Bulletin_Board_Review;
import vo.Camping_ALL;
import vo.Camping_Like;
import vo.Reserve_Camping;
import vo.Room_Info_Camping;

public class Camping_Product_DAO {

	DataSource ds;
	Connection con;
	private static Camping_Product_DAO campingDAO;

	private Camping_Product_DAO() {
	}
	
	public static Camping_Product_DAO getInstance(){
		if(campingDAO == null){
			campingDAO = new Camping_Product_DAO();
		}
		return campingDAO;
	}

	public void setConnection(Connection con){
		this.con = con;
	}
	public ArrayList<Accmodation_Camping> camping_SelectList() { //캠핑 인덱스 페이지 select
		ArrayList<Accmodation_Camping> campinglist = new ArrayList<Accmodation_Camping>();
		Accmodation_Camping camp = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String camp_list_sql="SELECT ac.reg_num_c, ac.acc_name, ac.category, ac.site_1, ac.site_2, ac.detail, ac.acc_picture,"
							+ " AVG(re.post_rating) AS avg_rating FROM accmodation_camping ac"
							+ " LEFT JOIN bulletin_board_review re ON ac.reg_num_c = re.post_category"
							+ " GROUP BY ac.reg_num_c, ac.acc_name, ac.category, ac.site_1, ac.site_2, ac.detail, ac.acc_picture"
							+ " ORDER BY reg_num_c ASC;";
		
			try {
				pstmt = con.prepareStatement(camp_list_sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					camp = new Accmodation_Camping();
					camp.setReg_num_c(rs.getString("reg_num_c"));
					camp.setAcc_name(rs.getString("acc_name"));
					camp.setCategory(rs.getString("category"));
					camp.setSite_1(rs.getString("site_1"));
					camp.setSite_2(rs.getString("site_2"));
					camp.setDetail(rs.getString("detail"));
					camp.setAcc_picture(rs.getString("acc_picture"));
					camp.setRating(rs.getDouble("avg_rating"));
					campinglist.add(camp);
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				close(rs);
				close(pstmt);
			}
			return campinglist;
	}	
	
//	캠팡장 정보 SELECT
	public ArrayList<Accmodation_Camping> campinglist(String reg_num){ 
//		System.out.println(reg_num);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Accmodation_Camping> campList = new ArrayList<Accmodation_Camping>();
		Accmodation_Camping camp = null;
		
		String sql2 = "SELECT * FROM accmodation_camping WHERE reg_num_c = ?;";
		
		try{
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, reg_num);
			rs = pstmt.executeQuery();

			while(rs.next()){
				camp = new Accmodation_Camping();
				camp.setReg_num_c(rs.getString("reg_num_c"));
				camp.setAcc_name(rs.getString("acc_name"));
				camp.setSite_1(rs.getString("site_1"));
				camp.setSite_2(rs.getString("site_2"));
				camp.setLocation(rs.getString("location"));
				camp.setCategory(rs.getString("category"));
				camp.setTel_no(rs.getString("tel_no"));
				camp.setFax(rs.getString("fax"));
				camp.setEmail(rs.getString("email"));
				camp.setDetail(rs.getString("detail"));
				camp.setFacility_list(rs.getString("facility_list"));
				camp.setAcc_picture(rs.getString("acc_picture"));
				campList.add(camp);
			}

		}catch(Exception ex){
		}finally{
			close(rs);
			close(pstmt);
		}
		return campList;
	}
	//room 정보 SELECT
	public ArrayList<Room_Info_Camping> roomList(String reg_num){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Room_Info_Camping> roomList = new ArrayList<Room_Info_Camping>();
		Room_Info_Camping camp = null;
		
		String sql2 = "SELECT * FROM room_info_camping WHERE reg_num_c = ?;";
		
		try{
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, reg_num);
			rs = pstmt.executeQuery();

			while(rs.next()){
				camp = new Room_Info_Camping();
				camp.setFacility_num_c(rs.getString("facility_num_c"));
				camp.setFacility_name(rs.getString("facility_name"));
				camp.setPrice(rs.getInt("price"));
				camp.setRoom_detail(rs.getString("room_detail"));
				camp.setStandard_amount(rs.getInt("standard_amount"));
				camp.setFacility_picture(rs.getString("facility_picture"));
				roomList.add(camp);
			}

		}catch(Exception ex){
		}finally{
			close(rs);
			close(pstmt);
		}
		return roomList;
	}
	
	public ArrayList<Bulletin_Board_Review> reviewList(String reg_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Bulletin_Board_Review> reviewList = new ArrayList<Bulletin_Board_Review>();
		Bulletin_Board_Review review = null;
		
		String sql = "SELECT * FROM bulletin_board_review WHERE post_category='"+reg_num+"';";
		
		try{
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()){
				review = new Bulletin_Board_Review();
				review.setPost_num(rs.getInt("post_num"));
				review.setUser_id(rs.getString("user_id"));
				review.setPost_date(rs.getString("post_date"));
				review.setPost_title(rs.getString("post_title"));
				review.setPost_body(rs.getString("post_body"));
				review.setPost_file(rs.getString("post_file"));
				review.setPost_category(rs.getString("post_category"));
				review.setPost_img(rs.getString("post_img"));
				review.setPost_readcount(rs.getInt("post_readcount"));
				review.setPost_like(rs.getInt("post_like"));
				review.setPost_travel_location(rs.getString("post_travel_location"));
				review.setPost_rating(rs.getDouble("post_rating"));
				reviewList.add(review);
				
//				System.out.println(reviewList.get(0).getPost_title());
			}

		}catch(Exception ex){
		}finally{
			close(rs);
			close(pstmt);
		}
		return reviewList;
	}

	public Accmodation_Camping selectArticle(String reg_num_c){ // 예약하고싶은 상품 선택시 특정상품의 정보 select

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Accmodation_Camping camp = null;

		try{
			pstmt = con.prepareStatement("select * from accmodation_camping where reg_num_c = ?");
			pstmt.setString(1, reg_num_c);
			rs= pstmt.executeQuery();

			if(rs.next()){
				camp = new Accmodation_Camping();
				camp.setReg_num_c(rs.getString("reg_num_c"));
				camp.setAcc_name(rs.getString("acc_name"));
				camp.setSite_1(rs.getString("site_1"));
				camp.setSite_2(rs.getString("site_2"));
				camp.setLocation(rs.getString("location"));
				camp.setTel_no(rs.getString("tel_no"));
				camp.setFax(rs.getString("fax"));
				camp.setEmail(rs.getString("email"));
				camp.setDetail(rs.getString("detail"));
				camp.setFacility_list(rs.getString("facility_list"));
			}
		}catch(Exception ex){
		}finally{
			close(rs);
			close(pstmt);
		}
		return camp;
	}
	public ArrayList<Accmodation_Camping> searchKeyword(String keyword){ // 시도 구군 select
		ArrayList<Accmodation_Camping> keywordlist = new ArrayList<Accmodation_Camping>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Accmodation_Camping camp = null;
		System.out.println(keyword);
		try{
			String sql = "SELECT ac.*, AVG(re.post_rating) AS avg_rating FROM accmodation_camping ac"
					+ " LEFT JOIN bulletin_board_review re ON ac.reg_num_c = re.post_category WHERE"
					+ " (acc_name LIKE ? OR site_1 LIKE ? OR site_2 LIKE ? OR "
					+ " location LIKE ? OR detail LIKE ? OR facility_list LIKE ?) AND category != 'Backcountry'"
					+ " GROUP BY ac.reg_num_c, ac.acc_name, ac.category, ac.site_1, ac.site_2, ac.detail, ac.acc_picture"
					+ " UNION"
					+ " SELECT ac.*, NULL AS avg_rating FROM accmodation_camping ac WHERE ac.category = 'Backcountry'"
					+ " GROUP BY ac.reg_num_c, ac.acc_name, ac.category, ac.site_1, ac.site_2, ac.detail, ac.acc_picture"
					+ " ORDER BY reg_num_c DESC;";


			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
	        pstmt.setString(2, "%" + keyword + "%");
	        pstmt.setString(3, "%" + keyword + "%");
	        pstmt.setString(4, "%" + keyword + "%");
	        pstmt.setString(5, "%" + keyword + "%");
	        pstmt.setString(6, "%" + keyword + "%");
			rs= pstmt.executeQuery();

			while(rs.next()){
				camp = new Accmodation_Camping();
				camp.setReg_num_c(rs.getString("reg_num_c"));
				camp.setAcc_name(rs.getString("acc_name"));
				camp.setCategory(rs.getString("category"));
				camp.setSite_1(rs.getString("site_1"));
				camp.setSite_2(rs.getString("site_2"));
				camp.setDetail(rs.getString("detail"));
				camp.setAcc_picture(rs.getString("acc_picture"));
				camp.setRating(rs.getDouble("avg_rating"));
				keywordlist.add(camp);
			}
			System.out.println(keywordlist.get(0).getAcc_name());
		}catch(Exception ex){
		}finally{
			close(rs);
			close(pstmt);
		}
		return keywordlist;
	}

	public ArrayList<Accmodation_Camping> searchPlace(String site_1,String site_2){ // 시도 구군 select
		ArrayList<Accmodation_Camping> list = new ArrayList<Accmodation_Camping>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Accmodation_Camping camp = null;
//		System.out.println(site_1);
//		System.out.println(site_2);
		try{
			String sql = "SELECT ac.*, AVG(re.post_rating) AS avg_rating FROM accmodation_camping ac"
					+ " LEFT JOIN bulletin_board_review re ON ac.reg_num_c = re.post_category WHERE ac.site_1 = ? AND ac.site_2 = ?"
					+ " GROUP BY ac.reg_num_c, ac.acc_name, ac.category, ac.site_1, ac.site_2, ac.detail, ac.acc_picture"
					+ " UNION"
					+ " SELECT ac.*, NULL AS avg_rating FROM accmodation_camping ac WHERE ac.category = 'Backcountry'"
					+ " GROUP BY ac.reg_num_c, ac.acc_name, ac.category, ac.site_1, ac.site_2, ac.detail, ac.acc_picture"
					+ " ORDER BY reg_num_c DESC;";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, site_1);
			pstmt.setString(2, site_2);
			rs= pstmt.executeQuery();

			while(rs.next()){
				camp = new Accmodation_Camping();
				camp.setReg_num_c(rs.getString("reg_num_c"));
				camp.setAcc_name(rs.getString("acc_name"));
				camp.setSite_1(rs.getString("site_1"));
				camp.setSite_2(rs.getString("site_2"));
				camp.setLocation(rs.getString("location"));
				camp.setCategory(rs.getString("category"));
				camp.setTel_no(rs.getString("tel_no"));
				camp.setFax(rs.getString("fax"));
				camp.setEmail(rs.getString("email"));
				camp.setDetail(rs.getString("detail"));
				camp.setFacility_list(rs.getString("facility_list"));
				camp.setAcc_picture(rs.getString("acc_picture"));
				camp.setRating(rs.getDouble("avg_rating"));
				list.add(camp);
			}
		}catch(Exception ex){
		}finally{
			close(rs);
			close(pstmt);
		}
		return list;
	} 
	
	public ArrayList<Accmodation_Camping> searchDate(String check_in,String check_out){ // 체크인 날짜로 검색
		ArrayList<Accmodation_Camping> list = new ArrayList<Accmodation_Camping>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Accmodation_Camping camp = null;
		try{
			String sql = "SELECT ac.*, AVG(re.post_rating) AS avg_rating FROM accmodation_camping ac "
					+"LEFT JOIN bulletin_board_review re ON ac.reg_num_c = re.post_category "
					+"WHERE ac.reg_num_c IN (SELECT DISTINCT reg_num_c FROM room_info_camping WHERE facility_num_c NOT IN ("
					+"SELECT facility_num_c FROM reserve_list WHERE reserve_date < ? AND expire_date > ?))GROUP BY reg_num_c "
					+"UNION " 
					+"SELECT ac.*, NULL AS avg_rating FROM accmodation_camping ac WHERE ac.category = 'Backcountry' "
					+"ORDER BY reg_num_c DESC";

			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, check_out);
			pstmt.setString(2, check_in);
			
			rs= pstmt.executeQuery();
				
			while(rs.next()){
				camp = new Accmodation_Camping();
				camp.setReg_num_c(rs.getString("reg_num_c"));
				camp.setAcc_name(rs.getString("acc_name"));
				camp.setSite_1(rs.getString("site_1"));
				camp.setSite_2(rs.getString("site_2"));
				camp.setCategory(rs.getString("category"));
				camp.setAcc_picture(rs.getString("acc_picture"));
				camp.setRating(rs.getDouble("avg_rating"));
				list.add(camp);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public Camping_Like like_check(String reg_num, String user_id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Camping_Like like = null;
		try{
			String sql = "SELECT like_check FROM camping_like where reg_num_c = ? and user_id = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reg_num);
			pstmt.setString(2, user_id);
			
			rs= pstmt.executeQuery();
			if(rs.next()){
				like = new Camping_Like();
				like.setLike_check(rs.getInt("like_check"));
			} else {
				like = new Camping_Like();
				like.setLike_check(0);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return like;
	}
	
	public void camping_like(Camping_Like check_like) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/websitedb","root","1234");
			stmt = conn.createStatement();
			String command = "INSERT INTO camping_like(reg_num_c, user_id, like_check) VALUES('"+check_like.getReg_num_c()+"','"+check_like.getUser_id()+"',1) ON DUPLICATE KEY UPDATE reg_num_c='"+check_like.getReg_num_c()+"',user_id='"+check_like.getUser_id()+"',like_check=0";
	        stmt.executeUpdate(command);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			close(con);
			close(stmt);
		}
	}
	
	public void camping_unlike(Camping_Like check_like) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/websitedb","root","1234");
			stmt = conn.createStatement();
			String command = "DELETE FROM camping_like WHERE reg_num_c='"+check_like.getReg_num_c()+"' AND user_id='"+check_like.getUser_id()+"'";
	        stmt.executeUpdate(command);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}
	public ArrayList<Reserve_Camping> reserve_check(Reserve_Camping rc) {
		ArrayList<Reserve_Camping> list = new ArrayList<Reserve_Camping>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Reserve_Camping date = null;
		try {
			String sql = "SELECT facility_num_c FROM reserve_list WHERE reserve_date < ? AND expire_date > ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rc.getExpire_date());	
			pstmt.setString(2, rc.getReserve_date());
			rs= pstmt.executeQuery();
				
			while(rs.next()){
				date = new Reserve_Camping();
				date.setFacility_num_c(rs.getString("facility_num_c"));
				list.add(date);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public boolean insertReserve(Reserve_Camping article) {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int num = 0;
	    String sql = "";
	    int insertCount = 0;
	    boolean isSuccess = false;

	    try {
	        pstmt = con.prepareStatement("SELECT MAX(reserve_num) FROM reserve_list");
	        boolean hasResultSet = pstmt.execute();

	        if (!hasResultSet) {
	            num = 1;
	        } else {
	            rs = pstmt.getResultSet();
	            if (rs.next()) {
	                num = rs.getInt(1) + 1;
	            } else {
	                num = 1;
	            }
	        }

	        sql = "INSERT INTO reserve_list (reserve_num, reserve_date, expire_date, user_id, facility_num_c) VALUES (?, ?, ?, ?, ?)";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, num);
	        pstmt.setString(2, article.getReserve_date());
	        pstmt.setString(3, article.getExpire_date());
	        pstmt.setString(4, article.getUser_id());
	        pstmt.setString(5, article.getFacility_num_c());

	        insertCount = pstmt.executeUpdate();
	        if (insertCount > 0) {
	            isSuccess = true;
	        } else {
	            isSuccess = false;
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    } finally {
//	    	db패키지에 JdbcUtil파일을 보면 commit이있고 이 메소드를 통해 밀어넣는것, insert가됫다고 인식이되나 DB에는 들어가지않았을때 사용
	    	JdbcUtil.commit(con);
	        close(rs);
	        close(pstmt);
	    }

	    return isSuccess;
	}

	
}
