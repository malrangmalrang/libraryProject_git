package svc.admin;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;


public class BookManageListCheckDeleteService {

	public int bookManageListCheckDelete_Service(String[] checkList) {
		int checkDelete=0;
		Connection con=getConnection();
		dao.admin.BookManageDAO bookManageDAO=dao.admin.BookManageDAO.getinstance();
		bookManageDAO.setConnection(con);
		checkDelete=bookManageDAO.bookManageListCheckDelete_DAO(checkList);
		
		if(checkDelete==1) {
			commit(con);
		}
		
		else if(checkDelete==0) {
			rollback(con);
		}
		
		
		close(con);
		
		return checkDelete;
	}

}
