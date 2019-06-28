package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import vo.MemberBean;
import vo.ReviewBean;
import vo.book.BookBean;

public class ReviewDAO {
	private static ReviewDAO instance;
	Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private ReviewDAO() {

	}

	public static ReviewDAO getinstance() {
		if (instance == null) {
			instance = new ReviewDAO();
		}
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	// ------------- checkMemberNo -----------------------------------
		public int checkMemberNo(String email) {
			int checkMemberNo = 0;
			String sql = "select no from member where email=?";

			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, email);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					checkMemberNo = rs.getInt("no");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("checkMemberNo 에러 : " + e.getMessage());
			} finally {
				close(pstmt);
				close(rs);
			}

			return checkMemberNo;
		}
		//------------------ checkMemberType -------------------
		public int checkMemberType(int checkMemberNo) {
			int checkMemberType = 0;
			String temp;
			String sql = "select type from member where no=?";

			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, checkMemberNo);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					temp = rs.getString("type");
					if(temp.equals("미승인")) {
						checkMemberType = 0;
					}else {
						checkMemberType = 1;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("checkMemberNo 에러 : " + e.getMessage());
			} finally {
				close(pstmt);
				close(rs);
			}

			return checkMemberType;
		}
		// -------------------- isWriter -------------------------------
		public boolean isArticleReviewWriter(int no) {

			boolean isWriter = false;
			String sql = "SELECT * FROM review WHERE no=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				
//				if(rs.next()) {
//					writer_no = rs.getInt("member_no");
//				}
//				
//				sql = "select * from member where no=?";
//				pstmt = con.prepareStatement(sql);
//				pstmt.setInt(1, no);
//				rs = pstmt.executeQuery();
//				
//				if(rs.next()) {
//					session_no = rs.getInt("no");
//				}
//				if(session_no == writer_no) {
//					isWriter = true;
//				}
				
			} catch (SQLException e) {
				System.out.println("isWriter() 실패! : " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return isWriter;
		}
		//--------------------updateArticle----------------------------------
		public int updateArticle(ReviewBean reviewBean) {
			int updateCount = 0;
			
			// BoardBean 객체의 board_num 에 해당하는 레코드를 수정
			// => 글제목(board_subject), 글내용(content) 수정
			String sql = "UPDATE review SET title=?,content=? WHERE no=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, reviewBean.getTitle());
				pstmt.setString(2, reviewBean.getContent());
				pstmt.setInt(3, reviewBean.getNo());
				updateCount = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("updateArticle() 실패! : " + e.getMessage());
			} finally {
				close(pstmt);
			}
			
			return updateCount;
		}
		
		//---------------- insertArticle ---------------------
		public int insertArticle(ReviewBean reviewBean) {
			int insertCount = 0;

			String sql="";	
			
			try {
				sql = "insert into review(no,title,content,readcount,file,ispublic,reg_date,rental_no) values(null,?,?,0,?,?,now(),?)";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, reviewBean.getTitle());
				pstmt.setString(2, reviewBean.getContent());
				pstmt.setString(3, reviewBean.getFile());
				pstmt.setString(4, reviewBean.getIspublic());
				pstmt.setInt(5, reviewBean.getRental_no());
				//대여한 책 번호를 넘겨줘야함
				
				System.out.println(pstmt);
				
				
				insertCount = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("insert 에러 : " + e.getMessage());
			} finally {
				close(pstmt);
				close(rs);
			}
			System.out.println(insertCount + "개 성공");

			return insertCount;
		}
		//-------------selectCount---------------------------
		public int selectCount() {
			System.out.println("ReviewDAO - selectCount()");
			int listCount = 0;
			String sql = "select count(*) from review where ispublic ='공개'";
			try {
				

				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					listCount = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("selectCount : 실패" + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			return listCount;
		}
		public int selectCount(String option, String keyword) {
			System.out.println("ReviewDAO - selectCount(keyword)");
			int listCount = 0;
			String sql = "select count(*) from review v, rental r, member m, book b where v.rental_no = r.no and m.no = r.member_no and b.no = r.book_no and v.ispublic = '공개' and "+option+" like '%"+keyword+"%' ";
			try {
				

				pstmt = con.prepareStatement(sql);
				System.out.println("검색 selectCount 쿼리 : "+pstmt);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					listCount = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("selectCount : 실패" + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			return listCount;
		}
		// ------------selectArticleList ---------------------------

		public ArrayList selectArticleList(int page, int limit) {
			ArrayList<Object> articleList = new ArrayList<Object>();
			ArrayList<ReviewBean> reviewList = new ArrayList<ReviewBean>();
			ArrayList<MemberBean> memberList = new ArrayList<MemberBean>();
			ArrayList<BookBean> bookList = new ArrayList<BookBean>();
			
			ReviewBean reviewBean = null;
			MemberBean memberBean = null;
			BookBean bookBean = null;
			int startRow = (page - 1) * 10; // 읽기 시작할 row 번호
//			String sql = "select email from member where type=?";

			// => 참조글번호 내림차순 & 답글순서번호 오름차순 정렬
			// => 지정 row 번호부터 10개 조회

			try {
//				pstmt = con.prepareStatement(sql);
//				pstmt.setString(1, member_type);
//				rs = pstmt.executeQuery();
//				
//				if(rs.next()) {
//					email = rs.getString(1);
//				}
				
				String sql = "select * " + 
						" from review v, rental r, member m, book b " + 
						" where v.rental_no = r.no " + 
						" and m.no = r.member_no " + 
						" and b.no = r.book_no and v.ispublic = '공개' ORDER BY v.reg_date DESC LIMIT ?,? ";
				
				pstmt = con.prepareStatement(sql);
				System.out.println("selectArticleList 검색 쿼리 : "+pstmt);
//				pstmt.setString(1,board_type);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, limit);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// 1개 게시물 레코드 읽어와서 BoardBean 객체에 저장
					reviewBean = new ReviewBean();
					
					reviewBean.setNo(rs.getInt("v.no"));
					reviewBean.setTitle(rs.getString("v.title"));
					reviewBean.setContent(rs.getString("v.content"));
					reviewBean.setReadcount(rs.getInt("v.readcount"));
					reviewBean.setFile(rs.getString("v.file"));
					reviewBean.setReg_date(rs.getDate("v.reg_date"));
					reviewBean.setIspublic(rs.getString("v.ispublic"));
					reviewBean.setRental_no(rs.getInt("v.rental_no"));
					
					memberBean = new MemberBean();
					
					memberBean.setAddress1(rs.getString("m.address1"));
					memberBean.setAddress2(rs.getString("m.address2"));
					memberBean.setBirth(rs.getString("m.birth"));
					memberBean.setEmail(rs.getString("m.email"));
					memberBean.setGender(rs.getString("m.gender"));
					memberBean.setImage(rs.getString("m.image"));
					memberBean.setName(rs.getString("m.name"));
					memberBean.setNo(rs.getInt("m.no"));
					memberBean.setPassword(rs.getString("m.password"));
					memberBean.setPhone(rs.getString("m.phone"));
					memberBean.setPostcode(rs.getInt("m.postcode"));
					memberBean.setReg_date(rs.getDate("m.reg_date"));
					memberBean.setType(rs.getString("m.type"));
					
					bookBean = new BookBean();
					
					bookBean.setNo(rs.getInt("b.no"));
					bookBean.setTitle(rs.getString("b.title"));
					bookBean.setAuthor(rs.getString("b.author"));
					bookBean.setPublisher(rs.getString("b.publisher"));
					bookBean.setPublish_date(rs.getDate("b.publish_date"));
					bookBean.setPrice(rs.getInt("b.price"));
					bookBean.setIsbn(rs.getString("b.isbn"));
					bookBean.setImage(rs.getString("b.image"));
					bookBean.setStatus(rs.getString("b.status"));
					bookBean.setKeyword1(rs.getString("b.keyword1"));
					bookBean.setKeyword2(rs.getString("b.keyword2"));
					bookBean.setKeyword3(rs.getString("b.keyword3"));
					bookBean.setCategory(rs.getString("b.category"));
					bookBean.setRent_code(rs.getString("b.rent_code"));
					bookBean.setBar_code(rs.getString("b.bar_code"));
					bookBean.setReg_date(rs.getTimestamp("b.reg_date"));
					
					reviewList.add(reviewBean);
					memberList.add(memberBean);
					bookList.add(bookBean);
//					articleList.add(reviewBean); // ArrayList 객체에 레코드 단위로 저장
//					articleList.add(memberBean);
//					articleList.add(bookBean);
				}
				articleList.add(reviewList);
				articleList.add(memberList);
				articleList.add(bookList);
				
			} catch (SQLException e) {
//				e.printStackTrace();
				System.out.println("selectArticleList() 실패! : " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}

			return articleList;
		}
		
		public ArrayList selectArticleList(int page, int limit, String option, String keyword) {
			ArrayList<Object> articleList = new ArrayList<Object>();
			ArrayList<ReviewBean> reviewList = new ArrayList<ReviewBean>();
			ArrayList<MemberBean> memberList = new ArrayList<MemberBean>();
			ArrayList<BookBean> bookList = new ArrayList<BookBean>();
			
			ReviewBean reviewBean = null;
			MemberBean memberBean = null;
			BookBean bookBean = null;
			int startRow = (page - 1) * 10; // 읽기 시작할 row 번호
//			String sql = "select email from member where type=?";

			// => 참조글번호 내림차순 & 답글순서번호 오름차순 정렬
			// => 지정 row 번호부터 10개 조회

			try {
				String sql = "select * " + 
						" from review v, rental r, member m, book b " + 
						" where v.rental_no = r.no " + 
						" and m.no = r.member_no " + 
						" and b.no = r.book_no and v.ispublic = '공개' " +
						" and "+option+" like '%"+keyword+"%' "+
						" ORDER BY v.reg_date DESC LIMIT ?,? ";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, limit);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// 1개 게시물 레코드 읽어와서 BoardBean 객체에 저장
					reviewBean = new ReviewBean();
					
					reviewBean.setNo(rs.getInt("v.no"));
					reviewBean.setTitle(rs.getString("v.title"));
					reviewBean.setContent(rs.getString("v.content"));
					reviewBean.setReadcount(rs.getInt("v.readcount"));
					reviewBean.setFile(rs.getString("v.file"));
					reviewBean.setReg_date(rs.getDate("v.reg_date"));
					reviewBean.setIspublic(rs.getString("v.ispublic"));
					reviewBean.setRental_no(rs.getInt("v.rental_no"));
					
					memberBean = new MemberBean();
					
					memberBean.setAddress1(rs.getString("m.address1"));
					memberBean.setAddress2(rs.getString("m.address2"));
					memberBean.setBirth(rs.getString("m.birth"));
					memberBean.setEmail(rs.getString("m.email"));
					memberBean.setGender(rs.getString("m.gender"));
					memberBean.setImage(rs.getString("m.image"));
					memberBean.setName(rs.getString("m.name"));
					memberBean.setNo(rs.getInt("m.no"));
					memberBean.setPassword(rs.getString("m.password"));
					memberBean.setPhone(rs.getString("m.phone"));
					memberBean.setPostcode(rs.getInt("m.postcode"));
					memberBean.setReg_date(rs.getDate("m.reg_date"));
					memberBean.setType(rs.getString("m.type"));
					
					bookBean = new BookBean();
					
					bookBean.setNo(rs.getInt("b.no"));
					bookBean.setTitle(rs.getString("b.title"));
					bookBean.setAuthor(rs.getString("b.author"));
					bookBean.setPublisher(rs.getString("b.publisher"));
					bookBean.setPublish_date(rs.getDate("b.publish_date"));
					bookBean.setPrice(rs.getInt("b.price"));
					bookBean.setIsbn(rs.getString("b.isbn"));
					bookBean.setImage(rs.getString("b.image"));
					bookBean.setStatus(rs.getString("b.status"));
					bookBean.setKeyword1(rs.getString("b.keyword1"));
					bookBean.setKeyword2(rs.getString("b.keyword2"));
					bookBean.setKeyword3(rs.getString("b.keyword3"));
					bookBean.setCategory(rs.getString("b.category"));
					bookBean.setRent_code(rs.getString("b.rent_code"));
					bookBean.setBar_code(rs.getString("b.bar_code"));
					bookBean.setReg_date(rs.getTimestamp("b.reg_date"));
					
					reviewList.add(reviewBean);
					memberList.add(memberBean);
					bookList.add(bookBean);
//					articleList.add(reviewBean); // ArrayList 객체에 레코드 단위로 저장
//					articleList.add(memberBean);
//					articleList.add(bookBean);
				}
				articleList.add(reviewList);
				articleList.add(memberList);
				articleList.add(bookList);
				
			} catch (SQLException e) {
//				e.printStackTrace();
				System.out.println("selectArticleList() 실패! : " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}

			return articleList;
		}
		
		//----------------- getViewArticle -------------------
		public BookBean getViewBook(int no) {
			System.out.println("getViewBook");
			ArrayList<Object> articleList = new ArrayList<Object>();
			ArrayList<ReviewBean> reviewList = new ArrayList<ReviewBean>();
			ArrayList<MemberBean> memberList = new ArrayList<MemberBean>();
			ArrayList<BookBean> bookList = new ArrayList<BookBean>();
			
			ReviewBean reviewBean = null;
			MemberBean memberBean = null;
			BookBean bookBean = null;
			
			String sql = "select * " + 
					" from review v, rental r, member m, book b " + 
					" where v.rental_no = r.no " + 
					" and m.no = r.member_no " + " and v.no = ?" +
					" and b.no = r.book_no and v.ispublic = '공개'";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					reviewBean = new ReviewBean();
					
					reviewBean.setNo(rs.getInt("v.no"));
					reviewBean.setTitle(rs.getString("v.title"));
					reviewBean.setContent(rs.getString("v.content"));
					reviewBean.setReadcount(rs.getInt("v.readcount"));
					reviewBean.setFile(rs.getString("v.file"));
					reviewBean.setReg_date(rs.getDate("v.reg_date"));
					reviewBean.setIspublic(rs.getString("v.ispublic"));
					reviewBean.setRental_no(rs.getInt("v.rental_no"));
					
					bookBean = new BookBean();
					
					bookBean.setNo(rs.getInt("b.no"));
					bookBean.setTitle(rs.getString("b.title"));
					bookBean.setAuthor(rs.getString("b.author"));
					bookBean.setPublisher(rs.getString("b.publisher"));
					bookBean.setPublish_date(rs.getDate("b.publish_date"));
					bookBean.setPrice(rs.getInt("b.price"));
					bookBean.setIsbn(rs.getString("b.isbn"));
					bookBean.setImage(rs.getString("b.image"));
					bookBean.setStatus(rs.getString("b.status"));
					bookBean.setKeyword1(rs.getString("b.keyword1"));
					bookBean.setKeyword2(rs.getString("b.keyword2"));
					bookBean.setKeyword3(rs.getString("b.keyword3"));
					bookBean.setCategory(rs.getString("b.category"));
					bookBean.setRent_code(rs.getString("b.rent_code"));
					bookBean.setBar_code(rs.getString("b.bar_code"));
					bookBean.setReg_date(rs.getTimestamp("b.reg_date"));
					
					memberBean = new MemberBean();
					
					memberBean.setAddress1(rs.getString("m.address1"));
					memberBean.setAddress2(rs.getString("m.address2"));
					memberBean.setBirth(rs.getString("m.birth"));
					memberBean.setEmail(rs.getString("m.email"));
					memberBean.setGender(rs.getString("m.gender"));
					memberBean.setImage(rs.getString("m.image"));
					memberBean.setName(rs.getString("m.name"));
					memberBean.setNo(rs.getInt("m.no"));
					memberBean.setPassword(rs.getString("m.password"));
					memberBean.setPhone(rs.getString("m.phone"));
					memberBean.setPostcode(rs.getInt("m.postcode"));
					memberBean.setReg_date(rs.getDate("m.reg_date"));
					memberBean.setType(rs.getString("m.type"));
					
					reviewList.add(reviewBean);
					memberList.add(memberBean);
					bookList.add(bookBean);
				}
				articleList.add(reviewList);
				articleList.add(memberList);
				articleList.add(bookList);
				
			} catch (SQLException e) {
//				e.printStackTrace();
				System.out.println("getViewBook() 실패! : " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return bookBean;
		}
		
		
		public ReviewBean getViewArticle(int no) {
			System.out.println("getViewArticle");
			ReviewBean reviewBean = null;
//			MemberBean memberBean = null;
			
			String sql = "SELECT * FROM review WHERE no=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					reviewBean = new ReviewBean();
					
					reviewBean.setNo(rs.getInt("no"));
					reviewBean.setTitle(rs.getString("title"));
					reviewBean.setContent(rs.getString("content"));
					reviewBean.setReadcount(rs.getInt("readcount"));
					reviewBean.setFile(rs.getString("file"));
					reviewBean.setReg_date(rs.getDate("reg_date"));
//					reviewBean.setImage(rs.getString("image"));
//					reviewBean.setWriter(rs.getString("writer"));
//					reviewBean.setIsbn(rs.getString("isbn"));
					
					
					
				}
				
			} catch (SQLException e) {
//				e.printStackTrace();
				System.out.println("getViewArticle() 실패! : " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
					
			return reviewBean;
		}
		
		
		public int updateReadcount(int no) {
			int updateCount = 0;
			
			String sql = "UPDATE review SET readcount=readcount+1 WHERE no=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				updateCount = pstmt.executeUpdate();
//				System.out.println(updateCount);
			} catch (SQLException e) {
				System.out.println("updateReadcount() 실패! : " + e.getMessage());
			} finally {
				close(pstmt);
			}
			
			return updateCount;
		}
		//----------------- deleteArticle --------------------------
		public int deleteArticle(int no) {
			int deleteCount = 0;
			
			// board_num 에 해당하는 레코드를 삭제
			String sql = "DELETE FROM review WHERE no=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				deleteCount = pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("deleteArticle() 실패! : " + e.getMessage());
			} finally {
				close(pstmt);
			}
			
			return deleteCount;
		}
		
		// 읽은 책 리스트 (리뷰 쓴 거 제외)
		public ArrayList<HashMap<String, String>> getRentalBookList(int member_no) {
			ArrayList<HashMap<String, String>> bookList = null;
			HashMap<String, String> book = null;
			String sql = "SELECT rental.no, book.image FROM rental "
					+ "JOIN book on rental.book_no = book.no WHERE rental.member_no=? AND isbn NOT IN "
					+ "(SELECT book.isbn FROM review JOIN (SELECT no, book_no FROM rental WHERE member_no = ?) r ON review.rental_no=r.no "
					+ "JOIN book ON r.book_no = book.no) GROUP BY book.isbn";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, member_no);
				pstmt.setInt(2, member_no);
				rs = pstmt.executeQuery();
				bookList = new ArrayList<HashMap<String, String>>();
				
				while(rs.next()) {
					book = new HashMap<String, String>();
					book.put("rental_no", rs.getInt("no") + "");
					book.put("image", rs.getString("image"));
					
					bookList.add(book);
				}
			} catch (SQLException e) {
				System.out.println("getRentalBookList() 실패! : " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			} 
			
			return bookList;
		}





}
