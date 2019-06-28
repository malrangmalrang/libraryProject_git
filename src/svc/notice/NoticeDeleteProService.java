package svc.notice;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.NoticeBoardDAO;

public class NoticeDeleteProService {
	public boolean isArticleWriter(int no, String pass) throws Exception {
		System.out.println("NoticeDeleteProService - isArticleWriter()");
		boolean isArticleWriter = false;
		
		Connection con = getConnection();
		
		NoticeBoardDAO boardDAO = NoticeBoardDAO.getInstance();
		boardDAO.setConnection(con);

		isArticleWriter = boardDAO.isArticleNoticeWriter(no); 
		
		close(con);
		
		return isArticleWriter;
	}

	public boolean removeArticle(int no) {
		boolean isRemoveSuccess = false;
		
		Connection con = getConnection();
		
		NoticeBoardDAO boardDAO = NoticeBoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int deleteSuccess = boardDAO.deleteArticle(no);
		
		if(deleteSuccess > 0) {
			commit(con);
			isRemoveSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isRemoveSuccess;
	}
}
















