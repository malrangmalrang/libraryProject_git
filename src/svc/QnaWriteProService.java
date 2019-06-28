package svc;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;
import vo.MemberBean;

import static db.JdbcUtil.*;

public class QnaWriteProService {

	public boolean registArticle(BoardBean boardBean, MemberBean memberBean) throws Exception{
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int insertCount = boardDAO.insertArticle(boardBean,memberBean);
		
		if(insertCount>0) {
			commit(con);
			isWriteSuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		return isWriteSuccess;
	}
	
}
