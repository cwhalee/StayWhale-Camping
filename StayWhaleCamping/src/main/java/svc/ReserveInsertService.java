package svc;

import java.sql.Connection;

import DAO.Camping_Product_DAO;
import vo.Reserve_Camping;

import static db.JdbcUtil.*;
public class ReserveInsertService {

	public boolean registArticle(Reserve_Camping reserve) throws Exception{		
		Connection con = getConnection();
		Camping_Product_DAO reserveDAO = Camping_Product_DAO.getInstance();
		reserveDAO.setConnection(con);
		
		boolean reserve_check = reserveDAO.insertReserve(reserve);
		
		close(con);
		
		return reserve_check;
	}

}
