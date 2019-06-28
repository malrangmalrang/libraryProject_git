package svc.notice;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.NoticeBoardDAO;
import vo.BoardBean;

public class NoticeDetailService {
	
	public BoardBean getArticle(int no) throws Exception {
		
		BoardBean boardBean = null;
		
		Connection con = getConnection();
		
		NoticeBoardDAO boardDAO = NoticeBoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		boardBean = boardDAO.selectArticle(no);
		
		int updateCount = boardDAO.updateReadcount(no);
		
		if(updateCount == 1) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return boardBean;
	}
}























