package svc.admin;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;


public class BookManageListSearchService {

	public int getListCount(String search) {
		Connection con=getConnection();
		dao.admin.BookManageDAO bookManageDAO=dao.admin.BookManageDAO.getinstance();
		bookManageDAO.setConnection(con);
		
		int listCount=bookManageDAO.getListSearchCount_DAO(search);
		
		close(con);
		return listCount;
	}//getListCount

	public ArrayList getBookList(int page, int limit, String search) {
		Connection con=getConnection();
		dao.admin.BookManageDAO bookManageDAO=dao.admin.BookManageDAO.getinstance();
		bookManageDAO.setConnection(con);
		
		ArrayList bookListvo=bookManageDAO.getBookSearchList_DAO(page,limit,search);
		close(con);
		return bookListvo;
	}


}
