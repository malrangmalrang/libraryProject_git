package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.AES256Cipher;
import static db.JdbcUtil.*;

public class MyDAO {
	
	private static MyDAO instance;
	
	private MyDAO() {}
	
	public static MyDAO getInstance() {
		if(instance == null) {
			instance = new MyDAO();
		}
		return instance;
	}
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public int updatePass(int memberNo, String nowPass, String newPass) {
		int updateCount = -1;
		
		String sql = "UPDATE member SET password=? WHERE no=? AND password=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, AES256Cipher.getInstance().encryption(newPass));
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, AES256Cipher.getInstance().encryption(nowPass));
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	public int updateProfilImage(int member_no, String image) {
		int updateCount = 0;
		
		String sql = "UPDATE member SET image=? WHERE no=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, image);
			pstmt.setInt(2, member_no);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}
	
	
}
