package svc;

import java.sql.Connection;

import dao.BoardDAO;
import static db.JdbcUtil.*;
public class QnaDeleteProService {

	public boolean isArticleWriter(int board_num, String email) {
		System.out.println("QnaDeleteProService - isArticleWriter()");
		boolean isArticleWriter = false;

		// Connection 객체 가져오기
		Connection con = getConnection();

		// BoardDAO 인스턴스 얻어오기 => setConnection() 메서드를 호출하여 Connection 객체 전달
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);

		isArticleWriter = boardDAO.isArticleBoardWriter(board_num, email); // 본인 확인(패스워드 일치여부 판별)

		close(con);

		return isArticleWriter;
	}

	public boolean removeArticle(int board_num) {
		boolean isRemoveSuccess = false;
		// Connection 객체 가져오기
		Connection con = getConnection();

		// BoardDAO 인스턴스 얻어오기 => setConnection() 메서드를 호출하여 Connection 객체 전달
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		//BoardDAO 클래스의 deleteArticle() 메서드를 호출하여 번호전달 후 게시물 삭제 수행 = > 삭제성공여부(deleteSuccess) int형으로 리턴
		int deleteSuccess = boardDAO.deleteArticle(board_num);
		//삭제성공여부값(deleteSuccess)가 0보다 크면 commit, isRemoveSuccess를 true로 변경
		//0이면 rollback
		if(deleteSuccess>0) {
			commit(con);
			isRemoveSuccess = true;
		}else {
			rollback(con);
		}
		
		//Connection 반환
		close(con);
		
		return isRemoveSuccess;
	}

	public boolean isConfirmByPass(String email, String member_pass) {
		boolean isConfirmByPass = false;
		// Connection 객체 가져오기
		Connection con = getConnection();

		// BoardDAO 인스턴스 얻어오기 => setConnection() 메서드를 호출하여 Connection 객체 전달
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		//BoardDAO 클래스의 deleteArticle() 메서드를 호출하여 번호전달 후 게시물 삭제 수행 = > 삭제성공여부(deleteSuccess) int형으로 리턴
		isConfirmByPass = boardDAO.isConfirmByPass(email, member_pass);
				
		//Connection 반환
		close(con);
		
		return isConfirmByPass;
	}

}
