package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MainDAO;
import vo.ReviewBean;
import vo.book.BookBean;

public class MainService {

	public ArrayList<BookBean> getBestBookList() {
		Connection con = getConnection();
		
		MainDAO mainDAO = MainDAO.getInstance();
		mainDAO.setConnection(con);
		
		ArrayList<BookBean> bestBookList = mainDAO.getBestBookList();
		
		close(con);
		
		return bestBookList;
	}

	public ArrayList<ReviewBean> getRecentReviewList() {
		Connection con = getConnection();
		
		MainDAO mainDAO = MainDAO.getInstance();
		mainDAO.setConnection(con);
		
		ArrayList<ReviewBean> recentReviewList = mainDAO.getRecentReviewList();
		
		close(con);
		
		return recentReviewList; 
	}

	public void updateOverdue() {
		Connection con=getConnection();
		MainDAO mainDAO=MainDAO.getInstance();
		mainDAO.setConnection(con);
		mainDAO.updateOverude_DAO();
		
		commit(con);
		close(con);
		
	}

}
