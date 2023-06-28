package svc;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.Camping_Product_DAO;
import vo.Accmodation_Camping;

import static db.JdbcUtil.*;

public class SearchDateService {

	public static ArrayList<Accmodation_Camping> date_search(String check_in, String check_out) throws Exception{
		ArrayList<Accmodation_Camping> campingList = null;
		
		Connection con = getConnection();
		
		Camping_Product_DAO campingDAO = Camping_Product_DAO.getInstance();
		campingDAO.setConnection(con);
//		System.out.println("서비스출발확인");
		campingList = campingDAO.searchDate(check_in, check_out);
		close(con);
		
		
		return campingList;
	}
}
