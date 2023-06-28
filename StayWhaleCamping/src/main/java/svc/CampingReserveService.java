package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import DAO.Camping_Product_DAO;
import vo.Accmodation_Camping;
import vo.Bulletin_Board_Review;
import vo.Camping_ALL;
import vo.Room_Info_Camping;

public class CampingReserveService {

	public static ArrayList<Object> getCampReserve(String reg_num) throws Exception{
		
		ArrayList<Accmodation_Camping> campList = null;
		ArrayList<Room_Info_Camping> roomList = null;
		ArrayList<Bulletin_Board_Review> reviewList = null;

		Connection con = getConnection();
		Camping_Product_DAO campingDAO = Camping_Product_DAO.getInstance();
		campingDAO.setConnection(con);
		
		
		campList = campingDAO.campinglist(reg_num);
		roomList = campingDAO.roomList(reg_num);
		reviewList = campingDAO.reviewList(reg_num);
		
		ArrayList<Object> articleList = new ArrayList<Object>();
		articleList.add(campList);
		articleList.add(roomList);
		articleList.add(reviewList);
		
		close(con);
		return articleList;
		
	}

}
