package svc.review;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import dao.ReviewDAO;
import vo.ReviewBean;
import vo.book.BookBean;

public class ReviewWriteFormService {

	public ArrayList<HashMap<String, String>> getRentalBookList(int member_no) {
		System.out.println("ReviewWriteFormService - getRentalBookList()");
		ArrayList<HashMap<String, String>> bookList = null;
		
		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getinstance();
		reviewDAO.setConnection(con);
		
		bookList = reviewDAO.getRentalBookList(member_no);
				
		close(con);
		
		return bookList;
	}
}
