package svc.book;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BookDAO;
import vo.book.RentBean;

public class BookRentProService {

	private Connection con;

	public int getRentableBookNo(String isbn) {

		int bookNo = 0;

		con = getConnection();
		
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		bookNo = bookDAO.selectRentableBookNo(isbn);

		// DB 접속 해제(Connection 자원 반환)
		close(con);

		return bookNo;
	}


	public boolean insertBookRent(RentBean rentBean) {

		boolean isInsertSuccess = false;

		con = getConnection();

		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		int insertCount = bookDAO.insertBookRent(rentBean); 

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
}
