package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import DAO.Camping_Product_DAO;
import vo.Accmodation_Camping;
import vo.Camping_Like;

public class CampingLikeCheckService {

	public static Camping_Like getLikeCheck(String reg_num_c, String user_id) throws Exception{
		Camping_Like like_check = null;
		
		Connection con = getConnection();
		
		Camping_Product_DAO campingDAO = Camping_Product_DAO.getInstance();
		campingDAO.setConnection(con);
		
		like_check = campingDAO.like_check(reg_num_c,user_id);		
		close(con);

		return like_check;		
	}

}
