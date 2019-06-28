package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.WishBookBean;

import static db.JdbcUtil.*;

public class WishBookDAO {
	private static WishBookDAO instance;
	Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private WishBookDAO() {
		
	}
	public static WishBookDAO getinstance() {
		if(instance == null) {
			instance = new WishBookDAO();
		}
		return instance;
	}
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//------------- insertArticle--------------------------
	public int insertWishBookArticle(WishBookBean wishBookBean) {
		int insertCount = 0;
		String sql= "insert into wish_book values(null,?,?,?,?,?,?,?,?,now(),?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, wishBookBean.getTitle());
			pstmt.setString(2, wishBookBean.getAuthors());
			pstmt.setString(3, wishBookBean.getPublisher());
			pstmt.setString(4, wishBookBean.getPublish_date());
			pstmt.setInt(5, wishBookBean.getPrice());
			pstmt.setString(6, wishBookBean.getIsbn());
			pstmt.setString(7, wishBookBean.getImage());
			pstmt.setString(8, "신청");
			pstmt.setInt(9, 1);
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("insert 에러 : " + e.getMessage());
		}finally {
			close(pstmt);
		}
		System.out.println(insertCount + "개 성공");
		
		return insertCount;
	}
	
}
