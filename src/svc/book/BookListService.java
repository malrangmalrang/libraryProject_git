package svc.book;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BookDAO;
import vo.book.BookBean;

public class BookListService {

	private Connection con;
	private String searchType;
	private String searchWord;
	private String category;
	private int page;
	private int limit;
	private int memberNo;

	public BookListService() {

	}

	public BookListService(String searchType, String searchWord, String category, int page, int limit, int memberNo) {
		this.searchType = searchType;
		this.searchWord = searchWord;
		this.category = category;
		this.page = page;
		this.limit = limit;
		this.memberNo = memberNo;
	}

	public int getBookListCount() throws Exception {
		int listCount = 0;

		// Connection 객체 가져오기
		con = getConnection();

		// BoardDAO 인스턴스 얻어오기 => setConnection 메서드를 호출하여 Connection 객체 전달
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);

		if (category.isEmpty()) {
			if (searchWord.isEmpty()) { // 전체 검색
				listCount = bookDAO.selectBookListCount();
			} else { // 단어 검색
				listCount = bookDAO.selectBookSearchListCount(searchType, searchWord);
			}
		} else { // 카테고리 검색
			if (searchWord.isEmpty()) { // 전체 검색
				listCount = bookDAO.selectBookCategoryListCount(category);
			} else { // 단어 검색
				listCount = bookDAO.selectBookCategorySearchListCount(category, searchType, searchWord);
			}
		}

		// Connection 객체 반환
		close(con);

		return listCount;
	}

	// 글 목록 구하기
	public ArrayList<BookBean> getBookList() throws Exception {

		ArrayList<BookBean> bookList = null;

		// Connection 객체 가져오기
		con = getConnection();

		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);

		if (category.isEmpty()) {
			if (searchWord.isEmpty()) { // 전체 검색
				bookList = bookDAO.selectBookList(page, limit, memberNo);
			} else { // 단어 검색
				bookList = bookDAO.selectBookSearchList(page, limit, searchType, searchWord, memberNo);
			}
		} else { // 카테고리 검색
			if (searchWord.isEmpty()) { // 전체 검색
				bookList = bookDAO.selectBookCategoryList(page, limit, category, memberNo);
			} else { // 단어 검색
				
				bookList = bookDAO.selectBookCategorySearchList(page, limit, category, searchType, searchWord, memberNo);
			}
		}

		// Connection 객체 반환
		close(con);

		return bookList;
	}

}
