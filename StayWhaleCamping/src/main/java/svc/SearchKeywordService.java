package svc;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.Camping_Product_DAO;
import vo.Accmodation_Camping;

import static db.JdbcUtil.*;

public class SearchKeywordService {

	public static ArrayList<Accmodation_Camping> keyword_search(String keyword) throws Exception{
		ArrayList<Accmodation_Camping> campingList = null;
		
		Connection con = getConnection();
		
		Camping_Product_DAO campingDAO = Camping_Product_DAO.getInstance();
		campingDAO.setConnection(con);
//		System.out.println(site_1);
		campingList = campingDAO.searchKeyword(keyword);
		close(con);
		
		
		return campingList;
	}
}
