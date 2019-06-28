package svc;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;

import static db.JdbcUtil.*;
public class BoardModifyProService {

	public boolean isArticleWriter(int board_num, String email) {
		System.out.println("BoardModifyProService-isArticleWriter");
		boolean isArticleWriter = false;
		Connection con= getConnection();
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		isArticleWriter = boardDAO.isArticleBoardWriter(board_num,email);
		close(con);
		return isArticleWriter;
	}

	public boolean ModifyArticle(BoardBean article) {
		boolean isModifySuccess = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int updateCount = boardDAO.updateArticle(article);
		if(updateCount>0) {
			commit(con);
			isModifySuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		return isModifySuccess;
	}

}
