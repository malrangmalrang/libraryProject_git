package svc.admin;

import java.sql.Connection;

import java.util.ArrayList;


import static db.JdbcUtil.*;

public class RentalManageSearchService {

	public ArrayList rentalSearchList_Service(String rentalSearchSelectValue, String rentalSearchText) {
		Connection con=getConnection();
		dao.admin.RentalManageDAO rentalManageDAO=dao.admin.RentalManageDAO.getinstance();
		rentalManageDAO.setConnection(con);
		
		ArrayList rentalList=rentalManageDAO.rentalSearchList_DAO(rentalSearchSelectValue,rentalSearchText);
		
		close(con);
		return rentalList;
	}

}
