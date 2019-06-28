package svc.admin;

import java.sql.Connection;

import java.util.ArrayList;


import static db.JdbcUtil.*;

public class WishBookManageListUpdateService {


	public ArrayList getWishBookUpdateList_Service(String[] values) {
		Connection con=getConnection();
		dao.admin.WishBookManageDAO wishBookManageDAO=dao.admin.WishBookManageDAO.getinstance();
		wishBookManageDAO.setConnection(con);
		
		ArrayList wishBookUpdateList=wishBookManageDAO.getWishBookUpdateList_DAO(values);
		
		close(con);
		
		return wishBookUpdateList;
	}

}
