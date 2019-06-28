package svc.book;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BookDAO;
import vo.book.BookBean;

public class BookBestRentService {

	private Connection con;
	private String category;
	private int memberNo;

	public BookBestRentService() {

	}

	public BookBestRentService(String category, int memberNo) {
		this.category = category;
		this.memberNo = memberNo;
	}

	public int getBookBestRentListCount() throws Exception {
		int listCount = 0;

		// Connection 객체 가져오기
		con = getConnection();

		// BoardDAO 인스턴스 얻어오기 => setConnection 메서드를 호출하여 Connection 객체 전달
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);

		/*if (category.isEmpty()) {
			if (month.isEmpty()) { // 전체 검색
				listCount = bookDAO.selectBookBestRentListCount();
			} else { // 단어 검색
				listCount = bookDAO.selectBookBestRentSearchListCount(year, month);
			}
		} else { // 카테고리 검색
			listCount = bookDAO.selectBookBestRentCategoryListCount(category);
		}*/
		
		if (category.isEmpty()) {
			listCount = bookDAO.selectBookBestRentListCount();
		} else {
			listCount = bookDAO.selectBookBestRentCategoryListCount(category);
		}

		// Connection 객체 반환
		close(con);

		return listCount;
	}

	// 글 목록 구하기
	public ArrayList<BookBean> getBookBestRentList() throws Exception {

		ArrayList<BookBean> bookList = null;

		// Connection 객체 가져오기
		con = getConnection();

		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);

		/*if (category.isEmpty()) {
			if (month.isEmpty()) { // 전체 검색
				bookList = bookDAO.selectBookBestRentList(page, limit, memberNo);
			} else { // 단어 검색
				bookList = bookDAO.selectBookBestRentSearchList(page, limit, year, month, memberNo);
			}
		} else { // 카테고리 검색
			bookList = bookDAO.selectBookBestRentCategoryList(page, limit, category, memberNo);
		}*/
		
		if (category.isEmpty()) {
			bookList = bookDAO.selectBookBestRentList(memberNo);
		} else { // 카테고리 검색
			bookList = bookDAO.selectBookBestRentCategoryList(category, memberNo);
		}

		// Connection 객체 반환
		close(con);

		return bookList;
	}

}
