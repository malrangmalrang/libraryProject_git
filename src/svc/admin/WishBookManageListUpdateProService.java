package svc.admin;

import java.sql.Connection;

import java.util.ArrayList;


import static db.JdbcUtil.*;

public class WishBookManageListUpdateProService {

	public int wishBookMangeListUpdate_Service(ArrayList wishBookUpdateList, ArrayList wishBookUpdateListArray) {
		Connection con=getConnection();
		dao.admin.WishBookManageDAO wishBookManageDAO=dao.admin.WishBookManageDAO.getinstance();
		wishBookManageDAO.setConnection(con);
		
		int isUpdateSuccess=wishBookManageDAO.wishBookManageListUpdate_DAO(wishBookUpdateList,wishBookUpdateListArray);
		
		if(isUpdateSuccess==1) {
			commit(con);
		}
		else if(isUpdateSuccess==0) {
			rollback(con);
		}
		close(con);
		
		return isUpdateSuccess;
	}

}
