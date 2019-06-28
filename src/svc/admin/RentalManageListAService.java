package svc.admin;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;


public class RentalManageListAService {

	public ArrayList RentalManageList_Service(int page, int limit, String rmClass) {
		Connection con=getConnection();
		dao.admin.RentalManageDAO rentalManageDAO=dao.admin.RentalManageDAO.getinstance();
		rentalManageDAO.setConnection(con);
		
		
		ArrayList rentalList=rentalManageDAO.RentalManageList_DAO(page, limit, rmClass);
		
		close(con);
		return rentalList;
	}

	public int getListCount(String rmClass) {
		int listCount = 0;
		Connection con = getConnection();
		dao.admin.RentalManageDAO rentalManageDAO=dao.admin.RentalManageDAO.getinstance();
		rentalManageDAO.setConnection(con);
		 listCount=rentalManageDAO.getListCount_DAO(rmClass);
		 close(con);
		 return listCount;
		

		
	}

}
