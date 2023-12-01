package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import DAO.Camping_Product_DAO;
import vo.Accmodation_Camping;

public class CampIndexService {

	public static ArrayList<Accmodation_Camping> getCampMain() throws Exception{
		ArrayList<Accmodation_Camping> campingList = null;
		
		Connection con = getConnection();
		
		Camping_Product_DAO campingDAO = Camping_Product_DAO.getInstance();
		campingDAO.setConnection(con);
		
		campingList = campingDAO.camping_SelectList();		
		close(con);
		System.out.println(campingList);
		return campingList;		
	}

}
