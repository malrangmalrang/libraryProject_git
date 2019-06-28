package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import vo.ReviewBean;
import vo.book.BookBean;

public class MainDAO {
	
	private static MainDAO instance;
	
	private MainDAO() {}
	
	public static MainDAO getInstance() {
		if(instance == null) {
			instance = new MainDAO();
		}
		return instance;
	}
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
	// 베스트 대여 5개
	public ArrayList<BookBean> getBestBookList() {
		ArrayList<BookBean> bestBookList = null;
		
		String sql = "SELECT book.*, COUNT(*) as rentCount FROM book join rental on book.no=rental.book_no "
				+ "GROUP BY isbn ORDER BY rentCount DESC limit 0, 5";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			bestBookList = new ArrayList<BookBean>();
			while(rs.next()) {
				BookBean bookBean = new BookBean();
				bookBean.setIsbn(rs.getString("isbn"));
				bookBean.setImage(rs.getString("image"));
				bookBean.setRentCount(rs.getInt("rentCount"));
				bestBookList.add(bookBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return bestBookList;
	}
	
	
	// 최신 리뷰 5개
	public ArrayList<ReviewBean> getRecentReviewList() {
		ArrayList<ReviewBean> recentReviewList = null;
		String content = "";
		
		String sql = "SELECT review.no, review.title, review.content, book.image FROM review "
				+ "JOIN rental ON review.rental_no = rental.no "
				+ "JOIN book ON rental.book_no = book.no "
				+ "ORDER BY review.reg_date DESC LIMIT 0, 5";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			recentReviewList = new ArrayList<ReviewBean>();
			
			while(rs.next()) {
				ReviewBean reviewBean = new ReviewBean();
				
				reviewBean.setNo(rs.getInt("no"));
				reviewBean.setTitle(rs.getString("title"));
				
				content = rs.getString("content");
				if(content.length() > 210) {
					content = rs.getString("content").substring(0, 210) + "...";
					reviewBean.setContent(content);
				} else {
					reviewBean.setContent(content);
				}
				
				reviewBean.setImage(rs.getString("image"));
				
				recentReviewList.add(reviewBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return recentReviewList;
	}

	public JSONArray getRecentNoticeList() {
		JSONArray noticeList = null;
		String content = "";
		
		String sql = "SELECT no, content FROM board WHERE board_type='notice' ORDER BY reg_date DESC LIMIT 0, 5";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			noticeList = new JSONArray();
			while(rs.next()) {
				JSONObject notice = new JSONObject();
				
				notice.put("no", rs.getInt("no") + "");
				content = rs.getString("content").trim();
				if(content.length() > 40) {
					content = content.substring(0, 39) + "...";
				}
				notice.put("content", content);
				
				noticeList.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return noticeList;
	}

	public void updateOverude_DAO() {
		System.out.println("Due DAO");
		ArrayList dueList=new ArrayList();
		String sql="select * from rental where due_date<now() AND status='대출중'";
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				dueList.add(rs.getInt("no"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			  close(pstmt);
			  close(rs);
	    }
		
		for(int i=0; i<dueList.size(); i++) {
		System.out.println("연체변경할 rental no : "+ dueList.get(i));
		}
		
		
		
		for(int i=0; i<dueList.size(); i++) {
			try {
				System.out.println(i+"번 째 연체변경시작");
				sql="UPDATE rental SET status=? where no=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "연체");
				pstmt.setInt(2, (int)dueList.get(i));
				pstmt.executeUpdate();
				System.out.println(i+"번 째 연체변경완료");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		}


	}
	
	
}
