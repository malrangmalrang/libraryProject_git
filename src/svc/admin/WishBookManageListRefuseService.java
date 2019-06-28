package svc.admin;
import static db.JdbcUtil.*;

import java.sql.Connection;


public class WishBookManageListRefuseService {

	public int wishBookManageListRefuse_Service(String[] values) {
		Connection con=getConnection();
		dao.admin.WishBookManageDAO wishBookManageDAO=dao.admin.WishBookManageDAO.getinstance();
		wishBookManageDAO.setConnection(con);
		
		int isRefuseSuccess=wishBookManageDAO.wishBookManageListRefuse_DAO(values);
		
		if(isRefuseSuccess==1) {
			commit(con);
		}
		
		else if(isRefuseSuccess==0) {
			rollback(con);
		}
		
		close(con);
		return isRefuseSuccess;
	}

}
