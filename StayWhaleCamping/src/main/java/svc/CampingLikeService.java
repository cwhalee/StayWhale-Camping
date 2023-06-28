package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import DAO.Camping_Product_DAO;
import vo.Accmodation_Camping;
import vo.Camping_Like;

public class CampingLikeService {

	public void getLikecheck(Camping_Like like_check) throws Exception{
		Connection con = getConnection();
		
		Camping_Product_DAO campingDAO = Camping_Product_DAO.getInstance();
		campingDAO.setConnection(con);
		
		campingDAO.camping_like(like_check);		
		close(con);
		
	}

}
