package svc.admin;
import static db.JdbcUtil.*;

import java.sql.Connection;

public class BookManagedetailDeleteService {

	public int bookManagedetailDelete_Service(int no) {
		int deleteSuccess=0;
		Connection con=getConnection();
		dao.admin.BookManageDAO bookManageDAO=dao.admin.BookManageDAO.getinstance();
		bookManageDAO.setConnection(con);
		
		
		deleteSuccess=bookManageDAO.bookManagedetailDelete_DAO(no);
		
		
		
		if(deleteSuccess==1) {
			commit(con);
		}
		
		else if(deleteSuccess==0) {
			rollback(con);
		}
		
		
		
		
		return deleteSuccess;
	}

}
