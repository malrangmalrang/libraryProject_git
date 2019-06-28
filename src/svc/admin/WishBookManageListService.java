package svc.admin;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;


public class WishBookManageListService {

	public ArrayList getWishBookList_Service(int page, int limit, String Class) {
		System.out.println("wishBook서비스");
		Connection con=getConnection();
	    dao.admin.WishBookManageDAO wishBookManageDAO=dao.admin.WishBookManageDAO.getinstance();
	    wishBookManageDAO.setConnection(con);
	    
	    ArrayList wishBookList=wishBookManageDAO.getWishBookList_DAO(page, limit,Class);
	    
	    close(con);
		
		return wishBookList;
	}

	public int getListCount(String Class) {
		int listCount = 0;
		Connection con = getConnection();
		   dao.admin.WishBookManageDAO wishBookManageDAO=dao.admin.WishBookManageDAO.getinstance();
		    wishBookManageDAO.setConnection(con);
		 listCount=wishBookManageDAO.getListCount_DAO(Class);
		 close(con);
		 return listCount;
	}

}
