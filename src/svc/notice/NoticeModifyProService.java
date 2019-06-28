package svc.notice;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.NoticeBoardDAO;
import vo.BoardBean;

public class NoticeModifyProService {
	
	public boolean isArticleWriter(int member_no) throws Exception {// NoticeModifyProAction 
		System.out.println("NoticeModifyProService - isArticleWriter()");
		boolean isArticleWriter = false;
		
		Connection con = getConnection();
		
		NoticeBoardDAO boardDAO = NoticeBoardDAO.getInstance();
		boardDAO.setConnection(con);

		isArticleWriter = boardDAO.isArticleNoticeWriter(member_no); 
		
		close(con);
		
		return isArticleWriter;
	}

	public boolean modifyArticle(BoardBean article) {
		boolean isModifySuccess = false;
		
		Connection con = getConnection();
		
		NoticeBoardDAO boardDAO = NoticeBoardDAO.getInstance();
		boardDAO.setConnection(con); 
		int updateCount = boardDAO.updateArticle(article); 
		
		if(updateCount > 0) {
			commit(con);
			isModifySuccess = true; 
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isModifySuccess;
	}

	
	
}























