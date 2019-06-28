package dao;

//JdbcUtil 클래스의 static 메서드를 클래스명 없이 메서드명으로만으로도 호출 가능하도록 import하기 위해 import static 사용
//=> import static 패키지명.클래스명.메서드명 또는 메서드명 대신 모든 메서드를 포함하도록 * 사용
import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.book.BookBean;
import vo.book.BookCommentBean;
import vo.book.DbCode;
import vo.book.RentBean;  

public class BookDAO {

	// ------------------------------------------------------------------------
	// 싱글톤 디자인 패턴을 활용하여 1개의 인스턴스를 생성하여 공유
	private static BookDAO instance;

	public static BookDAO getInstance() {
		// BoardDAO 객체를 저장하는 변수 instance 가 null 일 때만 인스턴스 생성
		if (instance == null) {
			instance = new BookDAO();
		}
		return instance;
	}

	private BookDAO() {

	}
	// ------------------------------------------------------------------------	

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public void setConnection(Connection con) {
		this.con = con; // Service 클래스로부터 전달받은 DB 연결 객체(Connection 객체)를 멤버변수에 저장
	}

	public int selectBookListCount() {

		int listCount = 0;

		String sql = "SELECT COUNT(no) FROM book";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				listCount = rs.getInt(1);
			}
			System.out.println("selectBookListCount(): " + listCount);
		} catch (SQLException e) {
			System.out.println("selectBookListCount() 에러: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	public int selectBookSearchListCount(String searchType, String searchWord) {

		int listCount = 0;

		String searchCondition = "";
		if (searchType.equals("keyword")) {
			searchCondition = "(keyword1 LIKE '%" + searchWord + "%' OR keyword2 LIKE '%" + searchWord + "%' OR keyword3 LIKE '%" + searchWord + "%') AND ";
		} else {
			searchCondition = searchType + " LIKE '%" + searchWord + "%' AND ";
		}

		String sql = "SELECT COUNT(no) FROM book WHERE " + searchCondition + "no IN (SELECT MAX(no) FROM book GROUP BY isbn)";
		System.out.println(sql);

		try {
			pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, "%" + searchWord + "%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				listCount = rs.getInt(1);
			}
			System.out.println("selectBookSearchListCount(" + searchType + ", " + searchWord + "): " + listCount);
		} catch (SQLException e) {
			System.out.println("selectBookSearchListCount() 에러: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	public int selectBookCategoryListCount(String category) {

		int listCount = 0;

		String sql = "select count(no) from book where category = ? AND no in (select max(no) from book group by isbn)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				listCount = rs.getInt(1);
			}
			System.out.println("selectBookCategoryListCount(" + category + "): " + listCount);
		} catch (SQLException e) {
			System.out.println("selectBookCategoryListCount() 에러: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}
	
	public int selectBookCategorySearchListCount(String category, String searchType, String searchWord) {

		int listCount = 0;

		String searchCondition = "";
		if (searchType.equals("keyword")) {
			searchCondition = "(keyword1 LIKE '%" + searchWord + "%' OR keyword2 LIKE '%" + searchWord + "%' OR keyword3 LIKE '%" + searchWord + "%') AND ";
		} else {
			searchCondition = searchType + " LIKE '%" + searchWord + "%' AND ";
		} 

		String sql = "SELECT COUNT(no) FROM book WHERE " + searchCondition + " category = ? AND no IN (SELECT MAX(no) FROM book GROUP BY isbn)";
		System.out.println(sql);
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				listCount = rs.getInt(1);
			}
			System.out.println("selectBookCategorySearchListCount(" + category + "): " + listCount);
		} catch (SQLException e) {
			System.out.println("selectBookCategorySearchListCount() 에러: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	public ArrayList<BookBean> selectBookList(int page, int limit, int memberNo) {

		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		
		String sql = "";
		if (memberNo != 0) {
			sql = "select book.*, sum(Case when book.status = '" + DbCode.BOOK_STATUS_IN + "' then 1 else 0 end) as rentCount, IFNULL(comment.grade, 0) AS averageGrade, sum(Case when favor.isbn then 1 else 0 end) as favorCount " + 
					"from book " + 
					"left join (select isbn, ROUND(AVG(grade), 2) AS grade from book_comment group by isbn) as comment " + 
					"on book.isbn = comment.isbn " + 
					"left join (select isbn, member_no from favor_book) as favor " + 
					"on book.isbn = favor.isbn and favor.member_no = " + memberNo +  
					" group by book.isbn order by book.no limit ?, ?";
		} else {
			sql = "select book.*, sum(Case when book.status = '" + DbCode.BOOK_STATUS_IN + "' then 1 else 0 end) as rentCount, IFNULL(comment.grade, 0) AS averageGrade, sum(Case when favor.isbn then 1 else 0 end) as favorCount " + 
					"from book " + 
					"left join (select isbn, ROUND(AVG(grade), 2) AS grade from book_comment group by isbn) as comment " + 
					"on book.isbn = comment.isbn " + 
					"left join (select isbn, member_no from favor_book) as favor " + 
					"on book.isbn = favor.isbn " + 
					"group by book.isbn order by book.no limit ?, ?";
		}

		try {
			pstmt = con.prepareStatement(sql);
			int startRow = (page - 1) * 10;
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookBean bookBean = new BookBean();
				bookBean.setNo(rs.getInt("no"));
				bookBean.setTitle(rs.getString("title"));
				bookBean.setAuthor(rs.getString("author"));
				bookBean.setPublisher(rs.getString("publisher"));
				bookBean.setPublish_date(rs.getDate("publish_date"));
				bookBean.setPrice(rs.getInt("price"));
				bookBean.setIsbn(rs.getString("isbn"));
				bookBean.setImage(rs.getString("image"));
				bookBean.setStatus(rs.getString("status"));
				bookBean.setKeyword1(rs.getString("keyword1"));
				bookBean.setKeyword2(rs.getString("keyword2"));
				bookBean.setKeyword3(rs.getString("keyword3"));
				bookBean.setCategory(rs.getString("category"));
				bookBean.setRent_code(rs.getString("rent_code"));
				bookBean.setBar_code(rs.getString("bar_code"));
				bookBean.setReg_date(rs.getTimestamp("reg_date"));
				bookBean.setAverageGrade(rs.getFloat("averageGrade"));
				bookBean.setRentCount(rs.getInt("rentCount"));
				bookBean.setFavorCount(rs.getInt("favorCount"));
				bookList.add(bookBean);
			}
			System.out.println("selectBookList(): " + bookList.size() + "개 조회!");
		} catch (SQLException e) {
			System.out.println("selectBookList() 에러: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return bookList;
	}

	public ArrayList<BookBean> selectBookSearchList(int page, int limit, String searchType, String searchWord, int memberNo) {

		ArrayList<BookBean> bookList = new ArrayList<BookBean>();

		String searchCondition = "";
		if (searchType.equals("all")) {

		} else if (searchType.equals("keyword")) {
			searchCondition = "(book.keyword1 LIKE '%" + searchWord + "%' OR book.keyword2 LIKE '%" + searchWord + "%' OR book.keyword3 LIKE '%" + searchWord + "%') ";
		} else {
			searchCondition = "book." + searchType + " LIKE '%" + searchWord + "%' ";
		}

		String sql = "";
		if (memberNo != 0) {
			sql = "select book.*, sum(Case when book.status = '" + DbCode.BOOK_STATUS_IN + "' then 1 else 0 end) as rentCount, IFNULL(comment.grade, 0) AS averageGrade, sum(Case when favor.isbn then 1 else 0 end) as favorCount " + 
					"from book " + 
					"left join (select isbn, ROUND(AVG(grade), 2) AS grade from book_comment group by isbn) as comment " + 
					"on book.isbn = comment.isbn " + 
					"left join (select isbn, member_no from favor_book) as favor " + 
					"on book.isbn = favor.isbn and favor.member_no = " + memberNo + 
					" WHERE " + searchCondition +
					"group by book.isbn order by book.no limit ?, ?";	
		} else {
			sql = "select book.*, sum(Case when book.status = '" + DbCode.BOOK_STATUS_IN + "' then 1 else 0 end) as rentCount, IFNULL(comment.grade, 0) AS averageGrade, sum(Case when favor.isbn then 1 else 0 end) as favorCount " + 
					"from book " + 
					"left join (select isbn, ROUND(AVG(grade), 2) AS grade from book_comment group by isbn) as comment " + 
					"on book.isbn = comment.isbn " + 
					"left join (select isbn, member_no from favor_book) as favor " + 
					"on book.isbn = favor.isbn " +
					"WHERE " + searchCondition +
					"group by book.isbn order by book.no limit ?, ?";
		}

		try {
			pstmt = con.prepareStatement(sql);
			int startRow = (page - 1) * 10;
			//pstmt.setString(2, "%" + searchWord + "%");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookBean bookBean = new BookBean();
				bookBean.setNo(rs.getInt("no"));
				bookBean.setTitle(rs.getString("title"));
				bookBean.setAuthor(rs.getString("author"));
				bookBean.setPublisher(rs.getString("publisher"));
				bookBean.setPublish_date(rs.getDate("publish_date"));
				bookBean.setPrice(rs.getInt("price"));
				bookBean.setIsbn(rs.getString("isbn"));
				bookBean.setImage(rs.getString("image"));
				bookBean.setStatus(rs.getString("status"));
				bookBean.setKeyword1(rs.getString("keyword1"));
				bookBean.setKeyword2(rs.getString("keyword2"));
				bookBean.setKeyword3(rs.getString("keyword3"));
				bookBean.setCategory(rs.getString("category"));
				bookBean.setRent_code(rs.getString("rent_code"));
				bookBean.setBar_code(rs.getString("bar_code"));
				bookBean.setReg_date(rs.getTimestamp("reg_date"));
				bookBean.setAverageGrade(rs.getFloat("averageGrade"));
				bookBean.setRentCount(rs.getInt("rentCount"));
				bookBean.setFavorCount(rs.getInt("favorCount"));
				bookList.add(bookBean);
			}
			System.out.println("selectBookSearchList(): " + bookList.size() + "개 조회!");
		} catch (SQLException e) {
			System.out.println("selectBookSearchList() 에러: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return bookList;
	}

	public ArrayList<BookBean> selectBookCategoryList(int page, int limit, String category, int memberNo) {

		ArrayList<BookBean> bookList = new ArrayList<BookBean>();

		String sql = "";
		if (memberNo != 0) {
			sql = "select book.*, sum(Case when book.status = '" + DbCode.BOOK_STATUS_IN + "' then 1 else 0 end) as rentCount, IFNULL(comment.grade, 0) AS averageGrade, sum(Case when favor.isbn then 1 else 0 end) as favorCount " + 
					"from book " + 
					"left join (select isbn, ROUND(AVG(grade), 2) AS grade from book_comment group by isbn) as comment " + 
					"on book.isbn = comment.isbn " + 
					"left join (select isbn, member_no from favor_book) as favor " + 
					"on book.isbn = favor.isbn and favor.member_no = " + memberNo + 
					" WHERE book.category = ? " + 
					"group by book.isbn order by book.no limit ?, ?";
		} else {
			sql = "select book.*, sum(Case when book.status = '" + DbCode.BOOK_STATUS_IN + "' then 1 else 0 end) as rentCount, IFNULL(comment.grade, 0) AS averageGrade, sum(Case when favor.isbn then 1 else 0 end) as favorCount " + 
					"from book " + 
					"left join (select isbn, ROUND(AVG(grade), 2) AS grade from book_comment group by isbn) as comment " + 
					"on book.isbn = comment.isbn " + 
					"left join (select isbn, member_no from favor_book) as favor " + 
					"on book.isbn = favor.isbn " +
					"WHERE book.category = ? " + 
					"group by book.isbn order by book.no limit ?, ?";
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			int startRow = (page - 1) * 10;
			pstmt.setString(1, category);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookBean bookBean = new BookBean();
				bookBean.setNo(rs.getInt("no"));
				bookBean.setTitle(rs.getString("title"));
				bookBean.setAuthor(rs.getString("author"));
				bookBean.setPublisher(rs.getString("publisher"));
				bookBean.setPublish_date(rs.getDate("publish_date"));
				bookBean.setPrice(rs.getInt("price"));
				bookBean.setIsbn(rs.getString("isbn"));
				bookBean.setImage(rs.getString("image"));
				bookBean.setStatus(rs.getString("status"));
				bookBean.setKeyword1(rs.getString("keyword1"));
				bookBean.setKeyword2(rs.getString("keyword2"));
				bookBean.setKeyword3(rs.getString("keyword3"));
				bookBean.setCategory(rs.getString("category"));
				bookBean.setRent_code(rs.getString("rent_code"));
				bookBean.setBar_code(rs.getString("bar_code"));
				bookBean.setReg_date(rs.getTimestamp("reg_date"));
				bookBean.setAverageGrade(rs.getFloat("averageGrade"));
				bookBean.setRentCount(rs.getInt("rentCount"));
				bookBean.setFavorCount(rs.getInt("favorCount"));
				bookList.add(bookBean);
			}
			System.out.println("selectBookCategoryList(): " + bookList.size() + "개 조회!");
		} catch (SQLException e) {
			System.out.println("selectBookCategoryList() 에러: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return bookList;
	}
	
	public ArrayList<BookBean> selectBookCategorySearchList(int page, int limit, String category, String searchType, String searchWord, int memberNo) {

		ArrayList<BookBean> bookList = new ArrayList<BookBean>();

		String searchCondition = "";
		if (searchType.equals("all")) {

		} else if (searchType.equals("keyword")) {
			searchCondition = "(book.keyword1 LIKE '%" + searchWord + "%' OR book.keyword2 LIKE '%" + searchWord + "%' OR book.keyword3 LIKE '%" + searchWord + "%') ";
		} else {
			searchCondition = "book." + searchType + " LIKE '%" + searchWord + "%' ";
		}

		String sql = "";
		if (memberNo != 0) {
			sql = "select book.*, sum(Case when book.status = '" + DbCode.BOOK_STATUS_IN + "' then 1 else 0 end) as rentCount, IFNULL(comment.grade, 0) AS averageGrade, sum(Case when favor.isbn then 1 else 0 end) as favorCount " + 
					"from book " + 
					"left join (select isbn, ROUND(AVG(grade), 2) AS grade from book_comment group by isbn) as comment " + 
					"on book.isbn = comment.isbn " + 
					"left join (select isbn, member_no from favor_book) as favor " + 
					"on book.isbn = favor.isbn and favor.member_no = " + memberNo + 
					" WHERE " + searchCondition +
					"AND book.category = ? " + 
					"group by book.isbn order by book.no limit ?, ?";			
		} else {
			sql = "select book.*, sum(Case when book.status = '" + DbCode.BOOK_STATUS_IN + "' then 1 else 0 end) as rentCount, IFNULL(comment.grade, 0) AS averageGrade, sum(Case when favor.isbn then 1 else 0 end) as favorCount " + 
					"from book " + 
					"left join (select isbn, ROUND(AVG(grade), 2) AS grade from book_comment group by isbn) as comment " + 
					"on book.isbn = comment.isbn " + 
					"left join (select isbn, member_no from favor_book) as favor " + 
					"on book.isbn = favor.isbn " +
					"WHERE " + searchCondition +
					"AND book.category = ? " + 
					"group by book.isbn order by book.no limit ?, ?";
		}

		try {
			pstmt = con.prepareStatement(sql);
			int startRow = (page - 1) * 10;
			pstmt.setString(1, category);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookBean bookBean = new BookBean();
				bookBean.setNo(rs.getInt("no"));
				bookBean.setTitle(rs.getString("title"));
				bookBean.setAuthor(rs.getString("author"));
				bookBean.setPublisher(rs.getString("publisher"));
				bookBean.setPublish_date(rs.getDate("publish_date"));
				bookBean.setPrice(rs.getInt("price"));
				bookBean.setIsbn(rs.getString("isbn"));
				bookBean.setImage(rs.getString("image"));
				bookBean.setStatus(rs.getString("status"));
				bookBean.setKeyword1(rs.getString("keyword1"));
				bookBean.setKeyword2(rs.getString("keyword2"));
				bookBean.setKeyword3(rs.getString("keyword3"));
				bookBean.setCategory(rs.getString("category"));
				bookBean.setRent_code(rs.getString("rent_code"));
				bookBean.setBar_code(rs.getString("bar_code"));
				bookBean.setReg_date(rs.getTimestamp("reg_date"));
				bookBean.setAverageGrade(rs.getFloat("averageGrade"));
				bookBean.setRentCount(rs.getInt("rentCount"));
				bookBean.setFavorCount(rs.getInt("favorCount"));
				bookList.add(bookBean);
			}
			System.out.println("selectBookCategorySearchList(): " + bookList.size() + "개 조회!");
		} catch (SQLException e) {
			System.out.println("selectBookCategorySearchList() 에러: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return bookList;
	}

	public BookBean selectBook(int memberNo, String isbn) {

		BookBean bookBean = null;

		String sql = "";
		if (memberNo != 0) {
			sql = "select book.*, sum(Case when book.status = '" + DbCode.BOOK_STATUS_IN + "' then 1 else 0 end) as rentCount, IFNULL(comment.grade, 0) AS averageGrade, sum(Case when favor.isbn then 1 else 0 end) as favorCount " + 
					"from book " + 
					"left join (select isbn, ROUND(AVG(grade), 2) AS grade from book_comment group by isbn) as comment " + 
					"on book.isbn = comment.isbn " + 
					"left join (select isbn, member_no from favor_book) as favor " + 
					"on book.isbn = favor.isbn and favor.member_no = " + memberNo +  
					" where book.isbn = ? " + 
					"group by book.isbn order by book.no";
		} else {
			sql = "select book.*, sum(Case when book.status = '" + DbCode.BOOK_STATUS_IN + "' then 1 else 0 end) as rentCount, IFNULL(comment.grade, 0) AS averageGrade, sum(Case when favor.isbn then 1 else 0 end) as favorCount " + 
					"from book " + 
					"left join (select isbn, ROUND(AVG(grade), 2) AS grade from book_comment group by isbn) as comment " + 
					"on book.isbn = comment.isbn " + 
					"left join (select isbn, member_no from favor_book) as favor " + 
					"on book.isbn = favor.isbn " + 
					"where book.isbn = ? " + 
					"group by book.isbn order by book.no";
		}

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				bookBean = new BookBean();
				bookBean.setNo(rs.getInt("no"));
				bookBean.setTitle(rs.getString("title"));
				bookBean.setAuthor(rs.getString("author"));
				bookBean.setPublisher(rs.getString("publisher"));
				bookBean.setPublish_date(rs.getDate("publish_date"));
				bookBean.setPrice(rs.getInt("price"));
				bookBean.setIsbn(rs.getString("isbn"));
				bookBean.setImage(rs.getString("image"));
				bookBean.setStatus(rs.getString("status"));
				bookBean.setKeyword1(rs.getString("keyword1"));
				bookBean.setKeyword2(rs.getString("keyword2"));
				bookBean.setKeyword3(rs.getString("keyword3"));
				bookBean.setCategory(rs.getString("category"));
				bookBean.setRent_code(rs.getString("rent_code"));
				bookBean.setBar_code(rs.getString("bar_code"));
				bookBean.setReg_date(rs.getTimestamp("reg_date"));
				bookBean.setAverageGrade(rs.getFloat("averageGrade"));
				bookBean.setRentCount(rs.getInt("rentCount"));
				bookBean.setFavorCount(rs.getInt("favorCount"));
			}
		} catch (SQLException e) {
			System.out.println("selectBook() 에러: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return bookBean;
	}

	public ArrayList<BookCommentBean> selectBookCommentList(String isbn) {
		ArrayList<BookCommentBean> bookCommentList = new ArrayList<BookCommentBean>();

		String sql = "select comment.*, member.name, member.image " + 
				"from book_comment comment " + 
				"join member " + 
				"on (comment.member_no = member.no) " + 
				"where comment.isbn = ? order by comment.no asc";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookCommentBean bookCommentBean = new BookCommentBean();
				bookCommentBean.setNo(rs.getInt("no"));
				bookCommentBean.setContent(rs.getString("content"));
				bookCommentBean.setGrade(rs.getInt("grade"));
				bookCommentBean.setIsbn(rs.getString("isbn"));
				bookCommentBean.setReg_date(rs.getTimestamp("reg_date"));
				bookCommentBean.setMember_no(rs.getInt("member_no"));
				bookCommentBean.setName(rs.getString("name"));
				bookCommentBean.setImage(rs.getString("image"));
				if (bookCommentBean.getImage() != null  && (bookCommentBean.getImage().startsWith("http://") || bookCommentBean.getImage().startsWith("https://"))) {
					bookCommentBean.setImageSrc("http");
				} else {
					bookCommentBean.setImageSrc("file");
				}
				bookCommentList.add(bookCommentBean);
			}
			System.out.println("selectBookCommentList(): " + bookCommentList.size() + "개 조회!");
		} catch (SQLException e) {
			System.out.println("selectBookCommentList() 에러: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return bookCommentList;
	}

	public int insertBookComment(BookCommentBean bookCommentBean) {

		int insertCount = 0;
		String sql = "INSERT INTO book_comment VALUES(?, ?, ?, ?, now(), ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, bookCommentBean.getContent());
			pstmt.setInt(3, bookCommentBean.getGrade());
			pstmt.setString(4, bookCommentBean.getIsbn());
			pstmt.setInt(5, bookCommentBean.getMember_no());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertBookComment() 에러: " + e.getMessage());
		} finally {
			close(pstmt); // PreparedStatement 자원 반환
		}
		System.out.println("insertBookComment() 실행 결과: " + insertCount + "개 성공!");

		return insertCount;
	}

	public int deleteBookComment(int BookCommentNo, int memberNo) {

		int deleteCount = 0;
		String sql = "DELETE FROM book_comment where no = ? AND member_no = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, BookCommentNo);
			pstmt.setInt(2, memberNo);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteBookComment() 에러: " + e.getMessage());
		} finally {
			close(pstmt); // PreparedStatement 자원 반환
		}
		System.out.println("deleteBookComment() 실행 결과: " + deleteCount + "개 성공!");

		return deleteCount;
	}

	public int selectRentableBookNo(String isbn) {

		int bookNo = 0;

		String sql = "select no from book where isbn = ? and status ='" + DbCode.BOOK_STATUS_IN + "' order by no limit 1";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				bookNo = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("selectRentableBookNo() 에러: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return bookNo;
	}

	public int insertBookRent(RentBean rentBean) {

		int insertCount = 0;
		String sql = "INSERT INTO rental VALUES (0, now(), DATE_ADD(NOW(), INTERVAL " + DbCode.RENT_PERIOD + " DAY), null,?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rentBean.getStatus());
			pstmt.setInt(2, rentBean.getMember_no());
			pstmt.setInt(3, rentBean.getBook_no());
			insertCount = pstmt.executeUpdate();
			System.out.println("insertCount: " + insertCount);

			sql = "UPDATE book SET status = ? WHERE no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rentBean.getStatus());
			pstmt.setInt(2, rentBean.getBook_no());
			int updateCount = pstmt.executeUpdate();
			System.out.println("updateCount: " + updateCount);
		} catch (SQLException e) {
			System.out.println("insertBookRent() 에러: " + e.getMessage());
		} finally {
			close(pstmt); // PreparedStatement 자원 반환
		}
		System.out.println("insertBookRent() 실행 결과: " + insertCount + "개 성공!");

		return insertCount;
	}

	public boolean selectBookFavor(int memberNo, String isbn) {

		boolean isBookExist = false;
		String sql = "SELECT * FROM favor_book WHERE member_no = ? AND isbn = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, isbn);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				isBookExist = true;
			} 
		} catch (SQLException e) {
			System.out.println("selectBookFavor() 에러: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return isBookExist;
	}

	public int insertBookFavor(String isbn, int memberNo) {

		int insertCount = 0;
		String sql = "INSERT INTO favor_book VALUES (?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, isbn);
			insertCount = pstmt.executeUpdate();
			System.out.println("insertCount: " + insertCount);
		} catch (SQLException e) {
			System.out.println("insertBookFavor() 에러: " + e.getMessage());
		} finally {
			close(pstmt); // PreparedStatement 자원 반환
		}
		System.out.println("insertBookFavor() 실행 결과: " + insertCount + "개 성공!");

		return insertCount;
	}

	public int deleteBookFavor(String isbn, int memberNo) {

		int deleteCount = 0;
		String sql = "DELETE FROM favor_book WHERE member_no = ? AND isbn = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, isbn);
			deleteCount = pstmt.executeUpdate();
			System.out.println("deleteCount: " + deleteCount);
		} catch (SQLException e) {
			System.out.println("deleteBookFavor() 에러: " + e.getMessage());
		} finally {
			close(pstmt); // PreparedStatement 자원 반환
		}
		System.out.println("deleteBookFavor() 실행 결과: " + deleteCount + "개 성공!");

		return deleteCount;
	}
	
	// 베스트 대출 관련
	public int selectBookBestRentListCount() {

		int listCount = 0;

		String sql = "select count(distinct book_no) " + 
				"from rental " + 
				"join (select no, isbn, category from book group by isbn) as book " + 
				"on rental.book_no = book.no";		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				listCount = rs.getInt(1);
			}
			System.out.println("selectBookBestRentListCount(): " + listCount);
		} catch (SQLException e) {
			System.out.println("selectBookBestRentListCount() 에러: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	public int selectBookBestRentCategoryListCount(String category) {

		int listCount = 0;

		String sql = "select count(distinct book_no) " + 
				"from rental " + 
				"join (select no, isbn, category from book group by isbn) as book " + 
				"on rental.book_no = book.no " + 
				"where book.category = ?";
		
		System.out.println("selectBookBestRentCategoryListCount: " + sql);

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				listCount = rs.getInt(1);
			}
			System.out.println("selectBookBestRentCategoryListCount(" + category + "): " + listCount);
		} catch (SQLException e) {
			System.out.println("selectBookBestRentCategoryListCount() 에러: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	public ArrayList<BookBean> selectBookBestRentList(int memberNo) {

		ArrayList<BookBean> bookList = new ArrayList<BookBean>();

		String sql = "select book.*, sum(Case when book.status = '" + DbCode.BOOK_STATUS_IN + "' then 1 else 0 end) as rentCount, IFNULL(comment.grade, 0) AS averageGrade, sum(Case when favor.isbn then 1 else 0 end) as favorCount, count(rental.book_no) as bestRentCount " + 
				"from rental " + 
				"join book " + 
				"on rental.book_no = book.no " + 
				"left join (select isbn, ROUND(AVG(grade), 2) AS grade from book_comment group by isbn) as comment " + 
				"on book.isbn = comment.isbn " + 
				"left join (select isbn, member_no from favor_book) as favor " + 
				"on book.isbn = favor.isbn and favor.member_no = ? " + 
				"group by book.isbn order by count(*) desc limit 0, 10";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookBean bookBean = new BookBean();
				bookBean.setNo(rs.getInt("no"));
				bookBean.setTitle(rs.getString("title"));
				bookBean.setAuthor(rs.getString("author"));
				bookBean.setPublisher(rs.getString("publisher"));
				bookBean.setPublish_date(rs.getDate("publish_date"));
				bookBean.setPrice(rs.getInt("price"));
				bookBean.setIsbn(rs.getString("isbn"));
				bookBean.setImage(rs.getString("image"));
				bookBean.setStatus(rs.getString("status"));
				bookBean.setKeyword1(rs.getString("keyword1"));
				bookBean.setKeyword2(rs.getString("keyword2"));
				bookBean.setKeyword3(rs.getString("keyword3"));
				bookBean.setCategory(rs.getString("category"));
				bookBean.setRent_code(rs.getString("rent_code"));
				bookBean.setBar_code(rs.getString("bar_code"));
				bookBean.setReg_date(rs.getTimestamp("reg_date"));
				bookBean.setAverageGrade(rs.getFloat("averageGrade"));
				bookBean.setRentCount(rs.getInt("rentCount"));
				bookBean.setFavorCount(rs.getInt("favorCount"));
				bookBean.setBestRentCount(rs.getInt("bestRentCount"));
				bookList.add(bookBean);
			}
			System.out.println("selectBookBestRentList(): " + bookList.size() + "개 조회!");
		} catch (SQLException e) {
			System.out.println("selectBookBestRentList() 에러: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return bookList;
	}

	public ArrayList<BookBean> selectBookBestRentCategoryList(String category, int memberNo) {

		ArrayList<BookBean> bookList = new ArrayList<BookBean>();

		String sql = "select book.*, sum(Case when book.status = '" + DbCode.BOOK_STATUS_IN + "' then 1 else 0 end) as rentCount, IFNULL(comment.grade, 0) AS averageGrade, sum(Case when favor.isbn then 1 else 0 end) as favorCount, count(rental.book_no) as bestRentCount " + 
				"from rental " + 
				"join book " + 
				"on rental.book_no = book.no " + 
				"left join (select isbn, ROUND(AVG(grade), 2) AS grade from book_comment group by isbn) as comment " + 
				"on book.isbn = comment.isbn " + 
				"left join (select isbn, member_no from favor_book) as favor " + 
				"on book.isbn = favor.isbn and favor.member_no = ? " + 
				"WHERE book.category = ? " + 
				"group by book.isbn order by count(*) desc limit 0, 10";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, category);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookBean bookBean = new BookBean();
				bookBean.setNo(rs.getInt("no"));
				bookBean.setTitle(rs.getString("title"));
				bookBean.setAuthor(rs.getString("author"));
				bookBean.setPublisher(rs.getString("publisher"));
				bookBean.setPublish_date(rs.getDate("publish_date"));
				bookBean.setPrice(rs.getInt("price"));
				bookBean.setIsbn(rs.getString("isbn"));
				bookBean.setImage(rs.getString("image"));
				bookBean.setStatus(rs.getString("status"));
				bookBean.setKeyword1(rs.getString("keyword1"));
				bookBean.setKeyword2(rs.getString("keyword2"));
				bookBean.setKeyword3(rs.getString("keyword3"));
				bookBean.setCategory(rs.getString("category"));
				bookBean.setRent_code(rs.getString("rent_code"));
				bookBean.setBar_code(rs.getString("bar_code"));
				bookBean.setReg_date(rs.getTimestamp("reg_date"));
				bookBean.setAverageGrade(rs.getFloat("averageGrade"));
				bookBean.setRentCount(rs.getInt("rentCount"));
				bookBean.setFavorCount(rs.getInt("favorCount"));
				bookBean.setBestRentCount(rs.getInt("bestRentCount"));
				bookList.add(bookBean);
			}
			System.out.println("selectBookBestRentCategoryList(): " + bookList.size() + "개 조회!");
		} catch (SQLException e) {
			System.out.println("selectBookBestRentCategoryList() 에러: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return bookList;
	}
}
