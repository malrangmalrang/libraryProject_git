package svc.admin;

import java.sql.Connection;

import java.util.ArrayList;
import static db.JdbcUtil.*;

public class BookManangeListService {

	public ArrayList getBookList(int page, int limit, String Class) {
		
		Connection con=getConnection();	
		dao.admin.BookManageDAO bookManageDAO=dao.admin.BookManageDAO.getinstance();
        bookManageDAO.setConnection(con);
		
		ArrayList bookList=bookManageDAO.getBookList(page, limit, Class);
		
		
		
		
		close(con);
		return bookList;
	}//getBookList();

	public int getListCount(String Class) {
		int listCount = 0;
		Connection con = getConnection();
		dao.admin.BookManageDAO bookManageDAO=dao.admin.BookManageDAO.getinstance();
		bookManageDAO.setConnection(con);
		
		listCount=bookManageDAO.getListCount_DAO(Class);
		
		close(con);
		return listCount;
		
	}//getListCount()
	
	

}
