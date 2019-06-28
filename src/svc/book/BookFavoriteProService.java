package svc.book;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BookDAO;

public class BookFavoriteProService {

	private Connection con;

	public boolean insertBookFavor(String isbn, int memberNo) {

		boolean isInsertSuccess = false;

		con = getConnection();

		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		int insertCount = bookDAO.insertBookFavor(isbn, memberNo); 

		// insertCount가 0보다 크면 트랜잭션 Commit, 아니면 트랜잭션 Rollback 수행
		if (insertCount > 0) {
			commit(con);
			isInsertSuccess = true; // 성공 표시
		} else {
			rollback(con);
		}

		// DB 접속 해제(Connection 자원 반환)
		close(con);

		return isInsertSuccess;
	}
	
	public boolean deleteBookFavor(String isbn, int memberNo) {

		boolean isDeleteSuccess = false;

		con = getConnection();

		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		int insertCount = bookDAO.deleteBookFavor(isbn, memberNo); 

		// insertCount가 0보다 크면 트랜잭션 Commit, 아니면 트랜잭션 Rollback 수행
		if (insertCount > 0) {
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
