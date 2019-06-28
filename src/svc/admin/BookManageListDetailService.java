package svc.admin;
import static db.JdbcUtil.*;

import java.sql.Connection;


public class BookManageListDetailService {

	public vo.admin.BookList getBookManageListDetail_Service(int no) {
		vo.admin.BookList bookListVo=new vo.admin.BookList();
		Connection con=getConnection();
		
		dao.admin.BookManageDAO bookManageDAO=dao.admin.BookManageDAO.getinstance();
		bookManageDAO.setConnection(con);
		
		bookListVo=bookManageDAO.getBookManageListDetail_DAO(no);
		
		
		
		close(con);
		return bookListVo;
	}

}
