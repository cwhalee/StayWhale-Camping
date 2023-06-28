package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import DAO.Camping_Product_DAO;
import vo.Accmodation_Camping;
import vo.Camping_Like;
import vo.Reserve_Camping;

public class CampingReserveCheckService {

	public ArrayList<Reserve_Camping> getReserve_check(Reserve_Camping rc) throws Exception{
		ArrayList<Reserve_Camping> List = null;
		
		Connection con = getConnection();
		
		Camping_Product_DAO campingDAO = Camping_Product_DAO.getInstance();
		campingDAO.setConnection(con);
		
		List = campingDAO.reserve_check(rc);		
		close(con);

		return List;	
	}

}
