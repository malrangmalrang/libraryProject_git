package svc.book;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BookDAO;
import vo.book.BookBean;
import vo.book.BookCommentBean;

public class BookContentService {

	private Connection con;

	public BookBean getBook(int memberNo, String isbn) {
		BookBean bookBean = null;

		// Connection 객체 가져오기
		con = getConnection();

		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);;

		bookBean = bookDAO.selectBook(memberNo, isbn);

		// Connection 객체 반환
		close(con);

		return bookBean;
	}

	public ArrayList<BookCommentBean> getBookCommentList(String isbn) {
		ArrayList<BookCommentBean> bookCommentList = null;

		con = getConnection();

		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);

		bookCommentList = bookDAO.selectBookCommentList(isbn);

		close(con);

		return bookCommentList;
	}

	public boolean isFavoriteBook(int memberNo, String isbn) {

		boolean isFavoriteBook = false;

		con = getConnection();

		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		
		isFavoriteBook = bookDAO.selectBookFavor(memberNo, isbn);

		return isFavoriteBook;
	}

}
