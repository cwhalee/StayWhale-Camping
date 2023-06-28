package svc;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.Camping_Product_DAO;
import vo.Accmodation_Camping;

import static db.JdbcUtil.*;

public class SearchPlaceService {

	public static ArrayList<Accmodation_Camping> place_search(String site_1, String site_2) throws Exception{
		ArrayList<Accmodation_Camping> campingList = null;
		
		Connection con = getConnection();
		
		Camping_Product_DAO campingDAO = Camping_Product_DAO.getInstance();
		campingDAO.setConnection(con);
//		System.out.println(site_1);
		campingList = campingDAO.searchPlace(site_1, site_2);
		close(con);
		
		
		return campingList;
	}
}
