package svc.book;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BookDAO;

public class BookCommentDeleteProService {

	public boolean deleteBookComment(int bookCommentNo, int memberNo) {

		boolean isDeleteSuccess = false;

		Connection con = getConnection();

		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con); // Connection 객체를 boardDAO 객체에 저장
		int deleteCount = bookDAO.deleteBookComment(bookCommentNo, memberNo); 

		// insertCount가 0보다 크면 트랜잭션 Commit, 아니면 트랜잭션 Rollback 수행
		if (deleteCount > 0) {
			commit(con);
			isDeleteSuccess = true; // 성공 표시
		} else {
			rollback(con);
		}

		// DB 접속 해제(Connection 자원 반환)
		close(con);

		return isDeleteSuccess;

	}



}
