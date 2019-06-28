package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import dao.CommentDAO;

public class QnaCommentDeleteProService {
	public boolean removeComment(int comment_num, int page, int board_num) {
		boolean isRemoveSuccess = false;
		// Connection 객체 가져오기
		Connection con = getConnection();

		// BoardDAO 인스턴스 얻어오기 => setConnection() 메서드를 호출하여 Connection 객체 전달
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnection(con);
		
		//BoardDAO 클래스의 deleteArticle() 메서드를 호출하여 번호전달 후 게시물 삭제 수행 = > 삭제성공여부(deleteSuccess) int형으로 리턴
		int deleteSuccess = commentDAO.deleteComment(comment_num);
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
}
