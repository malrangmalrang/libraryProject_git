package svc.admin;
import static db.JdbcUtil.*;

import java.sql.Connection;


public class BookManagedetailUpdateProService {

	public int bookManagedetailUpdate_Service(vo.admin.BookList bookListVo) {
		int updateSuccess=0;
		
		Connection con=getConnection();
		dao.admin.BookManageDAO bookManageDAO=dao.admin.BookManageDAO.getinstance();
		bookManageDAO.setConnection(con);
		
		
		
		updateSuccess=bookManageDAO.bookManagedetailUpdate_DAO(bookListVo);
		
		if(updateSuccess==1) {
			commit(con);
		}
		else if(updateSuccess==0) {
			rollback(con);
		}
		
		
		
		
		close(con);
		
		return updateSuccess;
	}

}
